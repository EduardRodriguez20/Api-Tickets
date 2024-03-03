package com.edan.api_tickets.repository;

import com.edan.api_tickets.repository.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
