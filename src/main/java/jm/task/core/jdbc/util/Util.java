package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

import java.util.Properties;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {
    // реализуйте настройку соеденения с БД

    //JDBC
    private final String url = "jdbc:mysql://localhost:3306/mydbfirst";

    /*public String getUrl() {
        return url;
    }*/

    private final static String username = "root";

    /*public static String getUsername() {
        return username;
    }*/

    private final static String pass = "admin";

    /*public static String getPass() {
        return pass;
    }*/

    private Connection connection;

    public Connection baseConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

    //HIBERNATE
    /*private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mydbfirst");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "admin");
                //settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                //settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                //settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }*/
}