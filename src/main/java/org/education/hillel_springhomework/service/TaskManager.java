package org.education.hillel_springhomework.service;

import org.education.hillel_springhomework.model.Task;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TaskManager {

    private final List<Task> taskList = new LinkedList<>();

    public List<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("Task with name "+task.getName()+" is added");
    }

    public String getStatusOfTask(Task task) {
         return task.getStatusOfTask();
    }

    public List<Task> allTaskBy(String status) {
        System.out.println("All tasks with status " + status+":");
        return taskList.stream().filter(f -> Objects.equals(f.getStatusOfTask(), status)).toList();
    }

    public List<Task> allTaskBy(int priority) {
        System.out.println("All tasks with priority " + priority+":");
        return taskList.stream().filter(f -> (f.getPriority() == priority)).toList();
    }

    public List<Task> allTaskBy(Calendar calendar) {
        System.out.println("All tasks by calendar " + calendar.getTime()+":");
        return taskList.stream().filter(f -> (f.getDeadLine().equals(calendar))).toList();
    }

    @Override
    public String toString() {
        return "TaskManager{" +
                "taskList=" + taskList +
                '}';
    }
}

