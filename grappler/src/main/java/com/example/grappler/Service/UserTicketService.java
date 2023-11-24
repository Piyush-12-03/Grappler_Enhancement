package com.example.grappler.Service;

import com.example.grappler.Entity.UserTicket;
import com.example.grappler.Exception.TicketNotFoundException;
import com.example.grappler.Repository.UserTicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTicketService {
    @Autowired
    private UserTicketRepository userTicketRepository;

    public UserTicket createUserTicket(UserTicket userTicket){
        return userTicketRepository.save(userTicket);
    }

    public UserTicket updateDropTicket(UserTicket newUserTicketData) {
        UserTicket userTicket = new UserTicket();
        userTicket.setDropDate(newUserTicketData.getDropDate());
        userTicket.setStartTicketTime(newUserTicketData.getStartTicketTime());
        userTicket.setEndTicketTime(newUserTicketData.getEndTicketTime());

        return userTicket;
    }
    //this is UserTicket Update
    public UserTicket updateDropTicket(Long userId, Long ticketId, UserTicket newUserTicketData) {
        UserTicket existingUserTicket = userTicketRepository.findUserTicketByUser_IdAndTicket_Id(userId, ticketId);

        if (existingUserTicket == null) {
            throw new TicketNotFoundException("Ticket not found for userId: " + userId + " and ticketId: " + ticketId);
        }

        // Update the fields with the new data
        existingUserTicket.setDropDate(newUserTicketData.getDropDate());
        existingUserTicket.setStartTicketTime(newUserTicketData.getStartTicketTime());
        existingUserTicket.setEndTicketTime(newUserTicketData.getEndTicketTime());

        // Save the updated user ticket
        return userTicketRepository.save(existingUserTicket);
    }

    @Transactional
    public void deleteDroppedTicket(Long userId, Long ticketId) {
        userTicketRepository.deleteFromUserTicketByUserIdAndTicketId(userId, ticketId);
    }

    public List<UserTicket> getUsersDroppedTicket(Long userId) {
       List<UserTicket> userTickets= userTicketRepository.findByUserId(userId);
        return userTickets;
    }
    public List<UserTicket> getUserDroppedTicket(Long userId) {
        List<UserTicket> userTickets= userTicketRepository.findByUserId(userId);
        return userTickets;
    }
}
