package com.techelevator.controller;

import com.techelevator.model.BugList;
import com.techelevator.service.BugService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class ProjectController {


    private final BugService bugService;

    public ProjectController(BugService bugService) {
        this.bugService = bugService;
    }


    @GetMapping("/projects")
    public List<BugList> getCurrentList(Principal principal) {
        return bugService.getUsersBugList(principal);
    }


}
