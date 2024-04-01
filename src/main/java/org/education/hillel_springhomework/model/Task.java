package org.education.hillel_springhomework.model;

import java.util.Calendar;

public class Task {

    private String name;
    private String description;
    private Calendar deadLine;
    private int priority;
    private String statusOfTask;

    public Task(String name, String description, Calendar deadLine, int priority, String statusOfTask) {
        this.name = name;
        this.description = description;
        this.deadLine = deadLine;
        this.priority = priority;
        this.statusOfTask = statusOfTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public String getStatusOfTask() {
        return statusOfTask;
    }

    public void setStatusOfTask(String statusOfTask) {
        this.statusOfTask = statusOfTask;
    }

    public Calendar getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Calendar deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        return "Task{" +
                "NAME='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine.getTime() +
                ", priority=" + priority +
                ", statusOfTask='" + statusOfTask + '\'' +
                '}';
    }
}
