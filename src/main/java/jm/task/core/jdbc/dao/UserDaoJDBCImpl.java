package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = null;

    public UserDaoJDBCImpl() {
        try {
            connection = Util.getMySQLConnection();
        } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
        }
    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE if not exists users " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " lastname VARCHAR (50), " +
                " age SMALLINT not NULL, " +
                " PRIMARY KEY (id))";

        try {
            connection.createStatement().executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("таблица users создана ");
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE if exists users";
        try {
            connection.createStatement().executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("таблица users удалена ");
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO users (name, lastname, age) values (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setInt(3,age);
            int val = preparedStatement.executeUpdate();
            if (val == 1) {
                System.out.println("user c именем " + name + " добавлен в таблицу ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, (int) id);
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                System.out.println("user с id " + id + " удалён ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM USERS";
        ResultSet rs;
        List<User> userList = new LinkedList<>();
        try {
            rs = connection.createStatement().executeQuery(SQL);
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("lastname"), rs.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE FROM users";
        try {
            int count = connection.createStatement().executeUpdate(SQL);
            if (count != 0) {
                System.out.println("Таблица users очищена ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
