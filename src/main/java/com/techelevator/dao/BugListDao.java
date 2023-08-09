package com.techelevator.dao;

import com.techelevator.model.BugList;

import java.util.List;

public interface BugListDao {

    List<BugList> findAll();

    BugList findById(int bugListId);

    BugList filterByName(String name);

    List<BugList> findByUserId(int createdBy);

    BugList findByListAndUserId(int bugListId, int createdBy);

    void addUserToList(int bugListId, int createdBy);


    BugList create(BugList newBugList);


    BugList update(BugList modifiedBugList);

    void deleteBugList(int bugListId, int createdBy);

    void removeUser(int bugListId, int createdBy);

    void delete(int bugListId);



}
