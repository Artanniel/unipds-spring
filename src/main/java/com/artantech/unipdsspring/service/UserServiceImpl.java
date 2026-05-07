package com.artantech.unipdsspring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.artantech.unipdsspring.events.NotFoundException;
import com.artantech.unipdsspring.model.User;
import com.artantech.unipdsspring.repository.UserRepo;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User createUser(User user) {
        return repo.save(user);
    }

    @Override
    public User getUserById(int id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User getByEmail(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new NotFoundException("User " + email + " not found"));
    }
}
