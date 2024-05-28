package com.two4h.two4h.shops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PostMapping(path = "/addShop")
    public String addShop(@RequestBody Shop shop) {
        return shopService.addShop(shop);
    }

    @GetMapping(path = "/shopsData")
    public List<Shop> shopsData() {
        return shopService.getAllShops();
    }

    @GetMapping(path = "/activeShops")
    public List<Shop> activeShopsData() {
        return shopService.getActiveShops();
    }

    @PutMapping(path = "/editShops")
    public String editShops(@RequestBody Shop shop) {
        return shopService.shopEdit(shop);
    }
}
