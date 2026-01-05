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
            .orElse(new Category());
    }

    public void save(Category category) {
        if (category.getPosition() == null) {
            category.setPosition((int) repo.count());
        }
        repo.save(category);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void incPosition(Long id) {
        Category mover = findById(id);
        Category moved = null;

        if (mover.getId() == null || mover.getPosition() == 0) return;
        for (Category it : findAll()) {
            if (it.getPosition() == mover.getPosition() - 1) {
                moved = it;
            }
        }

        mover.setPosition(mover.getPosition() - 1);
        save(mover);

        if (moved != null) {
            moved.setPosition(moved.getPosition() + 1);
            save(moved);
        }
    }
    
}
