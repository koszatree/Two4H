package com.two4h.two4h.products;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public String addProduct(Product product) {
        if(productRepository.findByProductName(product.getProductName()).isPresent()){
            return "Product Already Exists";
        }
        Product newProduct = new Product(product.getProductName(), product.getProductDescription(), product.getPrice(), product.getStock(), null, true);
        productRepository.save(newProduct);
        return "Product added successfully";
    }

    public String editProduct(Product product) {
        Product productToCheck = productRepository.findByProductName(product.getProductName()).orElse(null);
        assert productToCheck != null;
        if(productToCheck.getProductName().equals(product.getProductName()) && productToCheck.getId() != (product.getId())){
            return "This Product Already Exists";
        }

        Product productToEdit = productRepository.findById(product.getId()).get();
        productToEdit.setProductName(product.getProductName());
        productToEdit.setProductDescription(product.getProductDescription());
        productToEdit.setPrice(product.getPrice());
        productToEdit.setStock(product.getStock());
        productToEdit.setShop(product.getShop());
        productToEdit.setActive(product.getIsActive());

        productRepository.save(productToEdit);

        return "Product edited successfully";
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public List<Product> getActiveProducts() {
        return this.productRepository.findAllByIsActive(true);
    }
}
