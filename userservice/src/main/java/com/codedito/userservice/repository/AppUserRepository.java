package com.codedito.userservice.repository;

import com.codedito.userservice.domain.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String login);

    ApplicationUser findApplicationUserByPassword(String password);
}
