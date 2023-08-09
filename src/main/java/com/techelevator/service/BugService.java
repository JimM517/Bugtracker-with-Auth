package com.techelevator.service;

import com.techelevator.dao.*;
import com.techelevator.model.BugList;
import com.techelevator.model.Comments;
import com.techelevator.model.Tickets;
import com.techelevator.model.User;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
public class BugService {

    private final UserDao userDao;
    private final BugListDao bugListDao;
    private final TicketsDao ticketsDao;
    private final CommentsDao commentsDao;
    private final AssignmentsDao assignmentsDao;

    public BugService(UserDao userDao, BugListDao bugListDao, TicketsDao ticketsDao, CommentsDao commentsDao, AssignmentsDao assignmentsDao) {
        this.userDao = userDao;
        this.bugListDao = bugListDao;
        this.ticketsDao = ticketsDao;
        this.commentsDao = commentsDao;
        this.assignmentsDao = assignmentsDao;
    }



    //goals of service class
    //display each buglist
    //get tickets by buglist id
    //display users on buglist
    //only authenticated users can modify bug lists and tickets/ are able to comment

    public List<BugList> getUsersBugList(Principal principal) {
        // get current user id
        User user = getCurrentUser(principal);
        int id = user.getId();

        //
        List<BugList> results = bugListDao.findByUserId(id);

        // For each bug list get tickets and comments
        for (BugList bugList : results) {
            int bugListId = bugList.getId();

//            List<Tickets> tickets = getTickets(principal, bugListId);

            //get our users tickets
            List<Tickets> tickets = ticketsDao.findByBugListId(bugListId);

            //get comments for each ticket
            for (Tickets ticket : tickets) {
                int ticketId = ticket.getId();
                List<Comments> ticketComments = getTicketComments(principal, ticketId);
                ticket.setComments(ticketComments);
            }


            // this will set the tickets with comments
            bugList.setTickets(tickets);
        }

        //return final product
        return results;

    }

    public BugList createListForUser(String name, String description, Principal principal) {
        User user = getCurrentUser(principal);

        //create new buglist object
        BugList bugList = new BugList();

        //set variables
        bugList.setName(name);
        bugList.setDescription(description);
        bugList.setCreatedBy(user.getId());

        BugList createdList = bugListDao.create(bugList);

        return createdList;

    }

    public void deleteFromBugList(int buglistId, Principal principal) {
        User user = getCurrentUser(principal);
        bugListDao.deleteUserFromList(buglistId, user.getId());
    }



    //helper function
    public User getCurrentUser(Principal principal) {
         return userDao.findByUsername(principal.getName());
    }

    //helper function
    public int getUsersId(Principal principal) {
        return userDao.findIdByUsername(principal.getName());
    }

    //helper function
    List<Tickets> getTickets(Principal principal, int bugListId) {
        return ticketsDao.findByBugListId(bugListId);
    }

    //helper function
    List<Comments> getTicketComments(Principal principal, int ticketId) {
        return commentsDao.findByTicketId(ticketId);
    }


}
