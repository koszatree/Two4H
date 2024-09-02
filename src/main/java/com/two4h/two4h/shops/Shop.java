package com.two4h.two4h.shops;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.two4h.two4h.products.Product;
import com.two4h.two4h.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String shopName;
    // Relacja w jedną stronę sklep > user
    // mapped by shops
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-shops")
    @JsonIgnoreProperties("shopsOwned")
    private User owner;

    private double latitude;
    private double longtude;

    @Value("true")
    private boolean isActive;

    @OneToMany(mappedBy = "shop")
    @JsonManagedReference("shop-products")
    private Set<Product> products; // Wielostronna relacja z Product

    public Shop(String shopName, User owner, Set<Product> products, double latitude, double longitude, boolean isActive) {
        this.shopName = shopName;
        this.owner = owner;
        this.products = products;
        this.latitude = latitude;
        this.longtude = longitude;
        this.isActive = isActive;
    }
}

