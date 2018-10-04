package com.codedito.userservice.service.impl;


import com.codedito.userservice.model.Role;
import com.codedito.userservice.model.User;
import com.codedito.userservice.model.UserRole;
import com.codedito.userservice.repository.UserRepository;
import com.codedito.userservice.service.UserService;
import com.codedito.userservice.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser(final User user) {
        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("User with username {} already exists. Nothing will be done.", user.getUsername());
        } else {
            final Set<UserRole> userRoles = new HashSet<>();
            final Role localRole = new Role();
            localRole.setRoleId(1);
            userRoles.add(new UserRole(user, localRole));
            user.getUserRoles().addAll(userRoles);

            final Date today = new Date();
            user.setJoinDate(today);

            final String encryptedPassword = SecurityUtility.passwordEncoder().encode(user.getPassword());
            user.setPassword(encryptedPassword);
            localUser = userRepository.save(user);
        }

        return localUser;
    }
}
