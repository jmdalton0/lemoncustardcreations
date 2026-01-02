package com.lemoncustardcreations.catalog.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageService {

    private final int W = 400;
    private final int H = 500;

    private final Cloudinary cloudinary;

    public ImageService(
        @Value("${app.cloudinary.cloud-name}") String cloudName,
        @Value("${app.cloudinary.api-key}") String apiKey,
        @Value("${app.cloudinary.api-secret}") String apiSecret
    ) {
        System.out.println(cloudName);
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret,
            "secure", true
        ));
    }

    public String getImageUrl() {
        return cloudinary
            .url()
            .transformation(new Transformation<>().width(W).height(H).crop("fill"))
            .generate("samples/animals/three-dogs");
    }
    
}
