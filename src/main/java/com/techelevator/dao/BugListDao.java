package com.techelevator.dao;

import com.techelevator.model.BugList;

import java.util.List;

public interface BugListDao {

    List<BugList> findAll();

    BugList findById(int bugListId);

    BugList filterByName(String name);

    BugList findByUserId(int createdBy);

    BugList findByListAndUserId(int bugListId, int createdBy);

    void addUserToList(int bugListId, int createdBy);

    BugList create(BugList newBugList);


    BugList update(BugList modifiedBugList);

    void deleteUserFromList(int bugListId, int createdBy);

    void delete(int bugListId);



}
