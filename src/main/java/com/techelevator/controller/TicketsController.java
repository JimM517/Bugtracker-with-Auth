package com.techelevator.controller;

import com.techelevator.dao.JdbcTicketsDao;
import com.techelevator.model.Tickets;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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








}
