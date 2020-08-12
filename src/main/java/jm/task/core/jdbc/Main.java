package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Get connection ... ");


        Connection conn = Util.getMySQLConnection();


        System.out.println("Get connection " + conn);

        System.out.println("Done!");

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();


        conn.close();
    }
}
