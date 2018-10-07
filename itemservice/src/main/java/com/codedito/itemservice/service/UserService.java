package com.codedito.itemservice.service;


import com.codedito.itemservice.model.User;

public interface UserService {

    User findByUsername(String username);
}