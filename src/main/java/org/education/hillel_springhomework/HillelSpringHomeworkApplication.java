package org.education.hillel_springhomework;

import org.education.hillel_springhomework.dto.Task;
import org.education.hillel_springhomework.dto.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootApplication
public class HillelSpringHomeworkApplication {

    public static void main(String[] args) throws SQLException {

        SpringApplication.run(HillelSpringHomeworkApplication.class, args);

        //ConfigurableApplicationContext context = SpringApplication.run(HillelSpringHomeworkApplication.class, args);
        /* https://github.com/qnixdev/java_enterprise/pull/8*/

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
        Task task4 = new Task("Fours Task",
                "do something",
                new GregorianCalendar(2024, Calendar.MAY, 1, 13, 0),
                7, "started");
        Task task5 = new Task("Task 1",
                "do ssdfsddfaffasdsomething",
                new GregorianCalendar(2024, Calendar.MAY, 1, 13, 0),
                7, "started");

        User alex = new User("Alex");
        User vlad = new User("Vlad");
        User olga = new User("Olga");
        User anna = new User("Anna");

    }
}
