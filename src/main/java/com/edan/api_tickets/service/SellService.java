package com.edan.api_tickets.service;

import com.edan.api_tickets.repository.SellRepository;
import com.edan.api_tickets.repository.entities.Sell;

import java.util.Optional;

public class SellService {

    public static boolean ticketSold(SellRepository sellRepository, Sell sell) {
        Optional<Sell> sold = sellRepository.findByIdTicket(sell.getIdTicket());
        return sold.isEmpty();
    }


}
