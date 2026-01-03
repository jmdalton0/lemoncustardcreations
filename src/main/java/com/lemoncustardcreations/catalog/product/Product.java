package com.lemoncustardcreations.catalog.product;

import java.util.ArrayList;
import java.util.List;

import com.lemoncustardcreations.catalog.category.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Transient
    private List<String> imageUrls;

    public Product() {
        this.imageUrls = new ArrayList<>();
    }

    public Product(Long id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrls = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addImageUrl(String url) {
        this.imageUrls.add(url);
    }

}
