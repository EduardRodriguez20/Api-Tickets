package com.edan.api_tickets.controller.implement;

import com.edan.api_tickets.repository.SellerRepository;
import com.edan.api_tickets.repository.entities.Answer;
import com.edan.api_tickets.repository.entities.Seller;
import com.edan.api_tickets.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SellerController {
    @Autowired
    private SellerRepository sellerRepository;

    @GetMapping("/sellers")
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @PostMapping("/sellers/create")
    public Answer createSeller(Seller seller) {
        if (SellerService.createSeller(sellerRepository, seller)) {
            return new Answer("success", "Seller created");
        }
        return new Answer("error", "Seller already exists");
    }

    @GetMapping("/sellers/search/{document}")
    public Seller getSellerByDocument(@PathVariable Long document) {
        return sellerRepository.findByDocument(document).orElse(null);
    }

    @DeleteMapping("/sellers/delete/{id}")
    public Answer deleteSeller(@PathVariable Integer id) {
        if (SellerService.deleteSeller(sellerRepository, id)) {
            return new Answer("success", "Seller deleted");
        }
        return new Answer("error", "Seller not found");
    }


}
