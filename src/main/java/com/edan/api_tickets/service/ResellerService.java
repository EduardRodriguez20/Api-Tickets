package com.edan.api_tickets.service;

import com.edan.api_tickets.repository.ResellerRepository;
import com.edan.api_tickets.repository.entities.Reseller;

public class ResellerService {
    public static Boolean createReseller(ResellerRepository resellerRepository, Reseller reseller) {
        if (resellerRepository.findByDocument(reseller.getDocument()).isEmpty()){
            resellerRepository.save(reseller);
            return true;
        }
        return false;
    }
}
