package org.example.springsecurity.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.springsecurity.dto.Role;
import org.example.springsecurity.dto.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.util.Collections;

@AllArgsConstructor
@Data
@Service
@Transactional
public class ServiceDTO {

    private final DataSource dataSource;
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public void addUser(String username, String email, String password) {
        User user = new User(username, email, passwordEncoder.encode(password));
        user.setRoles(Collections.singleton(Role.USER));
        userDAO.save(user);
    }

    public User findByUsername(String name) {
        return userDAO.findByUsername(name);
    }

}
