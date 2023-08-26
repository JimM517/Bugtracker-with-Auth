package com.techelevator.controller;

import com.techelevator.model.BugList;
import com.techelevator.service.BugService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ProjectController {


    // TODO add more error handling


    private final BugService bugService;

    public ProjectController(BugService bugService) {
        this.bugService = bugService;
    }


    @GetMapping("/projects")
    public List<BugList> getCurrentList(Principal principal) {
        return bugService.getUsersBugList(principal);
    }


    @PostMapping("/projects")
    public BugList createListForUser(@RequestBody BugList buglist, Principal principal) {
        try {
            String name = buglist.getName();
            String description = buglist.getDescription();

            BugList addedList = bugService.createListForUser(name, description, principal);
            return addedList;
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The correct data must be entered to update a project.", null);
        }
    }

    @PutMapping("/projects/{bugListId}")
    public BugList updateList(@PathVariable int bugListId, @RequestBody BugList modifiedList, Principal principal) {
        if (bugListId != modifiedList.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The id path does not exist or was entered incorrectly", null);
        }
        return bugService.updateABugList(bugListId, principal, modifiedList);
    }


    //this works to delete the buglist

    @DeleteMapping("/projects/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteList(@PathVariable int id, Principal principal) {
        bugService.deleteBugList(id, principal);
    }

    @DeleteMapping("/projects/{bugListId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable int bugListId, Principal principal) {
        bugService.deleteUserFromList(bugListId, principal);
    }





}
