package com.techelevator.controller;

import com.techelevator.dao.JdbcBugListDao;
import com.techelevator.model.BugList;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BugListController {


    private final JdbcBugListDao jdbcBugListDao;

    public BugListController(JdbcBugListDao jdbcBugListDao) {
        this.jdbcBugListDao = jdbcBugListDao;
    }

    @GetMapping("/bugs")
    @PreAuthorize("permitAll()")
    public List<BugList> getAllProjects() {
        return jdbcBugListDao.findAll();
    }


}
