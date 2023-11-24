package com.example.grappler.Repository;

import com.example.grappler.Entity.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
    @Query("SELECT ut FROM UserTicket ut WHERE ut.user.id = :userId AND ut.tickets.id = :ticketId")
    UserTicket findUserTicketByUser_IdAndTicket_Id(@Param("userId") Long userId, @Param("ticketId") Long ticketId);

    @Modifying
    @Query("DELETE FROM UserTicket ut WHERE ut.user.id = :userId AND ut.tickets.id = :ticketId")
    void deleteFromUserTicketByUserIdAndTicketId(@Param("userId") Long userId, @Param("ticketId") Long ticketId);

    List<UserTicket> findByUserId(Long userId);
}
