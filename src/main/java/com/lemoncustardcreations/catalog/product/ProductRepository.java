package com.lemoncustardcreations.catalog.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @Query("SELECT p FROM Product p ORDER BY p.position ASC")
    List<Product> findAll();

    List<Product> findByCategoryIdOrderByPosition(Long id);
    
}
