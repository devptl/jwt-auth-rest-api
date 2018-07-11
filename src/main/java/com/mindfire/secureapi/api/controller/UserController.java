package com.mindfire.secureapi.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.secureapi.api.service.UserService;
import com.mindfire.secureapi.core.entity.User;

/**
 * Implementation for API end points to access User data via UserService.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listUser() {
	return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getOne(@PathVariable Long id) {
	return userService.findById(id);
    }

}
