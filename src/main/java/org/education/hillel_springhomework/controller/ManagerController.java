package org.education.hillel_springhomework.controller;

import org.education.hillel_springhomework.model.Task;
import org.education.hillel_springhomework.model.User;
import org.education.hillel_springhomework.service.Manager;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/manager")
public class ManagerController {

    private final Manager managerWeb;

    public ManagerController(Manager manager) {
        this.managerWeb = manager;
    }

    @GetMapping(path = "/getManager")
    public Map<User, List<Task>> getManager() {
        return managerWeb.getManager();
    }

    @GetMapping(path = "/getUsers")
    public Map<Integer, User> getUsers() {
        return managerWeb.getUserManager().getUsers();
    }

    @GetMapping(path = "/getTasks")
    public List<Task> getTaskList() {
        return managerWeb.getTaskManager().getTaskList();
    }


    @GetMapping(path = "/printUserTasks")
    public void printUserTasks(@RequestParam String name,
                             @RequestParam int id) {
        User user = new User(name,id);
        managerWeb.printUserTasks(user);
    }

    @GetMapping(path = "/allTaskByStatus")
    public List<Task> allTaskBy(@RequestParam String status) {
        return managerWeb.getTaskManager().allTaskBy(status);
    }

    @GetMapping(path = "/allTaskByPriority")
    public List<Task> allTaskBy(@RequestParam int priority) {
        return managerWeb.getTaskManager().allTaskBy(priority);
    }

    @GetMapping(path = "/allTaskByCalendar")
    public List<Task> allTaskBy(@RequestParam Calendar calendar) {
        return managerWeb.getTaskManager().allTaskBy(calendar);
    }

    @DeleteMapping(path = "/deleteUser")
    public void deleteUser(@RequestParam int userId) {
        managerWeb.deleteUser(userId);
    }

    @PostMapping(path = "/addUser")
    public void addUser(@RequestBody User user) {
        managerWeb.getUserManager().addUser(user);
    }

    @PostMapping(path = "/addTask")
    public void addTask(@RequestBody Task task) {
        managerWeb.getTaskManager().addTask(task);
    }

    @PostMapping(value = "/assignTask")
    public void assignTask(@RequestBody User user,
                           @RequestBody Task task) {
        managerWeb.assignTask(user, task);
    }

    @PutMapping(value = "/changeStatusOfTask")
    public void changeStatusOfTask(@RequestBody Task oldTask,
                                   @RequestParam String modifiedStatusOfTask) {
        managerWeb.changeStatusOfTask(oldTask, modifiedStatusOfTask);
    }

    @PutMapping(value = "/changeTask")
    public void changeTask(@RequestBody Task oldTask,
                           @RequestBody Task modifiedTask) {
        managerWeb.changeTask(oldTask, modifiedTask);
    }

}
