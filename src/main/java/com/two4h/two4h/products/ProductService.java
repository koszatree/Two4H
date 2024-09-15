package com.two4h.two4h.products;

import com.two4h.two4h.shops.Shop;
import com.two4h.two4h.shops.ShopService;
import com.two4h.two4h.shops.ShopsRepository;
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
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopsRepository shopsRepository;

    public String addProduct(Product product) {
        if(productRepository.existsByProductName(product.getProductName())){
            return "Product Already Exists";
        }
        Product newProduct = new Product(product.getProductName(), product.getProductDescription(), product.getPrice(), product.getStock(), product.getImage(), null, true);
        productRepository.save(newProduct);
        return "Product added successfully";
    }

    public String editProduct(int id, ProductDTO productDTO) {
        if (productRepository.existsById(id)) {
            // Check if a product with the same name already exists, except for the one being updated
            Product existingProduct = productRepository.findByProductName(productDTO.getProductName());

            if (existingProduct != null && existingProduct.getId() != id) {
                return "This Product Already Exists";
            }

            // Fetch the product to be edited
            Product productToEdit = productRepository.findById(id).get();

            // Update the product with the details from the DTO
            productToEdit.setProductName(productDTO.getProductName());
            productToEdit.setProductDescription(productDTO.getProductDescription());
            productToEdit.setPrice(productDTO.getPrice());
            productToEdit.setStock(productDTO.getStock());

            // Update image only if it's not null and different
            if (productDTO.getImage() != null && !Objects.equals(productDTO.getImage(), productToEdit.getImage())) {
                productToEdit.setImage(productDTO.getImage());
            }

            // Assuming you have a way to get the Shop entity from shopId
//            Shop shop = shopsRepository.findById(productDTO.getShopId())
//                    .orElseThrow(() -> new RuntimeException("Shop not found with id: " + productDTO.getShopId()));
//            productToEdit.setShop(shop);

            // Update the active status
            productToEdit.setActive(productDTO.getIsActive());

            // Save the updated product
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
