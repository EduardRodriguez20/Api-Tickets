package com.edan.api_tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edan.api_tickets.repository.entities.Sell;

import java.util.Optional;

public interface SellRepository extends JpaRepository<Sell, Integer> {
    Optional<Sell> findByIdTicket(Integer idTicket);
}
