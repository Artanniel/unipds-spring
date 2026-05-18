package com.artantech.unipdsspring.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.artantech.unipdsspring.model.Transaction;

@Repository
public interface TransactionRepo extends ListCrudRepository<Transaction, Integer> {

}
