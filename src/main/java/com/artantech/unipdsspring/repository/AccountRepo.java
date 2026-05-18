package com.artantech.unipdsspring.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.artantech.unipdsspring.model.Account;

@Repository
public interface AccountRepo extends ListCrudRepository<Account, Integer> {

}
