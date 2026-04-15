package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("John", "Doe", (byte) 40);
        userService.saveUser("Jane", "Doe", (byte) 35);
        userService.saveUser("Mike", "Smith", (byte) 16);
        userService.saveUser("Emily", "King", (byte) 25);

        List<User> userList = userService.getAllUsers();
        for (User user : userList){
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
