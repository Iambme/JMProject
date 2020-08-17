package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        SessionFactory sessionFactory;
        String SQL = "CREATE TABLE if not exists users " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " lastname VARCHAR (50), " +
                " age SMALLINT not NULL, " +
                " PRIMARY KEY (id))";
        try {
            sessionFactory = Util.getSessionFactoryWithoutXML();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("таблица users создана ");
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String SQL = "DROP TABLE if exists users";
        SessionFactory sessionFactory;
        try {
            sessionFactory = Util.getSessionFactoryWithoutXML();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("таблица users удалена ");
            session.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactoryWithoutXML().openSession();
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        System.out.println("user c именем " + name + " добавлен в таблицу ");
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactoryWithoutXML().openSession();
        User user = Util.getSessionFactoryWithoutXML().openSession().get(User.class, id);
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        System.out.println("user с id " + id + " удалён ");
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>) Util.getSessionFactoryWithoutXML().openSession().createQuery("from users").list();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String SQL = "DELETE FROM users";
        Session session = Util.getSessionFactoryWithoutXML().openSession();
        session.beginTransaction();
        session.createQuery(SQL).executeUpdate();
        session.getTransaction().commit();
        System.out.println("Таблица users очищена ");
        session.close();
    }

}

