package org.education.hillel_springhomework.repository.jdbc;

import org.education.hillel_springhomework.dto.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@ConditionalOnProperty(name = "implementation", havingValue = "jdbc", matchIfMissing = true)
public class UserDAOJDBC {

    private final DataSource databaseConnection;

    public UserDAOJDBC(DataSource databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement =
                databaseConnection.getConnection()
                        .prepareStatement("INSERT INTO users (name) VALUES (?)");

        preparedStatement.setString(1, user.getName());
        preparedStatement.executeUpdate();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        ResultSet resultSet = databaseConnection.getConnection().createStatement().executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            users.add(new User(resultSet.getString("name")));
        }
        return users;
    }

    public User getUserById(int id) throws SQLException {
        User user = null;
        PreparedStatement preparedStatement =
                databaseConnection.getConnection()
                        .prepareStatement("SELECT * FROM users WHERE id = ?");

        preparedStatement.setInt(1, id);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
            }
        }
        return user;
    }

    public void deleteUser(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement userStatement = null;
        PreparedStatement managerStatement = null;

        try {
            connection = databaseConnection.getConnection();
            connection.setAutoCommit(false);
            managerStatement = connection.prepareStatement("DELETE FROM manager_of_tasks WHERE user_id = ?");
            managerStatement.setInt(1, id);
            managerStatement.executeUpdate();

            userStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            userStatement.setInt(1, id);
            userStatement.executeUpdate();
            connection.commit();
            System.out.println("Пользователь и его задачи успешно удалены!");
        } catch (SQLException exception) {
            assert connection != null;
            connection.rollback();
            throw exception;
        } finally {
            if (userStatement != null) {
                userStatement.close();
            }
            if (managerStatement != null) {
                managerStatement.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
    }
}
