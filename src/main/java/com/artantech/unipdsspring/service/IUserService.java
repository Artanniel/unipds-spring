package com.artantech.unipdsspring.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.artantech.unipdsspring.model.User;
import com.artantech.unipdsspring.security.MyToken;

public interface IUserService {

    User createUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    User getByEmail(String email);

    public MyToken loginUser(User user);

}
