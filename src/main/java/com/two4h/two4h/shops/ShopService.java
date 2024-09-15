package com.two4h.two4h.shops;

import com.two4h.two4h.user.User;
import com.two4h.two4h.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopsRepository shopsRepository;

    @Autowired
    private UserRepository userRepository;

    public String addShop(Shop shop) {
        if(shopsRepository.findByShopName(shop.getShopName()).isPresent()){
            return "Shop already exists";
        }
        Shop newShop = new Shop(shop.getShopName(), shop.getOwner(), null, shop.getLatitude(), shop.getLongtude(), true);
        shopsRepository.save(newShop);

        return "Shop added successfully";
    }

    public List<Shop> getAllShops() {
        return this.shopsRepository.findAll();
    }

    public Shop getShopById(int id) { return shopsRepository.findById(id).get(); }

    public String shopEdit(int shopId, ShopDTO shopDTO) {
        try {
            // Find the existing shop entity by ID
            Shop shopToEdit = shopsRepository.findById(shopId)
                    .orElseThrow(() -> new RuntimeException("Shop not found with id: " + shopId));

            // Find the owner user entity by ownerId from the DTO
            User owner = userRepository.findById(shopDTO.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + shopDTO.getOwnerId()));

            // Update the shop entity fields with the data from the DTO
            shopToEdit.setShopName(shopDTO.getShopName());
            shopToEdit.setLatitude(shopDTO.getLatitude());
            shopToEdit.setLongtude(shopDTO.getLongitude());
            shopToEdit.setActive(shopDTO.getIsActive());
            shopToEdit.setOwner(owner); // Set the owner after fetching from the repository

            // Save the updated shop entity back to the database
            shopsRepository.save(shopToEdit);

            // Return success message
            return "Shop updated successfully!";
        } catch (Exception e) {
            // Return error message
            return "Failed to update shop: " + e.getMessage();
        }
    }

    public List<Shop> getActiveShops() {
        return this.shopsRepository.findAllByIsActive(true);
    }
}
