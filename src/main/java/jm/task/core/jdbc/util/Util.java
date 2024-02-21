package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String url = "jdbc:mysql://localhost:3306/mydbfirst";
    private final static String username = "root";
    private final static String pass = "admin";
    public Connection connection;
    public Statement statement;
    public Statement baseConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, pass);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
    public void baseClose() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}