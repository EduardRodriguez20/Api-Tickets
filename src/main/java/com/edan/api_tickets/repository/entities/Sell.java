package com.edan.api_tickets.repository.entities;

import com.edan.api_tickets.utils.Formats;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sell")
public class Sell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_ticket")
    private Integer idTicket;

    @Column(name = "id_reseller")
    private Integer idReseller;

    @Column(name = "document_user")
    private Long documentUser;

    @Column(name = "date")
    private String date;

    public Sell(Integer id_ticket, Integer id_reseller, Long document_user) {
        this.idTicket = id_ticket;
        this.idReseller = id_reseller;
        this.documentUser = document_user;
        this.date = Formats.formatDate(LocalDateTime.now());
    }

}
