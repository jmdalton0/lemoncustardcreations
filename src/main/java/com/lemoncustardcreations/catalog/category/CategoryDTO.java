package com.lemoncustardcreations.catalog.category;

import java.util.List;

import com.lemoncustardcreations.catalog.image.ImageDTO;

public record CategoryDTO(
    Long id,
    String name,
    String description,
    String details,
    List<ImageDTO> images
) {}
