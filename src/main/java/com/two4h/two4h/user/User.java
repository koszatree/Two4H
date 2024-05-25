package com.two4h.two4h.user;


import com.two4h.two4h.orders.Order;
import com.two4h.two4h.shops.Shop;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String password;
    @Value("true")
    private Boolean isCustomer;
    @Value("true")
    private Boolean isActive;

    @OneToMany(mappedBy = "user")
    private Set<Shop> shopsOwned; // Wielostronna relacja z Shop

    @OneToMany(mappedBy = "user")
    private Set<Order> ordersPlaced; // Wielostronna relacja z Order

    public User(String firstName, String lastName, Date birthDate, String email, String password, Set<Shop> shopsOwned, Set<Order> ordersPlaced) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
    }
}
