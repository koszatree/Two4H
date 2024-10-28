package com.two4h.two4h.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<OrderDTO> getAllOrders() {
        return ordersRepository.findAll()
                .stream()
                .map(OrderDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(int id) {
        return OrderDTO.fromEntity(ordersRepository.findById(id).get());
    }
}
