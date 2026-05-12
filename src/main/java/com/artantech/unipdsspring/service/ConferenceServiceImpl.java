package com.artantech.unipdsspring.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

import com.artantech.unipdsspring.events.NotFoundException;
import com.artantech.unipdsspring.model.Conference;
import com.artantech.unipdsspring.repository.ConferenceRepo;

@Service
public class ConferenceServiceImpl implements IConferenceService {

    private ConferenceRepo repo;

    public ConferenceServiceImpl(ConferenceRepo repo) {
        this.repo = repo;
    }

    @Override
    public Conference addConference(Conference conference) {
        conference.setCreatedAt(LocalDateTime.now());
        return repo.save(conference);
    }

    @Override
    public Conference getConferenceById(int id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Conference " + id + " not found"));
    }

    @Override
    public List<Conference> getAllConferences() {
        return repo.findAll();
    }

}
