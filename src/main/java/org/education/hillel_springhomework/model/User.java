package org.education.hillel_springhomework.model;

public class User {

    private final String name;
    private final int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
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

