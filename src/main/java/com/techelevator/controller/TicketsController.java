package com.techelevator.controller;

import com.techelevator.dao.JdbcCommentsDao;
import com.techelevator.dao.JdbcTicketsDao;
import com.techelevator.model.Comments;
import com.techelevator.model.Tickets;
import com.techelevator.service.BugService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TicketsController {

    private final JdbcTicketsDao jdbcTicketsDao;

    private final JdbcCommentsDao jdbcCommentsDao;

    private final BugService bugService;

    public TicketsController(JdbcTicketsDao jdbcTicketsDao, JdbcCommentsDao jdbcCommentsDao, BugService bugService) {
        this.jdbcTicketsDao = jdbcTicketsDao;
        this.jdbcCommentsDao = jdbcCommentsDao;
        this.bugService = bugService;
    }

    @GetMapping("/tickets")
    public List<Tickets> findAllTickets() {
        return jdbcTicketsDao.findAll();
    }


    @GetMapping("/tickets/{ticketId}")
    public Tickets getTicketById(int ticketId) {
        return jdbcTicketsDao.findById(ticketId);
    }

    @GetMapping("/comments")
    public List<Comments> getAllComments() {
        return jdbcCommentsDao.findAllComments();
    }

    @GetMapping("/comments/{commentId}")
    public Comments getCommentById(@PathVariable int commentId) {
        return jdbcCommentsDao.findById(commentId);
    }

    @PostMapping("/tickets")
    public void createNewTicket(@RequestBody Tickets ticket, Principal principal) {
        try {

            bugService.createNewTicket(ticket, principal);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The ticket you are trying to add does not have a corresponding bug list.", null);
        }
    }


    @PutMapping("/tickets/{ticketId}")
    public Tickets updateTicket(@PathVariable int ticketId, Tickets modifiedTicket, Principal principal ) {
        if (ticketId != modifiedTicket.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id path does not exist of was entered incorrectly!", null);
        }
        return bugService.updateTickets(ticketId, modifiedTicket, principal);
    }



    @PostMapping("/comments")
    public void addComment(@RequestBody Comments comment, Principal principal) {
        try {
            bugService.addCommentToTicket(comment, principal);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please select the correct ticket so a new comment can be added.", null);
        }
    }







}
