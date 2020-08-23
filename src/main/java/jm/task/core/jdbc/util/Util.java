package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Util {

    private static Connection conn;
    private static Statement statement;
    private static SessionFactory sessionFactory;
    private static Configuration configuration;

    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "testschema";
        String userName = "root";
        String password = "BEtlF0G23780";

        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws ClassNotFoundException, SQLException {

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=UTC&useSSL=false";

        if (conn == null) {
            conn = DriverManager.getConnection(connectionURL, userName,
                    password);
        }
        return conn;
    }

    public static SessionFactory getSessionFactoryWithoutXML() {
        if (sessionFactory == null) {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/testschema?useSSL=false&serverTimezone=UTC");
            prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            prop.setProperty("hibernate.connection.username", "root");
            prop.setProperty("hibernate.connection.password", "BEtlF0G23780");
            prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

            prop.setProperty("show_sql", String.valueOf(true));
            prop.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration = new Configuration();
            configuration.addProperties(prop);
            configuration.addAnnotatedClass(User.class);

            sessionFactory = configuration.buildSessionFactory();
        }

        return sessionFactory;
    }
}
