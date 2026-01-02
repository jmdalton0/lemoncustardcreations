package com.lemoncustardcreations.catalog.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", service.findAll());
        return "products";
    }

    @GetMapping("/create")
    public String showCreate() {
        return "products-create";
    }

    @PostMapping
    public String create(@ModelAttribute Product product) {
        service.save(product);
        return "redirect:/products";
    }
    
}
