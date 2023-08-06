package com.techelevator.dao;

import com.techelevator.model.Assignments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcAssignmentsDao implements AssignmentsDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcAssignmentsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Assignments> listAllAssignments() {
        List<Assignments> results = new ArrayList<>();
        String sql = "SELECT * FROM assignments";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        while(rs.next()) {
            Assignments assignments = new Assignments();
            results.add(assignments);
        }
        return results;
    }

    @Override
    public List<Assignments> findByTicketId(int ticketId) {
        List<Assignments> results = new ArrayList<>();
        String sql = "SELECT * FROM assignments WHERE ticket_id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, ticketId);
        while(rs.next()) {
            Assignments assignments = mapRowToAssignment(rs);
            results.add(assignments);
        }
        return results;
    }

    @Override
    public List<Assignments> findByUserId(int createdBy) {
        List<Assignments> results = new ArrayList<>();
        String sql = "SELECT * FROM assignments WHERE created_by = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, createdBy);
        while(rs.next()) {
            Assignments assignments = mapRowToAssignment(rs);
            results.add(assignments);
        }
        return results;
    }

    @Override
    public Assignments createAssignment(Assignments newAssignment) {
        String sql = "INSERT INTO assignments (ticket_id, user_id, assigned_at) VALUES (?, ?, ?) RETURNING id";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newAssignment.getTicketId(), newAssignment.getUserId(), newAssignment.getAssignedAt());
        return null;

    }

    @Override
    public Assignments updateAssignment(Assignments modifiedAssignment) {
        return null;
    }

    private Assignments mapRowToAssignment(SqlRowSet results) {
        Assignments assignments = new Assignments();
        assignments.setId(results.getInt("id"));
        assignments.setTicketId(results.getInt("ticket_id"));
        assignments.setUserId(results.getInt("user_id"));
        assignments.setAssignedAt(results.getDate("assigned_at").toLocalDate().atStartOfDay());
        return assignments;
    }


}
