package com.lemoncustardcreations.catalog.product;

import java.util.ArrayList;
import java.util.List;

import com.lemoncustardcreations.catalog.category.Category;
import com.lemoncustardcreations.catalog.image.Image;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String price;

    private String description;

    private Integer position;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    public Product() {
        this.id = 0L;
        this.name = "Product Does Not Exist";
        this.price = "";
        this.description = "";
        this.position = null;
        this.images = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public Integer getPosition() {
        return position;
    }

    public Category getCategory() {
        return category;
    }

    public List<Image> getImages() {
        return images;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
