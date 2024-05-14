package com.two4h.two4h.products;

import com.two4h.two4h.shops.Shops;
import jakarta.persistence.*;

@Entity
@Table(name = "Products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String productDescription;

    private double price;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shops shop;

    public Products(Long productId, String productName, String productDescription, double price, int stock, Shops shop) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.stock = stock;
        this.shop = shop;
    }
}

