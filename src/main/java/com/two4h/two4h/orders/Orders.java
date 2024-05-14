package com.two4h.two4h.orders;

import com.two4h.two4h.products.Products;
import com.two4h.two4h.user.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

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
    private Set<Products> products;

    public Orders(Long orderId, User user, Date orderDate, String orderStatus, double orderTotal, Set<Products> products) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
        this.products = products;
    }
}

