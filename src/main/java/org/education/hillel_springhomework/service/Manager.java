package org.education.hillel_springhomework.service;

import org.education.hillel_springhomework.model.Task;
import org.education.hillel_springhomework.model.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Manager {
    private final UserManager userManager;
    private final TaskManager taskManager;

    private final Map<User, List<Task>> manager = new HashMap<>();

    public Manager(UserManager userManager, TaskManager taskManager) {
        this.userManager = userManager;
        this.taskManager = taskManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    public void assignTask(User user, Task task) {
        if (!manager.isEmpty()) {
            for (Map.Entry<User, List<Task>> entry : manager.entrySet()) {
                if (entry.getKey().equals(user)) {
                    List<Task> temp = new LinkedList<>(entry.getValue());
                    temp.add(task);
                    entry.setValue(null);
                    entry.setValue(temp);
                    System.out.println("User " + user.getNAME() + " task " + task.getName());
                } else {
                    manager.put(user, List.of(task));
                    System.out.println("User " + user.getNAME() + " task " + task.getName());
                    break;
                }
            }

        } else {
            manager.put(user, List.of(task));
            System.out.println("User " + user.getNAME() + " task " + task.getName());
        }

    }

    public void printUserTasks(User user) {
        manager.get(user).forEach(System.out::println);
    }

    public void deleteUser(int userId) {
        User temp = userManager.getUsers().get(userId);

        if (temp == null) {
            throw new IllegalArgumentException("User " + userId + " not found");
        }

        manager.remove(getUserManager().getUsers().get(userId));
        userManager.getUsers().remove(userId);
        System.out.println("User " + temp + " is deleted");
    }

    public void changeStatusOfTask(Task oldTask, String modifiedStatusOfTask) {

        oldTask.setStatusOfTask(modifiedStatusOfTask);
        AtomicBoolean flag = new AtomicBoolean(false);

        for (Map.Entry<User, List<Task>> map : manager.entrySet()) {
            map.getValue().forEach(val -> {
                if (val.equals(oldTask)) {
                    val.setStatusOfTask(modifiedStatusOfTask);
                    flag.set(true);
                }
            });
        }
        if (flag.get()) System.out.println("Status of the task " + oldTask + " has been changed");
    }

    public void changeTask(Task oldTask, Task modifiedTask) {

        taskManager.getTaskList().replaceAll((task -> {
            if (task == oldTask) {
                task = modifiedTask;
                System.out.println("Task " + oldTask + " is changed " + modifiedTask);
            }
            return task;
        }));

        for (Map.Entry<User, List<Task>> map : manager.entrySet()) {
            map.getValue().forEach(val -> {
                if (val.equals(oldTask)) {
                    val.setName(modifiedTask.getName());
                    val.setDescription(modifiedTask.getDescription());
                    val.setDeadLine(modifiedTask.getDeadLine());
                    val.setPriority(modifiedTask.getPriority());
                    val.setStatusOfTask(modifiedTask.getStatusOfTask());
                }
                /*Понимаю что способ странный, но не знаю как по другому сделать.
                 * Через стримы тоже не получалось...*/
            });
        }

    }

    @Override
    public String toString() {
        return "Manager{" +
                "manager=" + manager +
                '}';
    }
}
