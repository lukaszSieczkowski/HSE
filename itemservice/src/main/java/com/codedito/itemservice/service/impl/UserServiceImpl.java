package com.codedito.itemservice.service.impl;

import com.codedito.itemservice.model.User;
import com.codedito.itemservice.repository.UserRepository;
import com.codedito.itemservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }
}