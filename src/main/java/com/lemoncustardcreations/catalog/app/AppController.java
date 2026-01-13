package com.lemoncustardcreations.catalog.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lemoncustardcreations.catalog.category.CategoryService;
import com.lemoncustardcreations.catalog.product.ProductService;

@Controller
@RequestMapping
public class AppController {

    private final CategoryService categoryService;

    private final ProductService productService;

    public AppController(
        ProductService productService,
        CategoryService categoryService
    ) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/categories")
    public String products(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findAll());
        return "categories";
    }

    @GetMapping("/categories/{id}")
    public String category(@PathVariable Long id, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("products", productService.findByCategoryId(id));
        return "category";
    }

    @GetMapping("/products/{id}")
    public String product(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/purchase")
    public String purchase() {
        return "purchase";
    }

    @GetMapping("/longarm")
    public String longarm() {
        return "longarm";
    }

    @GetMapping("/care")
    public String care() {
        return "care";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}
