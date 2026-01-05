package com.lemoncustardcreations.catalog.product;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public List<Product> findByCategoryId(Long id) {
        return repo.findByCategoryId(id);
    }

    public void save(Product product) {
        repo.save(product);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
