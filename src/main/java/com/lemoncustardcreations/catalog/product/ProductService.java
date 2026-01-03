package com.lemoncustardcreations.catalog.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lemoncustardcreations.catalog.image.ImageService;

@Service
public class ProductService {

    private final ProductRepository repo;

    private final ImageService imageService;

    public ProductService(
        ProductRepository repo,
        ImageService imageService
    ) {
        this.repo = repo;
        this.imageService = imageService;
    }

    public List<Product> findAll() {
        List<Product> products = repo.findAll();
        for (Product product : products) {
            String imageUrl = imageService.getImageUrl();
            product.addImageUrl(imageUrl);
        }
        return products;
    }

    public List<Product> findByCategoryId(Long id) {
        List<Product> products = repo.findByCategoryId(id);
        for (Product product : products) {
            String imageUrl = imageService.getImageUrl();
            product.addImageUrl(imageUrl);
        }
        return products;
    }

    public void save(Product product) {
        repo.save(product);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
