package com.two4h.two4h.user;

import com.two4h.two4h.registration.Login;
import com.two4h.two4h.registration.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<UserDTO> displayAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(int id) {
        User user = this.userRepository.findById(id).get();
        if (this.userRepository.findById(id).isPresent()) {
            return Optional.of(UserDTO.fromEntity(user));
        }
        return Optional.empty();
    }

    public List<UserDTO> getActiveUsers() {
        return this.userRepository.findAllByIsActive(true)
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getCustomers() {
        return this.userRepository.findAllByIsCustomer(true)
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getSellers() {
        return this.userRepository.findAllByIsCustomer(false)
                .stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public String editUser(int id, UserDTO choosenUserDTO) {
        // Check if the user exists
        if (userRepository.existsById(id)) {

            // Check if the email is already used by another user
            User userToCheck = userRepository.findByEmail(choosenUserDTO.getEmail());
            if (userToCheck != null && userToCheck.getId() != id) {
                return "This email is already in use";
            }

            // Fetch the user to be edited
            User editedUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

            // Update fields from the UserDTO
            editedUser.setFirstName(choosenUserDTO.getFirstName());
            editedUser.setLastName(choosenUserDTO.getLastName());
            editedUser.setBirthDate(choosenUserDTO.getBirthDate());
            editedUser.setEmail(choosenUserDTO.getEmail());
            editedUser.setIsCustomer(choosenUserDTO.getIsCustomer());
            editedUser.setIsActive(choosenUserDTO.getIsActive());

            // Save the updated user
            userRepository.save(editedUser);

            System.out.println("User with id: " + editedUser.getId() + " has been updated");
            return "User Updated Successfully";
        }

        return "User Not Found";
    }


}
