package com.two4h.two4h.shops;

import com.two4h.two4h.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;

    private String shopName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    private double latitude;
    private double longtude;

    public Shop(Long shopId, String shopName, User owner, double latitude, double longtude) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.owner = owner;
        this.latitude = latitude;
        this.longtude = longtude;
    }
}

