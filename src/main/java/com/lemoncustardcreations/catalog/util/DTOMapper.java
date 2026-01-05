package com.lemoncustardcreations.catalog.util;

import java.util.List;

import com.lemoncustardcreations.catalog.category.Category;
import com.lemoncustardcreations.catalog.category.CategoryDTO;
import com.lemoncustardcreations.catalog.image.Image;
import com.lemoncustardcreations.catalog.image.ImageDTO;
import com.lemoncustardcreations.catalog.product.Product;
import com.lemoncustardcreations.catalog.product.ProductDTO;

public class DTOMapper {

    public static CategoryDTO categoryDTO(Category category) {
        if (category == null) return null;

        List<ImageDTO> images = category.getImages()
            .stream()
            .map(i -> new ImageDTO(i.getId(), i.getUrl()))
            .toList();

        return new CategoryDTO(
            category.getId(),
            category.getName(),
            category.getDescription(),
            category.getDetails(),
            category.getPosition(),
            images
        );
    }

    public static ProductDTO productDTO(Product product) {
        if (product == null) return null;

        List<ImageDTO> images = product.getImages()
            .stream()
            .map(i -> new ImageDTO(i.getId(), i.getUrl()))
            .toList();

        Long categoryId = product.getCategory() == null ? null : product.getCategory().getId();

        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getDescription(),
            product.getPosition(),
            categoryId,
            images
        );
    }

    public static ImageDTO imageDTO(Image image) {
        if (image == null) return null;

        return new ImageDTO(
            image.getId(),
            image.getUrl()
        );
    }

}
