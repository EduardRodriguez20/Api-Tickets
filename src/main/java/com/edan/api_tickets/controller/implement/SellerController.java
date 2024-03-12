package com.edan.api_tickets.controller.implement;

import com.edan.api_tickets.controller.BaseController;
import com.edan.api_tickets.controller.BaseControllerDocument;
import com.edan.api_tickets.repository.SellerRepository;
import com.edan.api_tickets.repository.entities.Answer;
import com.edan.api_tickets.repository.entities.Seller;
import com.edan.api_tickets.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class SellerController implements BaseController<Seller>, BaseControllerDocument<Seller> {
    @Autowired
    private SellerRepository sellerRepository;

    @Override
    @GetMapping("/sellers")
    public ResponseEntity<List<Seller>> getAll() {
        List<Seller> sellers = sellerRepository.findAll();
        if (!sellers.isEmpty()){
            return new ResponseEntity<>(sellers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping("/sellers/{id}")
    public ResponseEntity<Seller> getById(@PathVariable Integer id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        return seller.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping("/sellers/search/{document}")
    public ResponseEntity<Seller> getByDocument(@PathVariable Long document) {
        Optional<Seller> seller = sellerRepository.findByDocument(document);
        return seller.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PostMapping("/sellers/create")
    public ResponseEntity<Answer> create(Seller entity) {
        if (SellerService.createSeller(sellerRepository, entity)) {
            return new ResponseEntity<>(new Answer("success", "Seller created"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Answer("error", "Seller already exists"), HttpStatus.CONFLICT);
    }

    @Override
    @DeleteMapping("/sellers/delete/{id}")
    public ResponseEntity<Answer> delete(Integer id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isPresent()) {
            sellerRepository.delete(seller.get());
            return new ResponseEntity<>(new Answer("success", "Seller deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Answer("error", "Seller not found"), HttpStatus.NOT_FOUND);
    }


}
