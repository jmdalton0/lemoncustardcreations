package com.lemoncustardcreations.catalog.category;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lemoncustardcreations.catalog.util.DTOMapper;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryDTO> index() {
        return service.findAll()
            .stream()
            .map(DTOMapper::categoryDTO)
            .toList();
    }

    @PostMapping
    public void create(@RequestBody CategoryDTO category) {
        service.save(category);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CategoryDTO category) {
        service.save(category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}/position")
    public void patchPosition(@PathVariable Long id) {
        service.incPosition(id);
    }

}
