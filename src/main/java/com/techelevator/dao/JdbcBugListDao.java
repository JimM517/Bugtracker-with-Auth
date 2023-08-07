package com.techelevator.dao;

import com.techelevator.model.BugList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBugListDao implements BugListDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcBugListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<BugList> findAll() {
        List<BugList> results = new ArrayList<>();
        String sql = "SELECT * FROM bug_lists";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        while(rs.next()) {
            BugList bugList = mapRowToBugList(rs);
            results.add(bugList);
        }
        return results;
    }

    @Override
    public BugList findById(int bugListId) {
        BugList bugList = null;
        String sql = "SELECT * FROM bug_lists WHERE id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, bugListId);
        if (rs.next()) {
            bugList = mapRowToBugList(rs);
        }
        return bugList;
    }

    @Override
    public BugList filterByName(String name) {
        BugList bugList = null;
        String sql = "SELECT * FROM bug_lists WHERE name ILIKE ?";
        String filteredName = "%" + name + "%";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, filteredName);
        if (rs.next()) {
            bugList = mapRowToBugList(rs);
        }
        return bugList;
    }

    @Override
    public List<BugList> findByUserId(int createdBy) {
        List<BugList> results = new ArrayList<>();
        String sql = "SELECT * FROM bug_lists WHERE created_by = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, createdBy);
        while(rs.next()) {
            BugList bugList = mapRowToBugList(rs);
            results.add(bugList);
        }
        return results;
    }

    @Override
    public BugList findByListAndUserId(int bugListId, int createdBy) {
        BugList bugList = null;
        String sql = "SELECT * FROM bug_lists WHERE id = ? AND created_by = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, bugListId, createdBy);
        if (rs.next()) {
            bugList = mapRowToBugList(rs);
        }
        return bugList;
    }

    @Override
    public void addUserToList(int bugListId, int createdBy) {
        String sql = "UPDATE bug_lists SET created_by = ? WHERE id = ?";
        jdbcTemplate.update(sql, createdBy, bugListId);
    }

    @Override
    public BugList create(BugList newBugList) {
        String sql = "INSERT INTO bug_lists (name, description, created_by, created_at) VALUES (?, ?, ?, ?) RETURNING id";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newBugList.getName(), newBugList.getDescription(), newBugList.getCreatedBy(), newBugList.getCreatedAt());
        return findById(newId);
    }

    @Override
    public BugList update(BugList modifiedBugList) {
        String sql = "UPDATE bug_lists SET name = ?, description = ?, created_by = ?, created_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, modifiedBugList.getName(), modifiedBugList.getDescription(), modifiedBugList.getCreatedBy(), modifiedBugList.getCreatedAt(), modifiedBugList.getId());
        return findById(modifiedBugList.getId());
    }


    //TODO need to make sure this works
    @Override
    public void deleteUserFromList(int bugListId, int createdBy) {
        String sql = "DELETE FROM bug_lists WHERE id = ? AND createdBy = ?";
        jdbcTemplate.update(sql, bugListId, createdBy);
    }


    //TODO need to make sure this works
    @Override
    public void delete(int bugListId) {
        String sql = "DELETE FROM bug_lists WHERE id = ?";
        jdbcTemplate.update(sql, bugListId);
    }

    private BugList mapRowToBugList(SqlRowSet results) {
        BugList bugList = new BugList();
        bugList.setId(results.getInt("id"));
        bugList.setName(results.getString("name"));
        bugList.setDescription(results.getString("description"));
        bugList.setCreatedBy(results.getInt("created_by"));
        bugList.setCreatedAt(results.getDate("created_at").toLocalDate().atStartOfDay());
        return bugList;
    }

}
