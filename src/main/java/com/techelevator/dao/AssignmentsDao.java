package com.techelevator.dao;

import com.techelevator.model.Assignments;

import java.util.List;

public interface AssignmentsDao {

    List<Assignments> listAllAssignments();

    List<Assignments> findByTicketId(int ticketId);

    List<Assignments> findByUserId(int createdBy);

    Assignments createAssignment(Assignments newAssignment);

    Assignments updateAssignment(Assignments modifiedAssignment);

}
