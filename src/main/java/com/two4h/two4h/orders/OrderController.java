package com.two4h.two4h.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(path = "addOrder")
    public String addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @GetMapping(path = "getAllOrders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(path = "getOrderById")
    public OrderDTO getOrderById(@RequestParam("id") int id) {
        return orderService.getOrderById(id);
    }
}
