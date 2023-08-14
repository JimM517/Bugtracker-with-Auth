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

    // this works
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

    //this works
    public BugList createListForUser(String name, String description, Principal principal) {
        User user = getCurrentUser(principal);

        //create new buglist object
        BugList bugList = new BugList();

        //set variables
        bugList.setName(name);
        bugList.setDescription(description);
        bugList.setCreatedBy(user.getId());

        //call the jdbcbuglist create function with the set variables
        BugList createdList = bugListDao.create(bugList);

        return createdList;

    }

    // update a buglist
    public BugList updateABugList(int bugListId, Principal principal, BugList modifiedBugList) {
        User user = getCurrentUser(principal);

        // find the list to update with findById
        BugList bugList = bugListDao.findById(bugListId);

        //check to make sure bug list isn't null and our user is on the project to update, don't want any user able to update projects
        if (bugList != null && bugList.getCreatedBy() == user.getId()) {
            bugList.setName(modifiedBugList.getName());
            bugList.setDescription(modifiedBugList.getDescription());
            bugList.setTickets(getTickets(principal, bugListId));

            BugList updatedList = bugListDao.update(bugList);
            return updatedList;
        } else {
            throw new IllegalArgumentException("Project not found, unable to update!");
        }

    }



    public void createNewTicket(Tickets ticket, Principal principal) {
        //get our user and corresponding id
        User user = getCurrentUser(principal);
        int id = user.getId();

        // get the current bug lists for our user
        List<BugList> usersList = bugListDao.findByUserId(id);

        for (BugList index : usersList) {
            if (index.getId() == ticket.getBugListId()) {
                int newTicketId = ticketsDao.addTicketToBugList(index.getId(), ticket);

                // update ticket with new id;
                ticket.setId(newTicketId);
                index.getTickets().add(ticket);
                // exit
                return;
            }
        }

        throw new IllegalArgumentException("Bug List was not found for the provided ticket.");

    }

    public Tickets updateTickets(int ticketId, Tickets modifiedTicket, Principal principal) {
        // get our user, don't want anyone to be able to update tickets
        User user = getCurrentUser(principal);

        // Find our ticket
        Tickets ticket = ticketsDao.findById(ticketId);

        if (ticket != null && ticket.getCreatedBy() == user.getId()) {
            ticket.setTitle(modifiedTicket.getTitle());
            ticket.setDescription(modifiedTicket.getDescription());
            ticket.setStatus(modifiedTicket.getStatus());
            ticket.setComments(modifiedTicket.getComments());

            return ticket;
        } else {
            throw new IllegalArgumentException("Ticket not found to update!");
        }

    }


    public int getAssignmentsTotal(Principal principal) {
        // get our user
        User user = getCurrentUser(principal);
        // return our count
        return assignmentsDao.countAssignments(user.getId());
    }





    // this works to delete the bug list, not user!!
    public void deleteBugList(int bugListId, Principal principal) {
        User user = getCurrentUser(principal);
        bugListDao.deleteBugList(bugListId, user.getId());
    }

    // Just remove user
    public void deleteUserFromList(int bugListId, Principal principal) {
        User user = getCurrentUser(principal);
        bugListDao.removeUser(bugListId, user.getId());
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
