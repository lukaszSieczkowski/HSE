package com.codedito.userservice.controller;

import com.codedito.userservice.model.User;
import com.codedito.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{username}")
    public User getUserByUsername(@PathVariable final String username) {
        return userService.getUserByUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    public User createUser(@RequestBody final User user) {
        return userService.createUser(user);
    }
}