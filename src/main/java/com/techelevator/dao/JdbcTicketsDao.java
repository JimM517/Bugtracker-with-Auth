package com.techelevator.dao;

import com.techelevator.model.Tickets;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTicketsDao implements TicketsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTicketsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Tickets> findAll() {
        List<Tickets> results = new ArrayList<>();
        String sql = "SELECT * FROM tickets";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        while(rs.next()) {
            Tickets ticket = mapRowToTickets(rs);
            results.add(ticket);
        }
        return results;
    }

    @Override
    public Tickets findById(int id) {
        return null;
    }

    @Override
    public List<Tickets> findByUserId(int createdBy) {
        return null;
    }

    @Override
    public Tickets findByTitle(String title) {
        return null;
    }

    @Override
    public Tickets findByStatus(String status) {
        return null;
    }

    @Override
    public Tickets findByBugListId(int bugListId) {
        return null;
    }

    @Override
    public Tickets createTicket(Tickets newTicket) {
        return null;
    }

    @Override
    public Tickets updateTicket(Tickets modifiedTicket) {
        return null;
    }

    @Override
    public void deleteTicket(int id) {

    }



    private Tickets mapRowToTickets(SqlRowSet results) {
        Tickets ticket = new Tickets();
        ticket.setId(results.getInt("id"));
        ticket.setTitle(results.getString("title"));
        ticket.setDescription(results.getString("description"));
        ticket.setStatus(results.getString("status"));
        ticket.setCreatedBy(results.getInt("created_by"));
        ticket.setCreatedAt(results.getDate("created_at").toLocalDate().atStartOfDay());
        ticket.setBugListId(results.getInt("bug_list_id"));
        return ticket;
    }

}
