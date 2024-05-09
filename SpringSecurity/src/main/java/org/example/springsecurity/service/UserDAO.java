package org.example.springsecurity.service;

import org.example.springsecurity.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {

    User findByUsername(String name);

}
