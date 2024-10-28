package com.two4h.two4h.orders;

import com.two4h.two4h.products.Product;
import com.two4h.two4h.products.ProductDTO;
import com.two4h.two4h.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private int id;
    private int userId; // Reference to the User's ID instead of the User object
    private Date orderDate;
    private String orderStatus;
    private double orderTotal;
    private Set<ProductDTO> products; // Reference to the ProductDTO instead of the Product entity

    public static OrderDTO fromEntity(Order order) {
        Set<ProductDTO> productDTOs = order.getProducts().stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toSet());

        return new OrderDTO(
                order.getId(),
                order.getUser().getId(), // Convert User to userId
                order.getOrderDate(),
                order.getOrderStatus(),
                order.getOrderTotal(),
                productDTOs
        );
    }

    // Convert OrderDTO to Order entity
    public static Order toEntity(OrderDTO orderDTO, User user, Set<Product> products) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUser(user); // Assign User entity
        order.setOrderDate(orderDTO.getOrderDate());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setOrderTotal(orderDTO.getOrderTotal());
        order.setProducts(products); // Assign Product entities
        return order;
    }
}
