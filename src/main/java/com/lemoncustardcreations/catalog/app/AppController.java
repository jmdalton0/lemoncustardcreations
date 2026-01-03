package com.lemoncustardcreations.catalog.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemoncustardcreations.catalog.product.ProductService;

@Controller
@RequestMapping
public class AppController {

    private final ProductService productService;

    public AppController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
