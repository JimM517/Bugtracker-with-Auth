package com.techelevator.controller;

import com.techelevator.dao.JdbcBugListDao;
import com.techelevator.model.BugList;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("permitAll()")
public class BugListController {


    private final JdbcBugListDao jdbcBugListDao;

    public BugListController(JdbcBugListDao jdbcBugListDao) {
        this.jdbcBugListDao = jdbcBugListDao;
    }

//    @GetMapping("/bugs")
//    public List<BugList> getAllProjects(@RequestParam String name) {
//        return jdbcBugListDao.findAll();
//    }

    @GetMapping("/bugs")
    public BugList getByName(@RequestParam String name) {
        return jdbcBugListDao.filterByName(name);
    }

    @GetMapping("/bugs/{id}")
    public BugList getListById(@PathVariable int id) {
        return jdbcBugListDao.findById(id);
    }

}
