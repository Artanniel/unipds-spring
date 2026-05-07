package com.artantech.unipdsspring.service;

import java.util.List;

import com.artantech.unipdsspring.model.User;

public interface IUserService {

    User createUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    User getByEmail(String email);
}
