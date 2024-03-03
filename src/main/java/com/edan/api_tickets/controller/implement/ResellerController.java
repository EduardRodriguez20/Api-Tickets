package com.edan.api_tickets.controller.implement;

import com.edan.api_tickets.repository.ResellerRepository;
import com.edan.api_tickets.repository.entities.Reseller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ResellerController {

    @Autowired
    private ResellerRepository resellerRepository;

    @GetMapping("/resellers")
    public List<Reseller> getAllResellers() {
        return resellerRepository.findAll();
    }

    @PostMapping("/resellers/create")
    public Reseller createReseller(Reseller reseller) {
        return resellerRepository.save(reseller);
    }

    @GetMapping("/resellers/search/{document}")
    public Reseller getResellerByDocument(@PathVariable Long document) {
        return resellerRepository.findByDocument(document).orElse(null);
    }

    @DeleteMapping("/resellers/delete/{id}")
    public void deleteReseller(@PathVariable Integer id) {
        resellerRepository.deleteById(id);
    }
}
