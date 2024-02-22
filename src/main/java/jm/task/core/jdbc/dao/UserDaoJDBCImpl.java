package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement statement = util.baseConnect().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (\n" +
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
        }

        try {
            /* Если я сам в ручную не закрываю Connection, он остается открытым, а
            try-wit-resources автоматически закрывает только Statement */
            util.getConnection().close();
            if (util.getConnection().isClosed()) {
                System.out.println("createUserTable: соединение закрыто");
            } else {
                System.out.println("createUserTable: соединение открыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = util.baseConnect().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("dropUserTable: Таблицы с таким именем не существует, удалять нечего");
        }

        try {
            /* Если я сам в ручную не закрываю Connection, он остается открытым, а
            try-wit-resources автоматически закрывает только Statement */
            util.getConnection().close();
            if (util.getConnection().isClosed()) {
                System.out.println("dropUserTable: соединение закрыто");
            } else {
                System.out.println("dropUserTable: соединение открыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = util.baseConnect().prepareStatement("INSERT into users (name, lastName, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            /* Если я сам в ручную не закрываю Connection, он остается открытым, а
            try-wit-resources автоматически закрывает только Statement */
            util.getConnection().close();
            if (util.getConnection().isClosed()) {
                System.out.println("saveUser: соединение закрыто");
            } else {
                System.out.println("saveUser: соединение открыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Statement statement = util.baseConnect().createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE id = " + id);
            System.out.println("User с ID - " + id + " удален из базы");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            /* Если я сам в ручную не закрываю Connection, он остается открытым, а
            try-wit-resources автоматически закрывает только Statement */
            util.getConnection().close();
            if (util.getConnection().isClosed()) {
                System.out.println("removeUserById: соединение закрыто");
            } else {
                System.out.println("removeUserById: соединение открыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try (Statement statement = util.baseConnect().createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
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
        try {
            /* Если я сам в ручную не закрываю Connection, он остается открытым, а
            try-wit-resources автоматически закрывает только Statement */
            util.getConnection().close();
            if (util.getConnection().isClosed()) {
                System.out.println("getAllUsers: соединение закрыто");
            } else {
                System.out.println("getAllUsers: соединение открыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = util.baseConnect().createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            /* Если я сам в ручную не закрываю Connection, он остается открытым, а
            try-wit-resources автоматически закрывает только Statement */
            util.getConnection().close();
            if (util.getConnection().isClosed()) {
                System.out.println("cleanUserTable: соединение закрыто");
            } else {
                System.out.println("dropUserTable: соединение открыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}