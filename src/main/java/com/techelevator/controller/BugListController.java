package com.techelevator.controller;

import com.techelevator.dao.JdbcBugListDao;
import com.techelevator.model.BugList;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("permitAll()")
public class BugListController {


    private final JdbcBugListDao jdbcBugListDao;

    public BugListController(JdbcBugListDao jdbcBugListDao) {
        this.jdbcBugListDao = jdbcBugListDao;
    }


    @GetMapping("/buglist")
    public List<BugList> getAllProjects() {
        return jdbcBugListDao.findAll();
    }


    @GetMapping("/dashboard")
    public BugList getByName(@RequestParam String name) {
        return jdbcBugListDao.filterByName(name);
    }

    @GetMapping("/dashboard/{id}")
    public BugList getListById(@PathVariable int id) {
        return jdbcBugListDao.findById(id);
    }





}
