package com.two4h.two4h.shops;

import com.two4h.two4h.orders.OrderDTO;
import com.two4h.two4h.products.Product;
import com.two4h.two4h.user.User;
import com.two4h.two4h.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopDTO {
    private int id;
    private String shopName;
    private int ownerId; // Simplified to only include the owner's ID
    private double latitude;
    private double longitude;
    private boolean isActive;

    public boolean getIsActive(){
        return isActive;
    }

    public static ShopDTO fromEntity(Shop shop) {
        return new ShopDTO(
                shop.getId(),
                shop.getShopName(),
                shop.getOwner() != null ? shop.getOwner().getId() : null, // Check if owner exists before getting ID
                shop.getLatitude(),
                shop.getLongtude(),
                shop.getIsActive()
        );
    }

    public Shop toEntity(User owner) {
        Shop shop = new Shop();
        shop.setId(this.id);
        shop.setShopName(this.shopName);
        shop.setLatitude(this.latitude);
        shop.setLongtude(this.longitude);
        shop.setActive(this.isActive);
        shop.setOwner(owner); // Use the provided User entity for the owner
        return shop;
    }
}
