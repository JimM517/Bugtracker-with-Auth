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
        Tickets ticket = null;
        String sql = "SELECT * FROM tickets WHERE id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
        if (rs.next()) {
            ticket = mapRowToTickets(rs);
        }
        return ticket;
    }

    @Override
    public List<Tickets> findByUserId(int createdBy) {
        List<Tickets> results = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE created_by = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, createdBy);
        while(rs.next()) {
            Tickets ticket = mapRowToTickets(rs);
            results.add(ticket);
        }
        return results;
    }

    @Override
    public Tickets findByTitle(String title) {
        Tickets ticket = null;
        String sql = "SELECT * FROM tickets WHERE title ILIKE ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, title);
        if (rs.next()) {
            ticket = mapRowToTickets(rs);
        }
        return ticket;
    }

    @Override
    public Tickets findByStatus(String status) {
        Tickets ticket = null;
        String sql = "SELECT * FROM tickets WHERE status = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, status);
        if (rs.next()) {
            ticket = mapRowToTickets(rs);
        }
        return ticket;
    }

    @Override
    public List<Tickets> findByBugListId(int bugListId) {
        List<Tickets> results = new ArrayList<>();
        String sql = "SELECT * FROM tickets WHERE bug_list_id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, bugListId);
        while(rs.next()) {
            Tickets ticket = mapRowToTickets(rs);
            results.add(ticket);
        }
        return results;
    }

    @Override
    public Tickets createTicket(Tickets newTicket) {
        String sql = "INSERT INTO tickets (title, description, status, created_by, created_at, bug_list_id) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newTicket.getTitle(), newTicket.getDescription(), newTicket.getStatus(), newTicket.getCreatedBy(), newTicket.getCreatedAt(), newTicket.getBugListId());
        return findById(newId);
    }

    @Override
    public Tickets updateTicket(Tickets modifiedTicket) {
        String sql = "UPDATE tickets SET title = ?, description = ?, status = ?, created_by = ?, created_at = ?, bug_list_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, modifiedTicket.getTitle(), modifiedTicket.getDescription(), modifiedTicket.getStatus(), modifiedTicket.getCreatedBy(), modifiedTicket.getCreatedAt(), modifiedTicket.getBugListId(), modifiedTicket.getId());
        return findById(modifiedTicket.getId());
    }

    @Override
    public void deleteTicket(int id) {
        String sql = "DELETE FROM tickets WHERE id = ?";
        jdbcTemplate.update(sql, id);
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
