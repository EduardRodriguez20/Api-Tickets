package com.edan.api_tickets.controller.implement;

import com.edan.api_tickets.repository.EventRepository;
import com.edan.api_tickets.repository.entities.Answer;
import com.edan.api_tickets.repository.entities.Event;
import com.edan.api_tickets.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @PostMapping("/events/create")
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    @PatchMapping("/events/edit/{id}")
    public Answer editStatusEvent(@PathVariable Integer id, @RequestBody Event event) {
        if (EventService.editStatusEvent(eventRepository, id, event)){
            return new Answer("success", "Event edited successfully");
        }
        return new Answer("error", "Event not found");
    }
    @GetMapping("/events/search/{id}")
    public Event getEventById(@PathVariable Integer id) {
        return eventRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/events/delete/{id}")
    public void deleteEvent(@PathVariable Integer id) {
        eventRepository.deleteById(id);
    }

}
