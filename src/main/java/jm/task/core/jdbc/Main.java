package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Albus", "Dumbledore", (byte) 99);
        System.out.println("User Albus Dumbledore was added to database");

        userService.saveUser("Harry", "Potter", (byte) 13);
        System.out.println("User Harry Potter was added to database");

        userService.saveUser("Severus", "Snape", (byte) 28);
        System.out.println("User Severus Snape was added to database");

        userService.saveUser("Luna", "Lovegood", (byte) 12);
        System.out.println("User Luna Lovegood was added to database");

        List<User> users = userService.getAllUsers();

        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
