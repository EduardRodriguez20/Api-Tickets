package com.edan.api_tickets.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    private String status;
    private String message;
    private Integer id_ticket;
    private Integer id_seller;

    public Answer(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
