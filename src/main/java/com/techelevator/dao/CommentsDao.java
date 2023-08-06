package com.techelevator.dao;

import com.techelevator.model.Comments;

import java.util.List;

public interface CommentsDao {

    List<Comments> findAllComments();

    Comments findById(int id);

    Comments findByContent(String content);

    List<Comments> findByUserId(int createdBy);

    List<Comments> findByTicketId(int ticketId);

    Comments createComments(Comments newComment);

    Comments updateComment(Comments modifiedComment);

    void deleteComment(int id);


}
