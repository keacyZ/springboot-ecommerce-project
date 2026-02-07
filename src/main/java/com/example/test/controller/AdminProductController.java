package com.example.test.controller;
import com.example.test.models.Product;
import com.example.test.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;
    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "admin/admin_products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/admin_product_add";
    }

    @PostMapping("/add")
    public String saveProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/admin/products";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/products";
        }

        @GetMapping("/edit/{id}")
        public String editProduct(@PathVariable Long id, Model model) {
            model.addAttribute("product", productService.findById(id));
            return "admin/admin_product_add";
        }

        @PostMapping("/update")
        public String updateProduct(Product product) {
            productService.save(product);
            return "redirect:/admin/products";
        }

    }

