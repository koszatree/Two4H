package com.two4h.two4h.orders;

import com.two4h.two4h.user.User;
import jakarta.persistence.*;

import java.util.Date;

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

    public Orders(Long orderId, User user, Date orderDate, String orderStatus, double orderTotal) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
    }
}

