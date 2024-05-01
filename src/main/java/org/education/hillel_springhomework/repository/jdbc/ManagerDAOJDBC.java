package org.education.hillel_springhomework.repository.jdbc;

import lombok.Getter;
import org.education.hillel_springhomework.dto.Task;
import org.education.hillel_springhomework.dto.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Repository
@ConditionalOnProperty(name = "implementation", havingValue = "jdbc", matchIfMissing = true)
public class ManagerDAOJDBC {

    private final DataSource databaseConnection;
    private final UserDAOJDBC userDAO;
    private final TaskDAOJDBC taskDAO;

    public ManagerDAOJDBC(DataSource databaseConnection, UserDAOJDBC userDAOJDBC, TaskDAOJDBC taskDAOJDBC) {
        this.databaseConnection = databaseConnection;
        this.userDAO = userDAOJDBC;
        this.taskDAO = taskDAOJDBC;
    }

    public void assignTask(User user, Task task) throws SQLException {
        PreparedStatement userStatement = databaseConnection.getConnection().prepareStatement(
                "SELECT * FROM users WHERE name = ?");
        PreparedStatement taskStatement = databaseConnection.getConnection().prepareStatement(
                "SELECT * FROM tasks WHERE name = ?");
        PreparedStatement managerStatement = databaseConnection.getConnection().prepareStatement(
                "INSERT INTO manager_of_tasks (user_id, task_name) VALUES (?, ?)");

        userStatement.setString(1, user.getName());
        int userId;
        try (ResultSet userResult = userStatement.executeQuery()) {
            if (userResult.next()) {
                userId = userResult.getInt(1);
            } else {
                throw new SQLException("Не удалось получить ID пользователя");
            }
        }

        taskStatement.setString(1, task.getName());
        String taskName;
        try (ResultSet taskResult = taskStatement.executeQuery()) {
            if (taskResult.next()) {
                taskName = taskResult.getString(1);
            } else {
                throw new SQLException("Не удалось получить доступ к задаче");
            }
        }

        managerStatement.setInt(1, userId);
        managerStatement.setString(2, taskName);
        managerStatement.executeUpdate();
    }

    public void printUserTasks(int id) throws SQLException {
        List<Task> tasks = new ArrayList<>();

        PreparedStatement userStatement = databaseConnection.getConnection().prepareStatement(
                "SELECT * FROM manager_of_tasks WHERE user_id = ?");

        PreparedStatement taskStatement = databaseConnection.getConnection().prepareStatement(
                "SELECT * FROM tasks WHERE name = ?");

        userStatement.setInt(1, id);
        String taskName;
        List<String> taskNameList = new ArrayList<>();

        try (ResultSet userResult = userStatement.executeQuery()) {
            if (userResult.next()) {
                userResult.getString(2);
            } else {
                throw new SQLException("Не удалось получить ИМЯ пользователя");
            }
        }

        try (ResultSet userResult = userStatement.executeQuery()) {
            while (userResult.next()) {
                taskName = userResult.getString(1);
                taskNameList.add(taskName);
            }
        }

        for (String string : taskNameList) {
            taskStatement.setString(1, string);
            ResultSet resultSet = taskStatement.executeQuery();
            getTaskDAO().getTask(resultSet, tasks);
        }
        System.out.println(tasks);
    }
}








