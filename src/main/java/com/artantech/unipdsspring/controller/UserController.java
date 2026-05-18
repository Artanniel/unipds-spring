package com.artantech.unipdsspring.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.artantech.unipdsspring.model.User;
import com.artantech.unipdsspring.security.MyToken;
import com.artantech.unipdsspring.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        System.out.println("Saving user: " + user);
        return service.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        System.out.println("Getting all users");
        return service.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<MyToken> loginUser(@RequestBody User user) {
        System.out.println("Logging in user: " + user);
        return ResponseEntity.ok(service.loginUser(user));
    }
}
