package com.artantech.unipdsspring.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.artantech.unipdsspring.events.NotFoundException;
import com.artantech.unipdsspring.model.User;
import com.artantech.unipdsspring.repository.UserRepo;
import com.artantech.unipdsspring.security.MyToken;
import com.artantech.unipdsspring.security.TokenUtil;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User createUser(User user) {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            if (user.getEmail() != null && !user.getEmail().isBlank()) {
                user.setUsername(user.getEmail());
            } else {
                user.setUsername(user.getName().toLowerCase().replace(" ", ""));
            }
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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

    @Override
    public MyToken loginUser(User user) {
        User storedUser = repo.findByEmail(user.getEmail())
                .orElseThrow(() -> new NotFoundException("User " + user.getEmail() + " not found"));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (bCryptPasswordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
            return new MyToken(TokenUtil.encode(storedUser));
        }
        throw new NotFoundException("Invalid password");
    }
}
