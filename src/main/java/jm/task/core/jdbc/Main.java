package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        System.out.println("Get connection ... ");
        Connection conn = Util.getMySQLConnection();
        System.out.println("Get connection " + conn);
        System.out.println("Done!");

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vasiliy","Utkin",(byte) 48);
        userService.saveUser("Oscar","Wild",(byte) 50);
        userService.saveUser("Stephen","King",(byte) 73);
        userService.saveUser("Maria","Kravc",(byte) 28);

        List<User> userList = userService.getAllUsers();
        userList.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();

        conn.close();
    }
}
