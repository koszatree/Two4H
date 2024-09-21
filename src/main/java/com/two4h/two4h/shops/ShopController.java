package com.two4h.two4h.shops;

import com.two4h.two4h.products.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @PostMapping(path = "/addShop")
    public String addShop(@RequestBody ShopDTO shop) {
        return shopService.addShop(shop);
    }

    @GetMapping(path = "/shopsData")
    public List<Shop> shopsData() {
        return shopService.getAllShops();
    }

    @GetMapping(path = "/shopByIdData")
    public Shop shopByIdData(@RequestParam("id") int id) {
        return shopService.getShopById(id);
    }

    @GetMapping(path = "/shopsByOwner")
    public List<Shop> shopsByOwner(@RequestParam("id") int ownerId) {
        return shopService.getShopsByOwner(ownerId);
    }

    @GetMapping(path = "/activeShops")
    public List<Shop> activeShopsData() {
        return shopService.getActiveShops();
    }

    @PutMapping(path = "/editShop")
    public ResponseEntity<String> editShops(@RequestParam("id") int id, @RequestBody ShopDTO shop) {
        String result = shopService.shopEdit(id, shop);

        if (result.equals("Shop updated successfully!")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @PutMapping(path = "/addProductsToShop")
    public ResponseEntity<String> addProductsToShop(@RequestParam("id") int id, @RequestBody List<ProductDTO> products) {
        String result = shopService.addProductsToShop(id, products);
        if(result.equals("Product added successfully!")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

    }

}
