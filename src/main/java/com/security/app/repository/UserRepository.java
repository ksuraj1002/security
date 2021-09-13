package com.security.app.repository;

import com.security.app.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByCredentials_UserName(String username);
}
