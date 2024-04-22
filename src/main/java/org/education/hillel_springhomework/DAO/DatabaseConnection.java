package org.education.hillel_springhomework.DAO;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/manager_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "vldr";

    private final Connection connection;

    public DatabaseConnection() throws SQLException {
       this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }


}
