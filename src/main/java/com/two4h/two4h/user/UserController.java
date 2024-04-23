package com.two4h.two4h.user;

import com.two4h.two4h.registration.Login;
import com.two4h.two4h.registration.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveRegisterData(@RequestBody User user){
        System.out.println("CONTROLER:\n" + user.getId() + "\n" + user.getFirstName()+ "\n" + user.getLastName()+ "\n" + user.getBirthDate()+ "\n" + user.getEmail()+ "\n" + user.getPassword());
        String id = userService.registerUser(user);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> userLogin(@RequestBody Login login){
        LoginResponse loginResponse = userService.userLogin(login);
        return ResponseEntity.ok(loginResponse);
    }
}
