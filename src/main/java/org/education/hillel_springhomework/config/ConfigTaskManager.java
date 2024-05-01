package org.education.hillel_springhomework.config;

import org.education.hillel_springhomework.DAO.DatabaseConnection;
import org.education.hillel_springhomework.service.Manager;
import org.education.hillel_springhomework.service.TaskManager;
import org.education.hillel_springhomework.service.UserManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.sql.SQLException;

@Configuration
public class ConfigTaskManager {

    @Bean
    public UserManager userManager() {
        return new UserManager();
    }

    @Bean
    public TaskManager taskManager() {
        return new TaskManager();
    }

    @Bean
    public Manager manager (UserManager userManager, TaskManager taskManager) {
        return new Manager(userManager,taskManager);
    }

    @Bean
    public DatabaseConnection databaseConnection() throws SQLException {
        return new DatabaseConnection();
    }

}
