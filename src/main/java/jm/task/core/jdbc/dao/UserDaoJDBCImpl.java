package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    private Statement statement;

    {
        try {
            connection = Util.getConnectionJDBC();
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Ошибка  подключения к бд");
        }
    }
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users  " +
                "(id serial PRIMARY KEY, " +
                " firstName VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age INTEGER )";
        try {
            statement.execute(sql);
            System.out.println("таблица создана ");
        } catch (SQLException e) {
            System.out.println("Не удается создать таблицу");
            ;
        }

    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users ";
        try {
            statement.execute(sql);
            System.out.println("таблица удалена ");
        } catch (SQLException e) {
            System.out.println("Не удается удалить таблицу");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users (firstname, lastname, age) VALUES(?,?,?)";
        try(PreparedStatement ps = connection.prepareStatement(sql);) {

            ps.setString(1, name);
            ps.setString(2,lastName);
            ps.setInt(3,age);
            ps.execute();
            System.out.println("Пользователь добавлен");
        } catch (SQLException e) {
            System.out.println("Не удается добавить пользователя " + e);;
        }


    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setLong(1,id);
            ps.execute();
            System.out.println("Пользователь удален");
        } catch (SQLException e) {
            System.out.println("Не удается удалить пользователя " + e);;
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try(ResultSet rs = statement.executeQuery(sql);) {
            while (rs.next()) {
                String name = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int age = rs.getInt("age");
                User user = new User(name,lastName,(byte)age);
                users.add(user);
            }
            System.out.println("Пользователь добавлен");
        } catch (SQLException e) {
            System.out.println("Не удается добавить пользователя " + e);;
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE  Users ";
        try {
            statement.execute(sql);
            System.out.println("таблица очищена ");
        } catch (SQLException e) {
            System.out.println("Не удается очистить таблицу");
        }

    }
}
