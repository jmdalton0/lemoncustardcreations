package com.lemoncustardcreations.catalog.product;

import java.util.List;

import com.lemoncustardcreations.catalog.image.ImageDTO;

public record ProductDTO(
    Long id,
    String name,
    String price,
    String description,
    Integer position,
    Long categoryId,
    List<ImageDTO> images
) {}
