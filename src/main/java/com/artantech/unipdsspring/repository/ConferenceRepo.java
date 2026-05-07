package com.artantech.unipdsspring.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.artantech.unipdsspring.model.Conference;

public interface ConferenceRepo extends ListCrudRepository<Conference, Integer> {

}
