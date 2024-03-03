package com.edan.api_tickets.controller.implement;

import com.edan.api_tickets.repository.TicketRepository;
import com.edan.api_tickets.repository.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @PostMapping("/tickets/create")
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @GetMapping("/tickets/search/{id}")
    public Ticket getTicketById(@PathVariable Integer id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/tickets/delete/{id}")
    public void deleteTicket(@PathVariable Integer id) {
        ticketRepository.deleteById(id);
    }
}
