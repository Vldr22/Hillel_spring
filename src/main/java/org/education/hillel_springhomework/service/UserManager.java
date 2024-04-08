package org.education.hillel_springhomework.service;

import org.education.hillel_springhomework.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private final Map<Integer, User> users = new HashMap<>();

    public Map<Integer, User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
        System.out.println("User "+user.getName()+" is added");
    }

    @Override
    public String toString() {
        return "UserManager{" +
                "users=" + users +
                '}';
    }
}
