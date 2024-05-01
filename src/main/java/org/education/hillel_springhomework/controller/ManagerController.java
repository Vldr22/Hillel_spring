package org.education.hillel_springhomework.controller;

import org.education.hillel_springhomework.DAO.ManagerDAO;
import org.education.hillel_springhomework.model.Task;
import org.education.hillel_springhomework.model.User;
import org.education.hillel_springhomework.service.Manager;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;


@RestController()
@RequestMapping("/manager")
public class ManagerController {

    private final Manager managerWeb;
    private final ManagerDAO managerDAO;

    public ManagerController(Manager manager, ManagerDAO managerDAO) {
        this.managerWeb = manager;
        this.managerDAO = managerDAO;
    }

    @GetMapping(path = "/getAllUsers")
    public List<User> getAllUsers() throws SQLException {
        //  return managerWeb.getUserManager().getUsers();
        return managerDAO.getUserDAO().getAllUsers();
    }

    @GetMapping(path = "/getAllTasks")
    public List<Task> getTaskList() throws SQLException {
        //return managerWeb.getTaskManager().getTaskList();
        return managerDAO.getTaskDAO().getAllTasks();
    }


    @GetMapping(path = "/printUserTasks")
    public void printUserTasks(@RequestParam String name,
                               int id) throws SQLException {
       /*User user = new User(name,id);
        managerWeb.printUserTasks(user);*/
        managerDAO.printUserTasks(id);
    }

    @GetMapping(path = "/allTaskByStatus")
    public List<Task> allTaskBy(@RequestParam String status) throws SQLException {
        // return managerWeb.getTaskManager().allTaskBy(status);
        return managerDAO.getTaskDAO().allTaskByStatus(status);
    }

    //good
    @GetMapping(path = "/allTaskByPriority")
    public List<Task> allTaskBy(@RequestParam int priority) throws SQLException {
        //   return managerWeb.getTaskManager().allTaskBy(priority);
        return managerDAO.getTaskDAO().allTaskByPriority(priority);
    }
    //хрен проверишь
    @GetMapping(path = "/allTaskByCalendar")
    public List<Task> allTaskBy(@RequestParam Calendar calendar) throws SQLException {
        //  return managerWeb.getTaskManager().allTaskBy(calendar);
        return managerDAO.getTaskDAO().allTaskByCalendar(calendar);
    }

    //good
    @DeleteMapping(path = "/deleteUser")
    public void deleteUser(@RequestParam int userId) throws SQLException {
        //   managerWeb.deleteUser(userId);
        managerDAO.getUserDAO().deleteUser(userId);
    }
    //good
    @PostMapping(path = "/addUser")
    public void addUser(@RequestBody User user) throws SQLException {
        //  managerWeb.getUserManager().addUser(user);
        managerDAO.getUserDAO().addUser(user);
    }
    //good
    @PostMapping(path = "/addTask")
    public void addTask(@RequestBody Task task) throws SQLException {
        // managerWeb.getTaskManager().addTask(task);
        managerDAO.getTaskDAO().addTask(task);
    }

    @PostMapping(value = "/assignTask")
    public void assignTask(@RequestBody User user,
                           @RequestBody Task task) throws SQLException {
       // managerWeb.assignTask(user, task);
        managerDAO.assignTask(user,task);
    }
    //good
    @PutMapping(value = "/changeStatusOfTask")
    public void changeStatusOfTask(@RequestBody Task oldTask,
                                   @RequestParam String modifiedStatusOfTask) throws SQLException {
        //managerWeb.changeStatusOfTask(oldTask, modifiedStatusOfTask);
        managerDAO.getTaskDAO().changeStatusOFTask(oldTask, modifiedStatusOfTask);
    }
    //good
    @PutMapping(value = "/changeTask")
    public void changeTask(@RequestBody Task oldTask,
                           @RequestBody Task modifiedTask) throws SQLException {
        //    managerWeb.changeTask(oldTask, modifiedTask);
        managerDAO.getTaskDAO().changeTask(oldTask, modifiedTask);
    }

}
