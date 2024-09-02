package com.two4h.two4h.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
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

    @GetMapping(path = "/productByIdData")
    public Product productByIdData(@RequestParam("id") int id){
        return productService.getProductById(id);
    }

    @GetMapping(path="/getProductImage")
    public ResponseEntity<Resource> getImage(@RequestParam("id") int id) throws MalformedURLException {
        return productService.getImage(id);
    }

    @PutMapping(path = "/editProduct")
    public String editProduct(@RequestParam("id") int id, @RequestBody Product product) {
        return productService.editProduct(id, product);
    }
}
