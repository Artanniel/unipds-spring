package com.artantech.unipdsspring.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.artantech.unipdsspring.model.Session;
import com.artantech.unipdsspring.model.Subscription;
import com.artantech.unipdsspring.model.User;
import com.artantech.unipdsspring.repository.SubscriptionRepo;

@Service
public class SubscriptionServiceImpl implements ISubscriptionService {

    private SubscriptionRepo repo;

    public SubscriptionServiceImpl(SubscriptionRepo repo) {
        this.repo = repo;
    }

    @Override
    public Subscription addSubscription(Subscription subscription) {
        subscription.setUniqueID(UUID.randomUUID().toString());
        subscription.setCreatedAt(LocalDateTime.now());
        return repo.save(subscription);
    }

    @Override
    public List<Subscription> getAllByUser(User user) {
        return repo.findByUser(user);
    }

    @Override
    public List<Subscription> getAllBySession(Session session) {
        return repo.findBySession(session);
    }

}
