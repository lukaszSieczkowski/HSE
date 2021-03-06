package com.codedito.itemservice.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@JsonDeserialize
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userRoleId;

    public UserRole() {
    }

    public UserRole(final User user, final Role role) {
        this.user = user;
        this.role = role;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Role role;

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(final long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }
}

