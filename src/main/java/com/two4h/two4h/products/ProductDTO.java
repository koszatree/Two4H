package com.two4h.two4h.products;

import com.two4h.two4h.shops.Shop;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private int id;
    private String productName;
    private String productDescription;
    private double price;
    private int stock;
    private String image; // Assuming image is a base64 encoded string
    private Integer shopId; // Only the ID of the associated shop
    private boolean isActive;

    public boolean getIsActive(){
        return isActive;
    }

    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImage(),
                product.getShop() != null ? product.getShop().getId() : null,
                product.getIsActive()
        );
    }

    public Product toEntity(Shop shop) {
        Product product = new Product();
        product.setId(this.id);
        product.setProductName(this.productName);
        product.setProductDescription(this.productDescription);
        product.setPrice(this.price);
        product.setStock(this.stock);
        product.setImage(this.image);
        product.setShop(shop); // Convert from DTO to Entity, so you need the actual Shop entity
        product.setActive(this.isActive);
        return product;
    }
}
