package com.example.grappler.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDropDTO {
    private Long userId;
    private Long ticketId;
    private LocalDateTime dropDate;
    private LocalDateTime startTicketTime;
    private LocalDateTime endTicketTime;
}