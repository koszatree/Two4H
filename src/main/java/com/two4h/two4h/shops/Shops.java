package com.two4h.two4h.shops;

import com.two4h.two4h.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "Shops")
public class Shops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    private String shopName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public Shops(Long shopId, String shopName, User owner) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.owner = owner;
    }
}

