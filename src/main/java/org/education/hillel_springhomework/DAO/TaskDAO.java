package org.education.hillel_springhomework.DAO;

import org.education.hillel_springhomework.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class TaskDAO {

    private final DatabaseConnection databaseConnection;

    @Autowired
    public TaskDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    public void addTask(Task task) throws SQLException {
        PreparedStatement preparedStatement =
                databaseConnection.getConnection().prepareStatement(
                        "INSERT INTO tasks (name, description, deadline, priority, status) VALUES (?,?,?,?,?)");

        preparedStatement.setString(1, task.getName());
        preparedStatement.setString(2, task.getDescription());
        preparedStatement.setTimestamp(3, new Timestamp(task.getDeadLine().getTimeInMillis()));
        preparedStatement.setInt(4, task.getPriority());
        preparedStatement.setString(5, task.getStatusOfTask());
        preparedStatement.executeUpdate();
    }


    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();

        ResultSet resultSet = databaseConnection.getConnection()
                .createStatement().executeQuery("SELECT * FROM tasks");
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
        return tasks;
    }

    public List<Task> allTaskByStatus(String status) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks WHERE status = ?")) {
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("status").equals(status)) {
                    Timestamp deadlineTimestamp = resultSet.getTimestamp("deadline");
                    Calendar deadlineCalendar = Calendar.getInstance();
                    deadlineCalendar.setTimeInMillis(deadlineTimestamp.getTime());

                    tasks.add(new Task(resultSet.getString("name"),
                            resultSet.getString("description"),
                            deadlineCalendar,
                            resultSet.getInt("priority"),
                            resultSet.getString("status")));
                }
            }
            return tasks;
        }
    }

    public List<Task> allTaskByPriority(int priority) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks WHERE priority = ?")) {
            statement.setInt(1, priority);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("priority") == priority) {
                    Timestamp deadlineTimestamp = resultSet.getTimestamp("deadline");
                    Calendar deadlineCalendar = Calendar.getInstance();
                    deadlineCalendar.setTimeInMillis(deadlineTimestamp.getTime());

                    tasks.add(new Task(resultSet.getString("name"),
                            resultSet.getString("description"),
                            deadlineCalendar,
                            resultSet.getInt("priority"),
                            resultSet.getString("status")));
                }
            }
        }
        return tasks;
    }

    public List<Task> allTaskByCalendar(Calendar calendar) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks WHERE deadline = ?")) {
            statement.setTimestamp(1, new Timestamp(calendar.getTimeInMillis()));
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                if (resultSet.getLong("deadline") == calendar.getTimeInMillis()) {
                    Timestamp deadlineTimestamp = resultSet.getTimestamp("deadline");
                    Calendar deadlineCalendar = Calendar.getInstance();
                    deadlineCalendar.setTimeInMillis(deadlineTimestamp.getTime());

                    tasks.add(new Task(resultSet.getString("name"),
                            resultSet.getString("description"),
                            deadlineCalendar,
                            resultSet.getInt("priority"),
                            resultSet.getString("status")));
                }
            }
            return tasks;
        }
    }

    public void changeTask(Task oldTask, Task newTask) throws SQLException {

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                "UPDATE tasks SET name = ?, description=?, deadline = ?, priority=?, status=? WHERE name=?");

        preparedStatement.setString(1, newTask.getName());
        preparedStatement.setString(2, newTask.getDescription());
        preparedStatement.setTimestamp(3, new Timestamp(newTask.getDeadLine().getTimeInMillis()));
        preparedStatement.setInt(4, newTask.getPriority());
        preparedStatement.setString(5, newTask.getStatusOfTask());
        preparedStatement.setString(6, oldTask.getName());
        preparedStatement.executeUpdate();

    }

    public void changeStatusOFTask(Task oldTask, String newStatusOfTask) throws SQLException {
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                "UPDATE tasks SET status = ? WHERE name = ?");

        preparedStatement.setString(1, newStatusOfTask);
        preparedStatement.setString(2, oldTask.getName());
        preparedStatement.executeUpdate();

    }
}
