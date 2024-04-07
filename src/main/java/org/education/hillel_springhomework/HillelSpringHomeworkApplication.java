package org.education.hillel_springhomework;

import org.education.hillel_springhomework.model.User;
import org.education.hillel_springhomework.model.Task;
import org.education.hillel_springhomework.service.Manager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootApplication
public class HillelSpringHomeworkApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext
                = SpringApplication.run(HillelSpringHomeworkApplication.class, args);

        Manager manager = applicationContext.getBean(Manager.class);

        User alex = new User("Alex", 1);
        User vlad = new User("Vlad", 2);
        User olga = new User("Olga", 3);
        User anna = new User("Anna", 4);


        Task task1 = new Task("Task 1",
                "Create First Spring Java Application",
                new GregorianCalendar(2024, Calendar.APRIL, 16),
                9, "New");
        Task task2 = new Task("Threads",
                "Use Multithreading",
                new GregorianCalendar(2024, Calendar.APRIL, 26, 12, 0),
                2, "at work");
        Task task3 = new Task("Threads with atomic",
                "Use Multithreading with atomic operations",
                new GregorianCalendar(2024, Calendar.MAY, 2, 20, 30),
                5, "at work");

        manager.getUserManager().addUser(alex);
        manager.getUserManager().addUser(vlad);
        manager.getUserManager().addUser(olga);
        manager.getUserManager().addUser(anna);
        manager.getTaskManager().addTask(task1);
        manager.getTaskManager().addTask(task2);

        manager.assignTask(alex, task1);
        manager.assignTask(alex, task2);
        manager.assignTask(vlad, task1);
        manager.assignTask(olga, task1);
        manager.assignTask(anna, task2);

        manager.deleteUser(4);
        System.out.println(manager);

        manager.changeStatusOfTask(task1, "Finally");
        manager.changeTask(task2,task3);
        System.out.println(manager.getTaskManager().allTaskBy(new GregorianCalendar
                (2024, Calendar.MAY, 2, 20, 30)));

        System.out.println(manager.getTaskManager().getStatusOfTask(task1));
        System.out.println(manager.getTaskManager().allTaskBy(5));
        System.out.println(manager.getTaskManager().allTaskBy("at work"));
        System.out.println(manager.getTaskManager().allTaskBy(3));


    }
}
