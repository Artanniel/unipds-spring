package com.artantech.unipdsspring.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.artantech.unipdsspring.model.Session;
import com.artantech.unipdsspring.model.Subscription;
import com.artantech.unipdsspring.model.SubscriptionID;
import com.artantech.unipdsspring.model.User;

public interface SubscriptionRepo extends ListCrudRepository<Subscription, SubscriptionID> {
    public List<Subscription> findByUser(User user);

    public List<Subscription> findBySession(Session session);
}
