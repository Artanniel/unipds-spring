package com.artantech.unipdsspring.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.artantech.unipdsspring.model.Conference;

@Repository
public interface ConferenceRepo extends ListCrudRepository<Conference, Integer> {

}
