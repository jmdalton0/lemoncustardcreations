package com.lemoncustardcreations.catalog.image;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @PostMapping("/category")
    public void createForCategory(
        @RequestParam() Long categoryId,
        @RequestParam() MultipartFile image 
    ) {
        service.createForCategory(categoryId, image);
    }

    @PostMapping("/product")
    public void createForProduct(
        @RequestParam() Long productId,
        @RequestParam() MultipartFile image 
    ) {
        service.createForProduct(productId, image);
    }
    
}
