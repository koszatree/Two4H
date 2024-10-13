package com.two4h.two4h.shops;

import com.two4h.two4h.products.Product;
import com.two4h.two4h.products.ProductDTO;
import com.two4h.two4h.products.ProductRepository;
import com.two4h.two4h.user.User;
import com.two4h.two4h.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShopService {
    @Autowired
    private ShopsRepository shopsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public String addShop(ShopDTO shopDTO) {
        // Check if shop already exists
        if(shopsRepository.findByShopName(shopDTO.getShopName()).isPresent()) {
            return "Shop already exists";
        }

        // Retrieve the owner from the repository
        User owner = userRepository.findById(shopDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        // Create a new shop entity
        Shop newShop = new Shop(
                shopDTO.getShopName(),
                owner,
                null,  // We will set the products later
                shopDTO.getLatitude(),
                shopDTO.getLongitude(),
                true
        );

        // Save the shop first to generate an ID for the shop
        Shop savedShop = shopsRepository.save(newShop);

        // Handle products if they exist in the DTO
        if (shopDTO.getProducts() != null && !shopDTO.getProducts().isEmpty()) {
            Set<Product> products = new HashSet<>();
            for (ProductDTO productDTO : shopDTO.getProducts()) {
                // Convert the ProductDTO into a Product entity
                Product product = productRepository.findById(productDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                // Set the shop for the product
                product.setShop(savedShop);
                products.add(product);
            }

            // Set the products for the new shop
            newShop.setProducts(products);

            // Save the shop again with the associated products
            shopsRepository.save(newShop);
        }

        return "Shop added successfully";
    }

    public List<ShopDTO> getAllShops() {
        return this.shopsRepository.findAll()
                .stream()
                .map(ShopDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsFromShop(int id) {
        return productRepository.findAllByShop(id)
                .stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ShopDTO getShopById(int id) { return ShopDTO.fromEntity(shopsRepository.findById(id).get()); }

    public List<ShopDTO> getShopsByOwner(int ownerId) {
        User owner = userRepository.findById(ownerId).get();
        List<Shop> shopsByOwner = shopsRepository.findAllByOwner(owner);
        List<Shop> shops = new ArrayList<>();

        for(Shop shop : shopsByOwner){
            if(shop.getIsActive()){
                shops.add(shop);
            }
        }

        return shops
                .stream()
                .map(ShopDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public String shopEdit(int shopId, ShopDTO shopDTO) {
        try {
            // Find the existing shop entity by ID
            Shop shopToEdit = shopsRepository.findById(shopId)
                    .orElseThrow(() -> new RuntimeException("Shop not found with id: " + shopId));

            // Find the owner user entity by ownerId from the DTO
            User owner = userRepository.findById(shopDTO.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + shopDTO.getOwnerId()));

            // Update the shop entity fields with the data from the DTO
            shopToEdit.setShopName(shopDTO.getShopName());
            shopToEdit.setLatitude(shopDTO.getLatitude());
            shopToEdit.setLongtude(shopDTO.getLongitude());
            shopToEdit.setActive(shopDTO.getIsActive());
            shopToEdit.setOwner(owner); // Set the owner after fetching from the repository

            // Save the updated shop entity back to the database
            shopsRepository.save(shopToEdit);

            // Return success message
            return "Shop updated successfully!";
        } catch (Exception e) {
            // Return error message
            return "Failed to update shop: " + e.getMessage();
        }
    }

    // PROBLEM: All products has one shopId, every time we add specific product to the shop it is deleted from another.
    // FIX IDEA: Add new product entity to the database, containing id of the shop.
    // IDEA 2: Separate product entity for products in shop (solution bypasses problem with adding products - elements from all shops "doubled" which causes a transparency problem)
    // IDEA 3: Adding method to return products without shop connected (NULL shopId value) and adding this products to shop as new entity
    // Also, shop owner should be able to edit product stock (and price ?)
    // RISK: Too much common elements (the same product entities with only different shopId)
    public String addProductToShop(int shopId, ProductDTO productDTO) {
        try {
            // Retrieve the shop by ID
            Shop shop = shopsRepository.findById(shopId)
                    .orElseThrow(() -> new RuntimeException("Shop not found with id: " + shopId));

            // Create a new product entity from the DTO for this specific shop
            Product newProduct = productDTO.toEntity(shop);
            newProduct.setId(productRepository.findMaxId() + 1);

            // Save the new product in the database
            productRepository.save(newProduct);

            // Add the product to the shop's product list5
            shop.getProducts().add(newProduct);

            // Save the shop with the new product
            shopsRepository.save(shop);

            return "Product added to shop successfully!";
        } catch (Exception e) {
            return "Failed to add product to shop: " + e.getMessage();
        }
    }

    public List<ShopDTO> getActiveShops() {
        return this.shopsRepository.findAllByIsActive(true)
                .stream()
                .map(ShopDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
