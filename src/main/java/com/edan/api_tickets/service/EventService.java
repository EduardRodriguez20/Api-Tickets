package com.edan.api_tickets.service;

import com.edan.api_tickets.repository.EventRepository;
import com.edan.api_tickets.repository.entities.Event;

import java.util.Optional;

public class EventService {

    public static Boolean editStatusEvent(EventRepository eventRepository, Integer id, Event event) {
        Optional<Event> existEvent = eventRepository.findById(id);
        if (existEvent.isPresent()) {
            Event currentEvent = existEvent.get();
            currentEvent.setStatus(event.getStatus());
            return true;
        }
        return false;
    }
}
