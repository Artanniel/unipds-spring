package com.artantech.unipdsspring.service;

import java.util.List;

import com.artantech.unipdsspring.model.Session;

public interface ISessionService {

    public Session createSession(Session session);

    public Session getSessionById(int id);

    public List<Session> getAllSessions();

    public void deleteSession(int id);

    public Session updateSession(Session session);

}
