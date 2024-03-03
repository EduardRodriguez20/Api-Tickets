package com.edan.api_tickets.service;

import com.edan.api_tickets.repository.SellerRepository;
import com.edan.api_tickets.repository.entities.Seller;

import java.util.Optional;

public class SellerService {
    public static boolean createSeller(SellerRepository sellerRepository, Seller seller){
        if (sellerRepository.findByDocument(seller.getDocument()).isEmpty()){
            sellerRepository.save(seller);
            return true;
        }
        return false;
    }

    public static boolean deleteSeller(SellerRepository sellerRepository, Integer id){
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isPresent()){
            sellerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
