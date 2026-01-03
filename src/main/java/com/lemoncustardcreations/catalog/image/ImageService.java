package com.lemoncustardcreations.catalog.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageService {

    private final int W = 400;
    private final int H = 500;

    private final List<String> urls;

    private final Cloudinary cloudinary;

    public ImageService(
        @Value("${app.cloudinary.cloud-name}") String cloudName,
        @Value("${app.cloudinary.api-key}") String apiKey,
        @Value("${app.cloudinary.api-secret}") String apiSecret
    ) {
        this.urls = new ArrayList<>();
        urls.add("main-sample");
        urls.add("diamonds-front_oecwx4");
        urls.add("blue-leaves-front_ivgqqa");
        urls.add("purple-peach_r6nqay");

        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret,
            "secure", true
        ));
    }

    public String getImageUrl() {
        Random rand = new Random();
        return cloudinary
            .url()
            .transformation(new Transformation<>().width(W).height(H).crop("fill"))
            .generate(urls.get(rand.nextInt(urls.size())));
    }
    
}
