package com.codedito.userservice.service;


import com.codedito.userservice.model.User;

public interface UserService {
    User createUser(User user);

    User getUserByUsername(String username);
}