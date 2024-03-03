package com.edan.api_tickets.repository;

import com.edan.api_tickets.repository.entities.Reseller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResellerRepository extends JpaRepository<Reseller, Integer> {
    Optional<Reseller> findByDocument(Long document);
}
