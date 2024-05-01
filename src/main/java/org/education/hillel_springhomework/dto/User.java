package org.education.hillel_springhomework.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public User(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public User() {

    }

    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "manager_of_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_name")
    )
    private Set<Task> tasks = new HashSet<>();


}

