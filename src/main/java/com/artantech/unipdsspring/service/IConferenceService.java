package com.artantech.unipdsspring.service;

import java.util.List;

import com.artantech.unipdsspring.model.Conference;

public interface IConferenceService {

    Conference addConference(Conference conference);

    Conference getConferenceById(int id);

    List<Conference> getAllConferences();

}
