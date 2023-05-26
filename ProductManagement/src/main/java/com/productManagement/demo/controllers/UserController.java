package com.productManagement.demo.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productManagement.demo.entity.*;
import com.productManagement.demo.repository.CartRepository;
import com.productManagement.demo.repository.ProductRepository;
import com.productManagement.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.productManagement.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;




    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user, HttpServletRequest request,
                                        HttpServletResponse response) {
    	if (user != null && user.getId() != null) {
            User userEntity = userService.getUserById(user.getId());
            userEntity.setId(user.getId());
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setActive(user.getActive());
            userEntity.setAddress(user.getAddress());
            userEntity.setCountry(user.getCountry());
            userEntity.setEmail(user.getEmail());
            userEntity.setLocation(user.getLocation());
            userEntity.setPassword(user.getPassword());
            userEntity.setPhone(user.getPhone());
            userEntity.setPincode(user.getPincode());
            userEntity.setPopularSearches(user.getPopularSearches());
            userEntity.setRecentSearches(user.getRecentSearches());
           // userEntity.setRoles(user.getRoles());
            userEntity.setProduct(user.getProduct());

            User result = userService.saveProduct(userEntity);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else if (user != null && user.getId() == null) {
            User userEntity = new User();
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setActive(user.getActive());
            userEntity.setAddress(user.getAddress());
            userEntity.setCountry(user.getCountry());
            userEntity.setEmail(user.getEmail());
            userEntity.setLocation(user.getLocation());
            userEntity.setPassword(user.getPassword());
            userEntity.setPhone(user.getPhone());
            userEntity.setPincode(user.getPincode());
            userEntity.setPopularSearches(user.getPopularSearches());
            userEntity.setRecentSearches(user.getRecentSearches());
           // userEntity.setRoles(user.getRoles());
            userEntity.setProduct(user.getProduct());
            User newUser = userService.saveProduct(userEntity);

            return ResponseEntity.status(HttpStatus.OK).body(newUser);

        } else {
            return ResponseEntity.badRequest().body("User Details Are missing");
        }
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> checkOutOrders(@RequestBody User user, HttpServletRequest request,
                                            HttpServletResponse response) {
        User result = userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<User> user = userService.findAll();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/findUser/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        Optional<User> user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/distinctPhone/{phone}")
    public ResponseEntity<?> distinctPhone(HttpServletRequest request, HttpServletResponse response,
                                           @PathVariable String phone) {
        List<User> users = userService.findDistinctByPhone(phone);
        if (users != null && users.size() != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }
    }

    @GetMapping("/distinctEmail/{email}")
    public ResponseEntity<?> distinctEmail(@PathVariable String email, HttpServletRequest request, HttpServletResponse response) {
        List<User> user = userService.findDistinctByEmail(email);
        if (user != null && user.size() != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(false);
        }
    }

    @GetMapping("/getUserDetails/{username}")
    public ResponseEntity<?> getUserDetails(@PathVariable String username, HttpServletRequest request,
                                            HttpServletResponse response) {
        List<User> user = userService.getUserDetails(username);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestParam String email, @RequestParam String currentPassword, @RequestParam String newPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return ResponseEntity.badRequest().body("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok("Password updated successfully");
    }

}


