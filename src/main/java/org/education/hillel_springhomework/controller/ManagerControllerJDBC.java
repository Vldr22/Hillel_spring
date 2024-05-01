package org.education.hillel_springhomework.controller;

import org.education.hillel_springhomework.dto.Task;
import org.education.hillel_springhomework.dto.User;
import org.education.hillel_springhomework.repository.jdbc.ManagerDAOJDBC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

@RestController()
@RequestMapping("/jdbcController")
@ConditionalOnProperty(name = "implementation", havingValue = "jdbc", matchIfMissing = true)
public class ManagerControllerJDBC {

    private final ManagerDAOJDBC managerDAO;

    public ManagerControllerJDBC(ManagerDAOJDBC managerDAO) {
        this.managerDAO = managerDAO;
    }

    @GetMapping(path = "/getAllUsers")
    public List<User> getAllUsers() throws SQLException {
        return managerDAO.getUserDAO().getAllUsers();
    }

    @GetMapping(path = "/getAllTasks")
    public List<Task> getTaskList() throws SQLException {
        return managerDAO.getTaskDAO().getAllTasks();
    }

    @GetMapping(path = "/printUserTasksById")
    public void printUserTasksById(@RequestParam int id) throws SQLException {
        managerDAO.printUserTasks(id);
    }

    @GetMapping(path = "/allTaskByStatus")
    public List<Task> allTaskBy(@RequestParam String status) throws SQLException {
        return managerDAO.getTaskDAO().allTaskByStatus(status);
    }

    @GetMapping(path = "/allTaskByPriority")
    public List<Task> allTaskBy(@RequestParam int priority) throws SQLException {
        return managerDAO.getTaskDAO().allTaskByPriority(priority);
    }

    @GetMapping(path = "/allTaskByCalendar")
    public List<Task> allTaskBy(@RequestParam Calendar calendar) throws SQLException {
        return managerDAO.getTaskDAO().allTaskByCalendar(calendar);
    }

    @DeleteMapping(path = "/deleteUser")
    public void deleteUser(@RequestParam int userId) throws SQLException {
        managerDAO.getUserDAO().deleteUser(userId);
    }

    @PostMapping(path = "/addUser")
    public void addUser(@RequestParam String name) throws SQLException {
        User user = new User(name);
        managerDAO.getUserDAO().addUser(user);
    }

    @PostMapping(path = "/addTask")
    public void addTask(@RequestBody Task task) throws SQLException {
        managerDAO.getTaskDAO().addTask(task);
    }

    @PostMapping(value = "/assignTask")
    public void assignTask(@RequestBody User user,
                           @RequestBody Task task) throws SQLException {
        managerDAO.assignTask(user, task);
    }

    @PutMapping(value = "/changeStatusOfTask")
    public void changeStatusOfTask(@RequestBody Task oldTask,
                                   @RequestParam String modifiedStatusOfTask) throws SQLException {
        managerDAO.getTaskDAO().changeStatusOFTask(oldTask, modifiedStatusOfTask);
    }

    @PutMapping(value = "/changeTask")
    public void changeTask(@RequestBody Task oldTask,
                           @RequestBody Task modifiedTask) throws SQLException {
        managerDAO.getTaskDAO().changeTask(oldTask, modifiedTask);
    }
}
