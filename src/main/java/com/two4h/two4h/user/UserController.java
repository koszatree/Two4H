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
    public List<User> getUserData() {
        return userService.displayAllUsers();
    }

    @GetMapping(path = "/userByIdData")
    @ResponseBody
    public Optional<User> getUserById(@RequestParam("id") int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/edit")
    public String updateUserData(@RequestParam("id") int id, @RequestBody User user){
        return userService.editUser(id, user);
    }
}
