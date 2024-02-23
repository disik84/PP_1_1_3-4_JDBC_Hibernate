package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Connection connect = util.baseConnect(); Statement statement = connect.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (\n" +
                    "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "`name` VARCHAR(45) NOT NULL,\n" +
                    "`lastName` VARCHAR(45) NOT NULL,\n" +
                    "`age` INT NOT NULL,\n" +
                    "PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
        } catch (SQLException e) {
            System.out.println("createUsersTable: Таблица с таким именем уже существует");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connect = util.baseConnect(); Statement statement = connect.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.out.println("dropUserTable: Таблицы с таким именем не существует, удалять нечего");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connect = util.baseConnect(); PreparedStatement statement = connect.prepareStatement("INSERT into users (name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection connect = util.baseConnect(); Statement statement = connect.createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE id = " + id);
            System.out.println("User с ID - " + id + " удален из базы");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try (Connection connect = util.baseConnect(); Statement statement = connect.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                listUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connect = util.baseConnect(); Statement statement = connect.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}