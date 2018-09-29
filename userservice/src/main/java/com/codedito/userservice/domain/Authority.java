package com.codedito.userservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uuid;

    private String role;

    @ManyToMany(mappedBy = "authorities")
    private final Set<ApplicationUser> appUsers = new HashSet<>();

    public Authority(final Role role) {
        this.role = role.getValue();
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
