package org.education.hillel_springhomework.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deadline")
    private Calendar deadline;

    @Column(name = "priority")
    private int priority;

    @Column(name = "status")
    private String statusOfTask;

    public Task(String name, String description, Calendar deadLine, int priority, String statusOfTask) {
        this.name = name;
        this.description = description;
        this.deadline = deadLine;
        this.priority = priority;
        this.statusOfTask = statusOfTask;
    }

    @ManyToMany(mappedBy = "tasks")
    @ToString.Exclude
    private Set<User> users = new HashSet<>();


}
