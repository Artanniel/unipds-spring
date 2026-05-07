package com.artantech.unipdsspring.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.artantech.unipdsspring.model.Session;

public interface SessionRepo extends ListCrudRepository<Session, Integer> {

}
