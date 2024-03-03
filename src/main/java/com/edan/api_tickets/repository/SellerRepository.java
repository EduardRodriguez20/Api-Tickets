package com.edan.api_tickets.repository;

import com.edan.api_tickets.repository.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Optional<Seller> findByDocument(Long document);
}
