package com.edan.api_tickets.controller.implement;

import com.edan.api_tickets.controller.BaseController;
import com.edan.api_tickets.repository.SellRepository;
import com.edan.api_tickets.repository.entities.Answer;
import com.edan.api_tickets.repository.entities.Sell;
import com.edan.api_tickets.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class SellController implements BaseController<Sell> {
    @Autowired
    private SellRepository sellRepository;

    @Override
    @GetMapping("/sells")
    public ResponseEntity<List<Sell>> getAll() {
        List<Sell> sells = sellRepository.findAll();
        if (!sells.isEmpty()) {
            return new ResponseEntity<>(sells, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping("/sells/{id}")
    public ResponseEntity<Sell> getById(Integer id) {
        Optional<Sell> sell = sellRepository.findById(id);
        return sell.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @PostMapping("/sells/create")
    public ResponseEntity<Answer> create(Sell entity) {
        if (SellService.ticketSold(sellRepository, entity)){
            sellRepository.save(entity);
            return new ResponseEntity<>(new Answer("success", "Ticket sold"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Answer("error", "Ticket already sold"), HttpStatus.CONFLICT);
    }

    @Override
    @DeleteMapping("/sells/delete/{id}")
    public ResponseEntity<Answer> delete(Integer id) {
        Optional<Sell> sell = sellRepository.findById(id);
        if (sell.isPresent()) {
            sellRepository.delete(sell.get());
            return new ResponseEntity<>(new Answer("success", "Ticket deleted"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Answer("error", "Ticket not found"), HttpStatus.NOT_FOUND);
    }
}
