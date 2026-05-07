package com.artantech.unipdsspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.artantech.unipdsspring.model.Session;
import com.artantech.unipdsspring.model.Subscription;
import com.artantech.unipdsspring.model.User;
import com.artantech.unipdsspring.service.ISubscriptionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class SubscriptionController {
    private ISubscriptionService service;

    public SubscriptionController(ISubscriptionService service) {
        this.service = service;
    }

    @PostMapping("/subscription")
    public Subscription addSubscription(@RequestBody Subscription subscription) {
        return service.addSubscription(subscription);
    }

    @GetMapping("/subscription/user/{userId}")
    public List<Subscription> getAllByUser(@PathVariable Integer userId) {
        User user = new User();
        user.setId(userId);
        return service.getAllByUser(user);
    }

    @GetMapping("/subscription/session/{id}")
    public List<Subscription> getAllBySession(@PathVariable Integer id) {
        Session session = new Session();
        session.setId(id);
        return service.getAllBySession(session);
    }
}
