package com.edan.api_tickets.controller.implement;

import com.edan.api_tickets.controller.BaseController;
import com.edan.api_tickets.repository.EventRepository;
import com.edan.api_tickets.repository.entities.Answer;
import com.edan.api_tickets.repository.entities.Event;
import com.edan.api_tickets.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EventController implements BaseController<Event> {

    @Autowired
    private EventRepository eventRepository;

    @Override
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAll() {
        List<Event> events = eventRepository.findAll();
        if (!events.isEmpty()){
            return new ResponseEntity<>(events, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @GetMapping("/events/search/{id}")
    public ResponseEntity<Event> getById(Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PostMapping("/events/create")
    public ResponseEntity<Answer> create(Event entity) {
        eventRepository.save(entity);
        return new ResponseEntity<>(new Answer("success", "Event created successfully"), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/events/delete/{id}")
    public ResponseEntity<Answer> delete(Integer id) {
        Optional<Event> event = eventRepository.findById(id);
        if (event.isPresent()){
            eventRepository.deleteById(id);
            return new ResponseEntity<>(new Answer("success", "Event deleted successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Answer("error", "Event not found"), HttpStatus.NOT_FOUND);
    }

    //own method
    @PatchMapping("/events/edit/{id}")
    public Answer editStatusEvent(@PathVariable Integer id, @RequestBody Event event) {
        if (EventService.editStatusEvent(eventRepository, id, event)){
            return new Answer("success", "Event edited successfully");
        }
        return new Answer("error", "Event not found");
    }
}
