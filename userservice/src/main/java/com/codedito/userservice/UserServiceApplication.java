package com.codedito.userservice;

import com.codedito.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;

    public static void main(final String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
//        final User user1 = new User();
//        user1.setFirstName("John");
//        user1.setLastName("Adams");
//        user1.setUsername("jadams");
//        user1.setPassword("password");
//        user1.setEmail("jadams@gmail.com");
//
//        final Set<UserRole> userRoles = new HashSet<>();
////        final Role role1 = new Role();
//        role1.setRoleId(1);
//        role1.setName("ROLE_USER");
//        userRoles.add(new UserRole(user1, role1));
//
//        userService.createUser(user1);
    }
}