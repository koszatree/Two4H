package com.two4h.two4h.user;

import com.two4h.two4h.registration.Login;
import com.two4h.two4h.registration.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveRegisterData(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> userLogin(@RequestBody Login login){
        LoginResponse loginResponse = userService.userLogin(login);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping(path = "/userData")
    public List<UserDTO> getUserData() {
        return userService.displayAllUsers();
    }

    @GetMapping("/activeUsersData")
    public List<UserDTO> getActiveUsersData() {
        return userService.getActiveUsers();
    }

    @GetMapping("/customersData")
    public List<UserDTO> getCustomersData() {
        return userService.getCustomers();
    }

    @GetMapping("/sellersData")
    public List<UserDTO> getSellersData() {
        return userService.getSellers();
    }

    @GetMapping(path = "/userByIdData")
    @ResponseBody
    public Optional<UserDTO> getUserById(@RequestParam("id") int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/edit")
    public String updateUserData(@RequestParam("id") int id, @RequestBody UserDTO user){
        return userService.editUser(id, user);
    }
}
