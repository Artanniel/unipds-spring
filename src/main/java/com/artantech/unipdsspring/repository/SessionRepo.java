package com.artantech.unipdsspring.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.artantech.unipdsspring.model.Session;

@Repository
public interface SessionRepo extends ListCrudRepository<Session, Integer> {

}
