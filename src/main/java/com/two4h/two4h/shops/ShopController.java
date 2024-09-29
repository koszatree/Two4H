package com.two4h.two4h.shops;

import com.two4h.two4h.products.Product;
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
    public List<ShopDTO> shopsData() {
        return shopService.getAllShops();
    }

    @GetMapping(path = "/shopByIdData")
    public ShopDTO shopByIdData(@RequestParam("id") int id) {
        return shopService.getShopById(id);
    }

    @GetMapping(path = "/shopsByOwner")
    public List<ShopDTO> shopsByOwner(@RequestParam("id") int ownerId) {
        return shopService.getShopsByOwner(ownerId);
    }

    @GetMapping(path = "/activeShops")
    public List<ShopDTO> activeShopsData() {
        return shopService.getActiveShops();
    }

    @GetMapping(path = "/productsFromShop")
    public List<ProductDTO> productsFromShop(@RequestParam("id") int id) {
        return shopService.getProductsFromShop(id);
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

    @PutMapping(path = "/addProductToShop")
    public ResponseEntity<String> addProductToShop(@RequestParam("id") int id, @RequestBody ProductDTO products) {
        String result = shopService.addProductToShop(id, products);
        if(result.equals("Product added successfully!")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

    }

}
