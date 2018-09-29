package com.codedito.userservice.repository;


import com.codedito.userservice.domain.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
}
