package com.example.grappler.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class UserTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "ticketId")
    private Tickets tickets;

    private LocalDateTime dropDate;
    private LocalDateTime startTicketTime;
    private LocalDateTime endTicketTime;
}
