package com.example.test.controller;
import com.example.test.models.Product;
import com.example.test.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin/product/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "post_views/product_add";
    }

    @PostMapping("/admin/product/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/";
    }
    @GetMapping("/pizzas")
    public String showPizzas(Model model) {
        model.addAttribute("products", productService.getAll());
        return "pizzas";
    }

}
