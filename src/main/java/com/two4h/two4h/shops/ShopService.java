package com.two4h.two4h.shops;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ShopService {
    private ShopsRepository shopsRepository;

    public String addShop(Shop shop) {
        if(shopsRepository.findByName(shop.getShopName()).isPresent()){
            return "Shop already exists";
        }
        Shop newShop = new Shop(shop.getShopName(), shop.getOwner(), null, shop.getLatitude(), shop.getLongtude());
        shopsRepository.save(newShop);

        return "Shop added successfully";
    }

    public List<Shop> getAllShops() {
        return this.shopsRepository.findAll();
    }

    public String shopEdit(Shop shop) {
        Shop shopToCheck = shopsRepository.findByName(shop.getShopName()).orElse(null);
        assert shopToCheck != null;
        if(shopToCheck.getShopName().equals(shop.getShopName()) && shopToCheck.getId() != (shop.getId())){
            return "Shop already exists";
        }

        Shop shopToEdit = shopsRepository.findById(shop.getId()).get();
        shopToEdit.setShopName(shop.getShopName());
        shopToEdit.setOwner(shop.getOwner());
        shopToEdit.setProducts(shop.getProducts());
        shopToEdit.setLatitude(shop.getLatitude());
        shopToEdit.setLongtude(shop.getLongtude());
        shopsRepository.save(shopToEdit);

        return "Shop edited successfully";
    }

    public List<Shop> getActiveShops() {
        return this.shopsRepository.findAllBiIsActive(true);
    }
}
