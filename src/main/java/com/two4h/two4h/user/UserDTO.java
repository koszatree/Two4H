package com.two4h.two4h.user;

import com.two4h.two4h.orders.Order;
import com.two4h.two4h.orders.OrderDTO;
import com.two4h.two4h.shops.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private Boolean isCustomer;
    private Set<OrderDTO> orders;
    private Boolean isActive;

    public static UserDTO fromEntity(User user) {
        Set<OrderDTO> orderDTOs = user.getOrdersPlaced().stream()
                .map(OrderDTO::convertToDto) // Use OrderConverter to convert each Order to OrderDTO
                .collect(Collectors.toSet());

        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getEmail(),
                user.getIsCustomer(),
                orderDTOs,
                user.getIsActive()
        );
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setBirthDate(this.birthDate);
        user.setEmail(this.email);
        user.setIsCustomer(this.isCustomer);
        user.setIsActive(this.isActive);
        return user;
    }
}
