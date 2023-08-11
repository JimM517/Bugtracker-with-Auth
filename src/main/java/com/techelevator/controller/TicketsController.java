package com.techelevator.controller;

import com.techelevator.dao.JdbcTicketsDao;
import com.techelevator.model.Tickets;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TicketsController {

    private final JdbcTicketsDao jdbcTicketsDao;

    public TicketsController(JdbcTicketsDao jdbcTicketsDao) {
        this.jdbcTicketsDao = jdbcTicketsDao;
    }

    @GetMapping("/tickets")
    public List<Tickets> findAllTickets() {
        return jdbcTicketsDao.findAll();
    }


    @GetMapping("/tickets/{ticketId}")
    public Tickets getTicketById(int ticketId) {
        return jdbcTicketsDao.findById(ticketId);
    }


    @PostMapping("/tickets")
    public Tickets updateTicket(@RequestBody Tickets ticket, Principal principal) {
        return null;
    }








}
