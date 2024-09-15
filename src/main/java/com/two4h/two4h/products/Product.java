package com.two4h.two4h.products;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.two4h.two4h.shops.Shop;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    private String productDescription;

    private double price;

    private int stock;

    @Lob
    private String image;

    @ManyToOne
    @JoinColumn(name = "shop_id")
//    @JsonBackReference("shop-products")
    private Shop shop;

    private boolean isActive;

    public Product(String productName, String productDescription, double price, int stock, String image, Shop shop, boolean isActive) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.shop = shop;
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return isActive;
    }
}

