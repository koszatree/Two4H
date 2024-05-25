package com.two4h.two4h.products;

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

    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "id")
    private Shop shop;

    public Product(String productName, String productDescription, double price, int stock, Shop shop, boolean isActive) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.stock = stock;
        this.shop = shop;
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return isActive;
    }
}

