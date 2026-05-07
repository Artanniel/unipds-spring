package com.artantech.unipdsspring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.artantech.unipdsspring.events.NotFoundException;
import com.artantech.unipdsspring.model.Session;
import com.artantech.unipdsspring.repository.SessionRepo;

@Service
public class SessionServiceImpl implements ISessionService {

    private SessionRepo repo;

    public SessionServiceImpl(SessionRepo repo) {
        this.repo = repo;
    }

    @Override
    public Session createSession(Session session) {
        return repo.save(session);
    }

    @Override
    public Session getSessionById(int id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Session " + id + " not found"));
    }

    @Override
    public List<Session> getAllSessions() {
        return (List<Session>) repo.findAll();
    }

    @Override
    public void deleteSession(int id) {
        repo.deleteById(id);
    }

    @Override
    public Session updateSession(Session session) {
        return repo.save(session);
    }

}
