package com.two4h.two4h.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    public String addOrder(Order order) {
        if(ordersRepository.existsById(order.getId())) {
            return "Order already exists";
        }

        Order newOrder = new Order(order.getUser(), order.getOrderDate(), order.getOrderStatus(), order.getOrderTotal(), order.getProducts());
        ordersRepository.save(newOrder);
        return "Order added";
    }

    public List<Order> getAllOrders() { return ordersRepository.findAll(); }
}
