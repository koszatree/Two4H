package com.two4h.two4h.user;

import com.two4h.two4h.registration.Login;
import com.two4h.two4h.registration.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveRegisterData(@RequestBody User user){
        String id = userService.registerUser(user);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> userLogin(@RequestBody Login login){
        LoginResponse loginResponse = userService.userLogin(login);
        return ResponseEntity.ok(loginResponse);
    }
}
