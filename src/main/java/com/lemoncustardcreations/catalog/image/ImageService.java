package com.lemoncustardcreations.catalog.image;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.lemoncustardcreations.catalog.category.Category;
import com.lemoncustardcreations.catalog.category.CategoryRepository;
import com.lemoncustardcreations.catalog.product.Product;
import com.lemoncustardcreations.catalog.product.ProductRepository;

@Service
public class ImageService {

    private final ImageRepository imageRepo;

    private final CategoryRepository categoryRepo;

    private final ProductRepository productRepo;

    private final Cloudinary cloudinary;

    public ImageService(
        ImageRepository imageRepo,
        CategoryRepository categoryRepo,
        ProductRepository productRepo,
        @Value("${app.cloudinary.cloud-name}") String cloudName,
        @Value("${app.cloudinary.api-key}") String apiKey,
        @Value("${app.cloudinary.api-secret}") String apiSecret
    ) {
        this.imageRepo = imageRepo;
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret,
            "secure", true
        ));
    }

    private Image create(MultipartFile imageBytes) throws IOException {
        Map res = this.cloudinary.uploader()
            .upload(imageBytes.getBytes(), ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", true,
                "overwrite", true
            ));
        String url = res.get("secure_url").toString();
        String publicId = res.get("public_id").toString();

        Image image = new Image();
        image.setUrl(url);
        image.setPublicId(publicId);

        return image;
    }

    public void createForCategory(Long categoryId, MultipartFile imageBytes) {
        try {
            Image image = create(imageBytes);
            Category category = categoryRepo.getReferenceById(categoryId);
            image.setCategory(category);
            imageRepo.save(image);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createForProduct(Long productId, MultipartFile imageBytes) {
        try {
            Image image = create(imageBytes);
            Product product = productRepo.getReferenceById(productId);
            image.setProduct(product);
            imageRepo.save(image);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Long id) {
        Image image = imageRepo.findById(id)
            .orElse(null);
        if (image == null) return;

        try {
            this.cloudinary.uploader()
                .destroy(image.getPublicId(), ObjectUtils.emptyMap());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        imageRepo.deleteById(id);
    }

}
