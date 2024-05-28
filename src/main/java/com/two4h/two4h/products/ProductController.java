package com.two4h.two4h.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(path = "/addProduct")
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping(path = "/productData")
    public List<Product> productData(){
        return productService.getAllProducts();
    }

    @GetMapping(path = "/activeProducts")
    public List<Product> activeProductData(){
        return productService.getActiveProducts();
    }

    @PutMapping(path = "/editProduct")
    public String editProduct(@RequestBody Product product) {
        return productService.editProduct(product);
    }
}
