package org.education.hillel_springhomework.repository.jpa;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.education.hillel_springhomework.dto.Task;
import org.education.hillel_springhomework.dto.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@ConditionalOnProperty(name = "implementation", havingValue = "jpa", matchIfMissing = true)
public class DataService {

    private final UserDAOJPA userDAOJPA;
    private final TaskDAOJPA taskDAOJPA;

    public DataService(UserDAOJPA userDAOJPA, TaskDAOJPA taskDAOJPA) {
        this.userDAOJPA = userDAOJPA;
        this.taskDAOJPA = taskDAOJPA;
    }

    //------------------Users--------------------
    public List<User> getAllUsers() {
        return userDAOJPA.findAll();
    }

    public User getUserById(int id) {
        Optional<User> optionalUser = userDAOJPA.findById(id);
        return optionalUser.orElseThrow();
    }

    public boolean addUser(User user) {
        try {
            userDAOJPA.save(user);
            return true;
        } catch (Exception e) {
            System.err.println("Не получилось добавить пользователя: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUserById(int id) {
        try {
            userDAOJPA.deleteById(id);
            return true;
        } catch (Exception e) {
            System.err.println("Не получилось удалить пользователя по id: " + e.getMessage());
            return false;
        }
    }

    //------------------Tasks--------------------
    public List<Task> getAllTasks() {
        return taskDAOJPA.findAll();
    }

    public boolean addTask(Task task) {
        try {
            taskDAOJPA.save(task);
            return true;
        } catch (Exception e) {
            System.err.println("Не получилось добавить задачу: " + e.getMessage());
            return false;
        }
    }

    public List<Task> allTaskByStatus(String status) {
        return taskDAOJPA.findAllByStatusOfTask(status);
    }

    public List<Task> allTaskByCalendar(Calendar calendar) {
        return taskDAOJPA.findAllByDeadline(calendar);
    }

    public List<Task> allTaskByPriority(int priority) {
        return taskDAOJPA.findAllByPriority(priority);
    }

    public boolean changeStatusOfTask(Task task, String newStatus) {
        try {
            taskDAOJPA.updateStatusOfTaskByStatus(task.getName(), newStatus);
            return true;
        } catch (Exception e) {
            System.err.println("Не получилось изменить статус задачи: " + e.getMessage());
            return false;
        }
    }

    //------------------Manager_Of_Tasks--------------------
    public boolean assignTask(User user, Task task) {
        User tempUser = userDAOJPA.findById(user.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Task tempTask = taskDAOJPA.findById(task.getName()).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        tempUser.getTasks().add(tempTask);

        try {
            userDAOJPA.save(tempUser);
            return true;
        } catch (Exception e) {
            System.err.println("Не получилось назначить задачу пользователю: " + e.getMessage());
            return false;
        }
    }

    public boolean changeTask(Task oldtask, Task updateTask) {
        try {
            taskDAOJPA.updateTask(oldtask.getName(),updateTask.getDescription(),
                    updateTask.getDeadline(), updateTask.getPriority(), updateTask.getStatusOfTask());
            return true;
        } catch (Exception e) {
            System.err.println("Не получилось изменить задачу: " + e.getMessage());
            return false;
        }
    }

    public boolean printAllTasksByUser(int id) {
        List<Task> temp = taskDAOJPA.findAllTasksByUserId(id);
        try {
            for (Task task : temp) {
                System.out.println(task);
            }
            return true;
        } catch (Exception e) {
            System.err.println("Не получилось вывести в консоль задачи назначенные данному пользователю:"
                    + e.getMessage());
            return false;
        }
    }
}
