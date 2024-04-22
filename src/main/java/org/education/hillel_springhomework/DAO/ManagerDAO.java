package org.education.hillel_springhomework.DAO;

import org.education.hillel_springhomework.model.Task;
import org.education.hillel_springhomework.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class ManagerDAO {

    private final DatabaseConnection databaseConnection;
    private final UserDAO userDAO;
    private final TaskDAO taskDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public TaskDAO getTaskDAO() {
        return taskDAO;
    }

    @Autowired
    public ManagerDAO(DatabaseConnection databaseConnection, UserDAO userDAO, TaskDAO taskDAO) {
        this.databaseConnection = databaseConnection;
        this.userDAO = userDAO;
        this.taskDAO = taskDAO;
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

        PreparedStatement managerStatement = databaseConnection.getConnection().prepareStatement(
                "SELECT * FROM manager_of_tasks WHERE user_id = ?");

       PreparedStatement taskStatement = databaseConnection.getConnection().prepareStatement(
               "SELECT * FROM tasks WHERE name = ?");

        managerStatement.setInt(1, id);
        String userName;
        try (ResultSet userResult = managerStatement.executeQuery()) {
            if (userResult.next()) {
                userName = userResult.getString(3);
            } else {
                throw new SQLException("Не удалось получить ИМЯ пользователя");
            }
        }

        taskStatement.setString(1,userName);
        ResultSet resultSet = taskStatement.executeQuery();
        while (resultSet.next()) {
            Timestamp deadlineTimestamp = resultSet.getTimestamp("deadline");
            Calendar deadlineCalendar = Calendar.getInstance();
            deadlineCalendar.setTimeInMillis(deadlineTimestamp.getTime());

            tasks.add(new Task(resultSet.getString("name"),
                    resultSet.getString("description"),
                    deadlineCalendar,
                    resultSet.getInt("priority"),
                    resultSet.getString("status")));
        }
        System.out.println(tasks);
    }
}








