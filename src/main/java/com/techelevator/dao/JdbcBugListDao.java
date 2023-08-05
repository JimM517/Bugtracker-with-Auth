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
        return null;
    }

    @Override
    public BugList filterByName(String name) {
        return null;
    }

    @Override
    public BugList findByUserId(int createdBy) {
        return null;
    }

    @Override
    public BugList findByListAndUserId(int bugListId, int createdBy) {
        return null;
    }

    @Override
    public void addUserToList(int bugListId, int createdBy) {

    }

    @Override
    public BugList create(BugList newBugList) {
        return null;
    }

    @Override
    public BugList update(BugList modifiedBugList) {
        return null;
    }

    @Override
    public void deleteUserFromList(int bugListId, int createdBy) {

    }


    @Override
    public void delete(int bugListId) {

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
