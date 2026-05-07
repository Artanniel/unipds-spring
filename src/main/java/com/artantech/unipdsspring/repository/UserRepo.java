package com.artantech.unipdsspring.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.artantech.unipdsspring.model.User;

public interface UserRepo extends ListCrudRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
