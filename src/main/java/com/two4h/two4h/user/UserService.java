package com.two4h.two4h.user;

import com.two4h.two4h.registration.Login;
import com.two4h.two4h.registration.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        User newUser = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getEmail(), user.getPassword());

        userRepository.save(newUser);

        return newUser.getFirstName();
    }

    public LoginResponse userLogin(Login login) {
        String message = "";
        User user = userRepository.findByEmail(login.getEmail());

        if(user != null) {
            String givenPassword = login.getPassword();
            String userPassword = user.getPassword();
            Boolean isPasswordRight = givenPassword.equals(userPassword);

            if(isPasswordRight){
                Optional<User> userOptional = userRepository.findOneByEmailAndPassword(login.getEmail(), login.getPassword());
                if(userOptional.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login Failed", false);
                }
            } else {
                return new LoginResponse("Wrong Password", false);
            }
        } else {
            return new LoginResponse("Incorrect Email", false);
        }
    }
}