package com.two4h.two4h.shops;

import com.two4h.two4h.products.Product;
import com.two4h.two4h.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private User owner;

    private double latitude;
    private double longtude;

    @OneToMany(mappedBy = "shop")
    private Set<Product> products; // Wielostronna relacja z Product

    public Shop(String shopName, User owner,Set<Product> products, double latitude, double longtude) {
        this.shopName = shopName;
        this.owner = owner;
        this.products = products;
        this.latitude = latitude;
        this.longtude = longtude;
    }
}

