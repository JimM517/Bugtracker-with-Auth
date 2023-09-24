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

    // TODO check methods, add more error handling

    private final JdbcBugListDao jdbcBugListDao;

    public BugListController(JdbcBugListDao jdbcBugListDao) {
        this.jdbcBugListDao = jdbcBugListDao;
    }

    // TODO -> don't think I need this anymore, mostly everything should be in project controller
    @GetMapping("/buglist")
    public List<BugList> getAllProjects() {
        return jdbcBugListDao.findAll();
    }


//     TODO 8/25, don't really think I need this, may refactor to get from /buglist endpoints, but don't see a use for this atm
//    @GetMapping("/dashboard")
//    public BugList getByName(@RequestParam String name) {
//        return jdbcBugListDao.filterByName(name);
//    }

    @GetMapping("/buglist/{id}")
    public BugList getListById(@PathVariable int id) {
        return jdbcBugListDao.findById(id);
    }





}
