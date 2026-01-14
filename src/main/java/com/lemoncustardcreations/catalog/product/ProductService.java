package com.lemoncustardcreations.catalog.product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lemoncustardcreations.catalog.category.Category;
import com.lemoncustardcreations.catalog.category.CategoryRepository;

@Service
public class ProductService {

    private final ProductRepository repo;

    private final CategoryRepository categoryRepo;

    public ProductService(
        ProductRepository repo,
        CategoryRepository categoryRepo
    ) {
        this.repo = repo;
        this.categoryRepo = categoryRepo;
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Product findById(Long id) {
        return repo.findById(id)
            .orElse(new Product());
    }

    public List<Product> findByCategoryId(Long id) {
        return repo.findByCategoryIdOrderByPosition(id);
    }

    public void save(ProductDTO productDTO) {
        Product product = new Product();
        if (productDTO.id() != null) {
            product = repo.findById(productDTO.id()).orElse(null);
        }
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setDescription(productDTO.description());
        product.setPosition(productDTO.position());
        if (product.getPosition() == null) {
            product.setPosition((int) repo.count());
        }
        Category category = null;
        if (productDTO.categoryId() != null) {
            category = categoryRepo.getReferenceById(productDTO.categoryId());
        }
        product.setCategory(category);
        repo.save(product);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void incPosition(Long id) {
        Product mover = findById(id);
        Product moved = null;

        if (mover.getId() == null || mover.getPosition() == 0) return;
        for (Product it : findAll()) {
            if (it.getPosition() == mover.getPosition() - 1) {
                moved = it;
            }
        }

        mover.setPosition(mover.getPosition() - 1);
        repo.save(mover);

        if (moved != null) {
            moved.setPosition(moved.getPosition() + 1);
            repo.save(moved);
        }
    }
    
}
