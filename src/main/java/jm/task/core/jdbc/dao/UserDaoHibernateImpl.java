package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactoryWithoutXML();
    }


    @Override
    public void createUsersTable() {

        String SQL = "CREATE TABLE if not exists users " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " lastname VARCHAR (50), " +
                " age SMALLINT not NULL, " +
                " PRIMARY KEY (id))";
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("таблица users создана ");
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        String SQL = "DROP TABLE if exists users";
        Session session = null;
        try {
            sessionFactory = Util.getSessionFactoryWithoutXML();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("таблица users удалена ");

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = null;
        try {


            session = Util.getSessionFactoryWithoutXML().openSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("user c именем " + name + " добавлен в таблицу ");
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = Util.getSessionFactoryWithoutXML().openSession();

            User user = session.get(User.class, id);
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("user с id " + id + " удалён ");
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>) Util.getSessionFactoryWithoutXML().openSession().createQuery("from users").list();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String SQL = "DELETE FROM users";
        Session session = null;
        try {
            session = Util.getSessionFactoryWithoutXML().openSession();
            session.beginTransaction();
            session.createQuery(SQL).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица users очищена ");
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

}

