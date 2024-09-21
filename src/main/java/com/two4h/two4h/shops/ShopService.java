package com.two4h.two4h.shops;

import com.two4h.two4h.products.Product;
import com.two4h.two4h.products.ProductDTO;
import com.two4h.two4h.user.User;
import com.two4h.two4h.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopService {
    @Autowired
    private ShopsRepository shopsRepository;

    @Autowired
    private UserRepository userRepository;

    public String addShop(ShopDTO shop) {
        if(shopsRepository.findByShopName(shop.getShopName()).isPresent()){
            return "Shop already exists";
        }

        Shop newShop = new Shop(shop.getShopName(), userRepository.findById(shop.getOwnerId()).get(), null, shop.getLatitude(), shop.getLongitude(), true);
        shopsRepository.save(newShop);

        return "Shop added successfully";
    }

    public List<Shop> getAllShops() {
        return this.shopsRepository.findAll();
    }

    public Shop getShopById(int id) { return shopsRepository.findById(id).get(); }

    public List<Shop> getShopsByOwner(int ownerId) {
        User owner = userRepository.findById(ownerId).get();

        return shopsRepository.findAllByOwner(owner);
    }

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

    public String addProductsToShop(int id, List<ProductDTO> products) {
        try{
            Shop shop = shopsRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Shop not found with id: " + id));
            Set<Product> productList = new Set<Product>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @Override
                public Iterator<Product> iterator() {
                    return null;
                }

                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @Override
                public <T> T[] toArray(T[] a) {
                    return null;
                }

                @Override
                public boolean add(Product product) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends Product> c) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> c) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> c) {
                    return false;
                }

                @Override
                public void clear() {

                }
            };

            for(ProductDTO product : products){
                 productList.add(product.toEntity(shop));
            }

            shop.setProducts(productList);

            return "Product added successfully!";

        } catch (Exception e) {
            return "Failed to add products to shop: " + e.getMessage();
        }
    }

    public List<Shop> getActiveShops() {
        return this.shopsRepository.findAllByIsActive(true);
    }
}
