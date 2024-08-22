package com.example.demo.Controller;

import java.util.*;


import com.example.demo.UserDTO;
import com.example.demo.UserService;
import com.example.demo.repo.UserRepo;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.repo.UserRepo.users;


@RestController
public class UserController {

    @Autowired
    private UserRepo userService;

//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody User users) {
//        String email = users.getEmail();
//        String name = users.getName();
//        String password = users.getPassword();
//
//        // Check if the user already exists
//        if (userService.findByEmail(email) != null) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden, Account already exists");
//        } else {
//            userService.save(users);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Account Creation Successful");
//        }
//
//    }
@PostMapping("/signup")
public ResponseEntity<?> signup(@RequestBody User user) {
    String email = user.getEmail();

    // Check if the user already exists
    if (userService.findByEmail(email) != null) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Forbidden");
        response.put("message", "Account already exists");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    } else {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account Creation Successful");
    }
}
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User users) {
//        String email = users.getEmail();
//        String password = users.getPassword();
//
//        // Check if the user exists
//        User user = userService.findByEmail(email);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
//        }
//
//        // Check if the password is correct
//        if (!user.getPassword().equals(password)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username/Password Incorrect");
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body("Login Successful");
//    }
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User users) {
    String email = users.getEmail();
    String password = users.getPassword();

    // Check if the user exists
    User user = userService.findByEmail(email);
    if (user == null) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "User does not exist");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Check if the password is correct
    if (!user.getPassword().equals(password)) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Username/Password Incorrect");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    return ResponseEntity.status(HttpStatus.OK).body("Login Successful");
}
    @Autowired
    private UserService uService;

//
//@GetMapping("/user")
//public ResponseEntity<?> getUserDetails(@RequestParam long userID) {
//    Optional<User> userOptional = userService.findById(userID);
//    if (userOptional.isPresent()) {
//        User user = userOptional.get();
//        // Return user details if user exists
//        return ResponseEntity.ok(new UserDTO(user.getName(),user.getUserID(), user.getEmail() ));
//    } else {
//        // Return error message if user does not exist
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
//    }
//}
    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(@RequestParam long userID) {
        Optional<User> userOptional = userService.findById(userID);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Return user details if user exists
            return ResponseEntity.ok(new UserDTO(user.getName(), user.getUserID(), user.getEmail()));
        } else {
            // Return error message if user does not exist
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "User does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return uService.getUsers();
    }

}