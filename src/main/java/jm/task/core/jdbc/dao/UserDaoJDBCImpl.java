package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "lastName VARCHAR(255), " +
                "age TINYINT" +
                ")";

        try(
                Connection connection = Util.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        )
        {
            stmt.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to create table", e);
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";
        try(
                Connection connection = Util.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        )
        {
            stmt.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to delete table", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users (name, lastName, age) " +
                "VALUES (?,?,?)";
        try(
                Connection connection = Util.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ){
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setByte(3, age);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM Users " +
                "WHERE id = ?";
        try(
                Connection connection = Util.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to delete user", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM Users";
        try(
                Connection connection = Util.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getByte("age")
                );
                users.add(user);
            }
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to get users", e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM Users";
        try(
                Connection connection = Util.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ){
            stmt.executeUpdate();
        }
        catch (SQLException e){
            throw new RuntimeException("Failed to clear table", e);
        }
    }
}
