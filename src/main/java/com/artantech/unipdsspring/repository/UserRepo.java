package com.artantech.unipdsspring.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.artantech.unipdsspring.model.User;

@Repository
public interface UserRepo extends ListCrudRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
