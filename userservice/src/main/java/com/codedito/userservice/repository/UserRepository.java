package com.codedito.userservice.repository;

import com.codedito.userservice.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}