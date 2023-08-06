package com.techelevator.dao;

import com.techelevator.model.Comments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCommentsDao implements CommentsDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcCommentsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Comments> findAllComments() {
        List<Comments> results = new ArrayList<>();
        String sql = "SELECT * FROM comments";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        while(rs.next()) {
            Comments comment = mapRowToComments(rs);
            results.add(comment);
        }
        return results;
    }

    @Override
    public Comments findById(int id) {
        Comments comment = null;
        String sql = "SELECT * FROM comments WHERE id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
        if (rs.next()) {
            comment = mapRowToComments(rs);
        }
        return comment;
    }

    @Override
    public Comments findByContent(String content) {
        Comments comment = null;
        String sql = "SELECT * FROM comments WHERE content ILIKE ?";
        String filteredContent = "%" + content + "%";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, filteredContent);
        if (rs.next()) {
            comment = mapRowToComments(rs);
        }
        return comment;
    }

    @Override
    public List<Comments> findByUserId(int createdBy) {
        List<Comments> results = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE created_by = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, createdBy);
        while(rs.next()) {
            Comments comment = mapRowToComments(rs);
            results.add(comment);
        }
        return results;
    }

    @Override
    public List<Comments> findByTicketId(int ticketId) {
        List<Comments> results = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE ticket_id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, ticketId);
        while(rs.next()) {
            Comments comment = mapRowToComments(rs);
            results.add(comment);
        }
        return results;
    }

    @Override
    public Comments createComments(Comments newComment) {
        String sql = "INSERT INTO comments (content, created_by, created_at, ticket_id) VALUES (?, ?, ?, ?) RETURNING id";
        int newId = jdbcTemplate.queryForObject(sql, int.class, newComment.getContent(), newComment.getCreatedBy(), newComment.getCreatedAt(), newComment.getTicketId());
        return findById(newId);
    }

    @Override
    public Comments updateComment(Comments modifiedComment) {
        String sql = "UPDATE comments SET content = ?, created_by = ?, created_at = ?, ticket_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, modifiedComment.getContent(), modifiedComment.getCreatedBy(), modifiedComment.getCreatedAt(), modifiedComment.getTicketId(), modifiedComment.getId());
        return findById(modifiedComment.getId());
    }

    @Override
    public void deleteComment(int id) {
        String sql = "DELETE FROM comments WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    private Comments mapRowToComments(SqlRowSet results) {
        Comments comment = new Comments();
        comment.setId(results.getInt("id"));
        comment.setContent(results.getString("content"));
        comment.setCreatedBy(results.getInt("created_by"));
        comment.setCreatedAt(results.getDate("created_at").toLocalDate().atStartOfDay());
        comment.setTicketId(results.getInt("ticket_id"));
        return comment;
    }


}
