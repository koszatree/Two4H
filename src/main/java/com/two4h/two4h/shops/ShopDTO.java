package com.two4h.two4h.shops;

import com.two4h.two4h.orders.OrderDTO;
import com.two4h.two4h.products.Product;
import com.two4h.two4h.products.ProductDTO;
import com.two4h.two4h.user.User;
import com.two4h.two4h.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShopDTO {
    private int id;
    private String shopName;
    private int ownerId;
    private double latitude;
    private double longitude;
    private Set<ProductDTO> products;
    private boolean isActive;

    public boolean getIsActive(){
        return isActive;
    }

    public static ShopDTO fromEntity(Shop shop) {
        ShopDTO dto = new ShopDTO();
        dto.setId(shop.getId());
        dto.setShopName(shop.getShopName());
        dto.setOwnerId(shop.getOwner() != null ? shop.getOwner().getId() : null);
        dto.setLatitude(shop.getLatitude());
        dto.setLongitude(shop.getLongtude());
        dto.setIsActive(shop.getIsActive());

        // Convert Set<Product> to Set<ProductDTO>
        dto.setProducts(shop.getProducts().stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toSet()));
        return dto;
    }

    private void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Shop toEntity(User owner) {
        Shop shop = new Shop();
        shop.setId(this.id);
        shop.setShopName(this.shopName);
        shop.setLatitude(this.latitude);
        shop.setLongtude(this.longitude);
        shop.setActive(this.isActive);
        shop.setOwner(owner);

        Set<Product> productEntities = this.products.stream()
                .map(productDTO -> productDTO.toEntity(shop))
                .collect(Collectors.toSet());
        shop.setProducts(productEntities);

        return shop;
    }
}
