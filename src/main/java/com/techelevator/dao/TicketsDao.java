package com.techelevator.dao;

import com.techelevator.model.Tickets;

import java.util.List;

public interface TicketsDao {

    List<Tickets> findAll();

    Tickets findById(int id);

    List<Tickets> findByUserId(int createdBy);

    Tickets findByTitle(String title);

    Tickets findByStatus(String status);

    List<Tickets> findByBugListId(int bugListId);

    int addTicketToBugList(int bugListId, Tickets addedTicket);

    Tickets createTicket(Tickets newTicket);

    Tickets updateTicket(Tickets modifiedTicket);

    void deleteTicket(int id);
}
