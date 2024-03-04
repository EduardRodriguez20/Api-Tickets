package com.edan.api_tickets.controller.implement;

import com.edan.api_tickets.controller.BaseController;
import com.edan.api_tickets.controller.BaseControllerDocument;
import com.edan.api_tickets.repository.ResellerRepository;
import com.edan.api_tickets.repository.entities.Answer;
import com.edan.api_tickets.repository.entities.Reseller;
import com.edan.api_tickets.service.ResellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ResellerController implements BaseController<Reseller>, BaseControllerDocument<Reseller> {

    @Autowired
    private ResellerRepository resellerRepository;

    @Override
    @GetMapping("/resellers")
    public ResponseEntity<List<Reseller>> getAll() {
        List<Reseller> resellers = resellerRepository.findAll();
        if (!resellers.isEmpty()) {
            return new ResponseEntity<>(resellers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping("/resellers/{id}")
    public ResponseEntity<Reseller> getById(Integer id) {
        Optional<Reseller> reseller = resellerRepository.findById(id);
        return reseller.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping("/resellers/search/{document}")
    public ResponseEntity<Reseller> getByDocument(Long document) {
        Optional<Reseller> reseller = resellerRepository.findByDocument(document);
        return reseller.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Override
    @PostMapping("/resellers/create")
    public ResponseEntity<Answer> create(Reseller entity) {
        if (ResellerService.createReseller(resellerRepository, entity)){
            return new ResponseEntity<>(new Answer("success", "Reseller created successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Answer("error", "There is a reseller with the same document"), HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<Answer> delete(Integer id) {
        Optional<Reseller> reseller = resellerRepository.findById(id);
        if (reseller.isPresent()) {
            resellerRepository.delete(reseller.get());
            return new ResponseEntity<>(new Answer("success", "Reseller deleted successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Answer("error", "Reseller not found"), HttpStatus.NOT_FOUND);
    }
    public static ResponseEntity<Answer> xdd(){
        return new ResponseEntity<>(new Answer("success", "xdd"), HttpStatus.OK);
    }

    public void xd(){
        ResponseEntity<Answer> xdd = xdd();
    }
}
