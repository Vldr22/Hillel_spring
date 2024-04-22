package org.education.hillel_springhomework.model;

public class User {

    private final String name;
    private int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "NAME='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

