package com.edan.api_tickets.repository;

import com.edan.api_tickets.repository.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
