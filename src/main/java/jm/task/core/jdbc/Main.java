package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Leo", "Messi", (byte) 36);
        userService.saveUser("Cristiano", "Ronaldo", (byte) 37);
        userService.saveUser("Zinedin", "Zidan", (byte) 47);
        userService.saveUser("Roberto", "Carlos", (byte) 49);
        List<User> list = userService.getAllUsers();
        for (int i = 0; i < list.size(); i++ ) {
            System.out.println(list.get(i).toString());
        }
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}