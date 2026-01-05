package com.lemoncustardcreations.catalog.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    @Query("SELECT c FROM Category c ORDER BY c.position ASC")
    List<Category> findAll();
    
}
