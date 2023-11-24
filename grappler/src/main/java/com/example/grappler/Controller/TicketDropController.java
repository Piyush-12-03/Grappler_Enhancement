package com.example.grappler.Controller;

import com.example.grappler.Entity.UserTicket;
import com.example.grappler.Service.UserTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/ticket-drops")
public class TicketDropController {

    @Autowired
    private UserTicketService userTicketService;

    @PostMapping("/")
    public ResponseEntity<?> handleTicketDrop(@RequestBody UserTicket userTicketData) {
        try {
            UserTicket createdUserTicket = userTicketService.createUserTicket(userTicketData);
            return new ResponseEntity<>(createdUserTicket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create user ticket", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{userId}/{ticketId}")
    public ResponseEntity<?> updateExistingTicket(
            @PathVariable Long userId,
            @PathVariable Long ticketId,
            @RequestBody UserTicket newUserTicketData) {
        try {
            UserTicket updatedUserTicket = userTicketService.updateDropTicket(userId, ticketId, newUserTicketData);
            return new ResponseEntity<>(updatedUserTicket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user ticket", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{userId}/{ticketId}")
    public ResponseEntity<?> deleteExistingTicket(
            @PathVariable Long userId,
            @PathVariable Long ticketId) {
        try {
            userTicketService.deleteDroppedTicket(userId, ticketId);
            return new ResponseEntity<>("Dropped Ticket Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete dropped ticket", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUsersDroppedTicket(
            @PathVariable Long userId) {
        try {
            List<UserTicket> usersDroppedTicket = userTicketService.getUsersDroppedTicket(userId);
            return new ResponseEntity<>(usersDroppedTicket, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ticket not found", HttpStatus.BAD_REQUEST);
        }
    }
}
