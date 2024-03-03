package com.edan.api_tickets.controller.implement;

import com.edan.api_tickets.queue.Request;
import com.edan.api_tickets.queue.RequestQueue;
import com.edan.api_tickets.repository.SellRepository;
import com.edan.api_tickets.repository.entities.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SellController {
    @Autowired
    private SellRepository sellRepository;

    @GetMapping("/sells")
    public List<Sell> getAllSells() {
        return sellRepository.findAll();
    }

    @PostMapping("/sells/create")
    public Sell createSell(Sell sell) {
        RequestQueue.getInstance().addQueue(new Request("create", sell));
        return sellRepository.save(sell);
    }

//    @PatchMapping("/sells/edit/{id}")
//    public Sell editSell(@PathVariable Integer id, @RequestBody Sell sell) {
//        return sellRepository.save(sell);
//    }

    @GetMapping("/sells/search/{id}")
    public Sell getSellById(@PathVariable Integer id) {
        return sellRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/sells/delete/{id}")
    public void deleteSell(@PathVariable Integer id) {
        sellRepository.deleteById(id);
    }

}
