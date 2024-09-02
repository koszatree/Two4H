package com.two4h.two4h.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public String addProduct(Product product) {
//        if(productRepository.findByProductName(product.getProductName()).isPresent()){
//            return "Product Already Exists";
//        }
        Product newProduct = new Product(product.getProductName(), product.getProductDescription(), product.getPrice(), product.getStock(), product.getImage(), null, true);
        productRepository.save(newProduct);
        return "Product added successfully";
    }

    public String editProduct(int id, Product product) {
        if (productRepository.existsById(id)) {

//            Product productToCheck = productRepository.findByProductName(product.getProductName());
//
//            if(productToCheck == null && productToCheck.getId() != (product.getId())){
//                return "This Product Already Exists";
//            }

            Product productToEdit = productRepository.findById(id).get();
            productToEdit.setProductName(product.getProductName());
            productToEdit.setProductDescription(product.getProductDescription());
            productToEdit.setPrice(product.getPrice());
            productToEdit.setStock(product.getStock());
            //productToEdit.setImage(product.getImage());
            //productToEdit.setShop(product.getShop());
            productToEdit.setActive(product.getIsActive());

            productRepository.save(productToEdit);

            return "Product edited successfully";
        }

        return "Product Not Found";
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public List<Product> getActiveProducts() {
        return this.productRepository.findAllByIsActive(true);
    }

    public Product getProductById(int id) {
        return this.productRepository.findById(id).get();
    }

    public ResponseEntity<Resource> getImage(int id) throws MalformedURLException {
        String imagePath = productRepository.findOneImageById(id);

        if (imagePath == null) {
            return ResponseEntity.notFound().build();
        }

        Path filePath = Paths.get(imagePath);
        Resource resource = new UrlResource(filePath.toUri());

        // Check if the file exists and determine the content type
        if (resource.exists() || resource.isReadable()) {
            String contentType = "image/png"; // Assuming it's a PNG, adjust if necessary

            try {
                contentType = Files.probeContentType(filePath);
            } catch (Exception e) {
                // Use default if content type could not be determined
                contentType = "image/png";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filePath.getFileName().toString() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
//        Path filePath = Paths.get(this.productRepository.findOneImageById(id));
//        Resource resource = new FileSystemResource(filePath.toFile());
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .body(resource);
    }
}
