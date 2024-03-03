package com.edan.api_tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edan.api_tickets.repository.entities.Sell;

public interface SellRepository extends JpaRepository<Sell, Integer> {
}
