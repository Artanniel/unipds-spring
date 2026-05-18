package com.artantech.unipdsspring.service;

import java.util.List;

import com.artantech.unipdsspring.model.Session;
import com.artantech.unipdsspring.model.Subscription;
import com.artantech.unipdsspring.model.User;

public interface ISubscriptionService {
    public Subscription addSubscription(Subscription subscription);

    public List<Subscription> getAllByUser(User user);

    public List<Subscription> getAllBySession(Session session);

}
