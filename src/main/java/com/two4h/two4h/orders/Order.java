package com.two4h.two4h.orders;

import com.two4h.two4h.products.Product;
import com.two4h.two4h.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date orderDate;

    private String orderStatus;

    private double orderTotal;

    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;

    public Order(Long orderId, User user, Date orderDate, String orderStatus, double orderTotal, Set<Product> products) {
        this.id = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
        this.products = products;

    }
}
