package com.artantech.unipdsspring.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.artantech.unipdsspring.model.DocFiscal;

@Repository
public interface DocFiscalRepo extends ListCrudRepository<DocFiscal, Integer> {

    public Optional<DocFiscal> findByProtocolo(String protocolo);
}
