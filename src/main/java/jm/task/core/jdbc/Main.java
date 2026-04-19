package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl daoHibernate = new UserDaoHibernateImpl();
        daoHibernate.createUsersTable();

        daoHibernate.saveUser("Albus", "Dumbledore", (byte) 99);
        System.out.println("User Albus Dumbledore was added to database");

        daoHibernate.saveUser("Harry", "Potter", (byte) 13);
        System.out.println("User Harry Potter was added to database");

        daoHibernate.saveUser("Severus", "Snape", (byte) 28);
        System.out.println("User Severus Snape was added to database");

        daoHibernate.saveUser("Luna", "Lovegood", (byte) 12);
        System.out.println("User Luna Lovegood was added to database");

        List<User> users = daoHibernate.getAllUsers();

        for (User user : users) {
            System.out.println(user);
        }

        daoHibernate.cleanUsersTable();
        daoHibernate.dropUsersTable();

    }
}
