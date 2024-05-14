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
    private Long productId;

    private String productName;

    private String productDescription;

    private double price;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    public Product(Long productId, String productName, String productDescription, double price, int stock, Shop shop) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.stock = stock;
        this.shop = shop;
    }
}

