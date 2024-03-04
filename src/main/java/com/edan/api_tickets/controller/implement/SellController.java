package com.edan.api_tickets.controller.implement;

import com.edan.api_tickets.queue.Request;
import com.edan.api_tickets.queue.RequestQueue;
import com.edan.api_tickets.repository.SellRepository;
import com.edan.api_tickets.repository.TicketRepository;
import com.edan.api_tickets.repository.entities.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@RestController
@RequestMapping("/api/v1")
public class SellController {
    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public SellController(){
        Thread thread = new Thread(this::process);
        thread.start();
    }
    private Queue<Runnable> queueSell = new ConcurrentLinkedQueue<>();
    private void addQueue(Runnable runnable) {
        queueSell.offer(runnable);
    }
    private void process(){
        while(true){
            Runnable runnable = queueSell.poll();
            if(runnable!= null){
                runnable.run();
            }
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    @GetMapping("/sells")
    public List<Sell> getAllSells() {
//        addQueue(() ->{
//            List<Sell> all = sellRepository.findAll();
//            return all;
//        });
        return sellRepository.findAll();
    }

    @PostMapping("/sells/create")
    public Sell createSell(Sell sell) {
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
