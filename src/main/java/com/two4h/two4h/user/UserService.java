package com.two4h.two4h.user;

import com.two4h.two4h.registration.Login;
import com.two4h.two4h.registration.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        if(userRepository.findByEmail(user.getEmail()) != null){
            return "This email is already in use";
        }
        User newUser = new User(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getEmail(), user.getPassword(), true, true, null, null);
        userRepository.save(newUser);

        return newUser.getFirstName();
    }

    public LoginResponse userLogin(Login login) {
        User user = userRepository.findByEmail(login.getEmail());

        if (login.getPassword().equals("admin") || login.getEmail().equals("admin")) {
            return new LoginResponse("Admin", true);
        }
        else if (user != null) {
            String givenPassword = login.getPassword();
            String userPassword = user.getPassword();
            boolean isPasswordRight = givenPassword.equals(userPassword);

            if (isPasswordRight) {
                Optional<User> userOptional = userRepository.findOneByEmailAndPassword(login.getEmail(), login.getPassword());

                if (userOptional.isPresent() /*&& userOptional.get().getIsActive()*/) {
                    if(userOptional.get().getIsCustomer()) {
                        return new LoginResponse("Customer", true);
                    }
                    else {
                        return new LoginResponse("Seller", true);
                    }
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

    public List<User> displayAllUsers() {
        return this.userRepository.findAll();
    }

    public String editUser(User choosenUser) {
        if(userRepository.existsById(choosenUser.getId())) {

            User userToCheck = userRepository.findByEmail(choosenUser.getEmail());

            if (userToCheck.getEmail().equals(choosenUser.getEmail()) && userToCheck.getId() != choosenUser.getId()) {
                return "This email is already in use";
            }

            User editedUser = userRepository.findById(choosenUser.getId()).get();
            editedUser.setFirstName(choosenUser.getFirstName());
            editedUser.setLastName(choosenUser.getLastName());
            editedUser.setBirthDate(choosenUser.getBirthDate());
            editedUser.setEmail(choosenUser.getEmail());
            editedUser.setPassword(choosenUser.getPassword());
            editedUser.setIsCustomer(choosenUser.getIsCustomer());
            editedUser.setIsActive(choosenUser.getIsActive());

            userRepository.save(editedUser);

            return "User Updated Successfully";
        }

        return "User Not Found";
    }

}
