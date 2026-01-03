package com.lemoncustardcreations.catalog.category;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Category findById(Long id) {
        return repo.findById(id)
            .orElse(new Category(
                0L,
                "Unknown Category",
                "Category Does Not Exist"
            ));
    }
    
}
