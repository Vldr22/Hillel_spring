package org.education.hillel_springhomework.model;

public class User {

    private final String NAME;
    private final int id;

    public User(String name, int id) {
        this.NAME = name;
        this.id = id;
    }

    public String getNAME() {
        return NAME;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "NAME='" + NAME + '\'' +
                ", id=" + id +
                '}';
    }
}

