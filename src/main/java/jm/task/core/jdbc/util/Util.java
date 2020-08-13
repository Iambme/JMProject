package jm.task.core.jdbc.util;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

    private static Connection conn;
    private static Statement statement;

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

    public static Statement getStatement() throws SQLException, ClassNotFoundException {
        if (statement == null) {
            statement = getMySQLConnection().createStatement();
        }
        return statement;
    }
}
