package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Statement statement = util.baseConnect();
        try {
            statement.executeUpdate("CREATE TABLE users (\n" +
                    "`id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "`name` VARCHAR(45) NOT NULL,\n" +
                    "`lastName` VARCHAR(45) NOT NULL,\n" +
                    "`age` INT NOT NULL,\n" +
                    "PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("createUsersTable: Таблица с таким именем уже существует");
        } finally {
            util.baseClose();
        }
    }

    public void dropUsersTable() {
        Statement statement = util.baseConnect();
        try {
            statement.executeUpdate("DROP TABLE users");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("dropUserTable: Таблицы с таким именем не существует");
        } finally {
            util.baseClose();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Statement statement = util.baseConnect();
        try {
            statement.executeUpdate("INSERT into users (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ")");
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("saveUser: Таблицы с таким именем не существует");
        } finally {
            util.baseClose();
        }
    }

    public void removeUserById(long id) {
        Statement statement = util.baseConnect();
        try {
            statement.executeUpdate("DELETE FROM users WHERE id = " + id);
            System.out.println("User с ID - " + id + " удален из базы");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            util.baseClose();
        }
    }

    public List<User> getAllUsers()
    {
        Statement statement = util.baseConnect();
        List<User> listUsers = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
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
            //System.out.println("Таблицы с таким именем не существует");
        } finally {
            util.baseClose();
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        Statement statement = util.baseConnect();
        try {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.baseClose();
        }
    }
}
