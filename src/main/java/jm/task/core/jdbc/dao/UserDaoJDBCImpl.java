package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE if not exists users " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " lastname VARCHAR (50), " +
                " age SMALLINT not NULL, " +
                " PRIMARY KEY (id))";

        try {
            Util.getStatement().executeUpdate(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("таблица users создана ");
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE if exists users";
        try {
            Util.getStatement().executeUpdate(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("таблица users удалена ");
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = String.format("INSERT INTO users (name, lastname, age) values ('%s', '%s', %d)", name, lastName, age);
        try {
            Util.getStatement().executeUpdate(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("user c именем "+ name +" добавлен в таблицу ");
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id = " + id;
        try {
            int count = Util.getStatement().executeUpdate(SQL);
            if (count == 1) {
                System.out.println("user с id "+ id +" удалён ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM USERS";
        ResultSet rs;
        List<User> userList = new LinkedList<>();
        try {
            rs = Util.getStatement().executeQuery(SQL);
            while (rs.next()) {
                User user = new User(rs.getString("name"), rs.getString("lastname"), rs.getByte("age"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE FROM users";
        try {
            int count = Util.getStatement().executeUpdate(SQL);
            if (count != 0) {
                System.out.println("Таблица users очищена ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
