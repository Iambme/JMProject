package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.RollbackException;
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
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery(SQL).executeUpdate();
            tx.commit();
            System.out.println("таблица users создана ");
        } catch (IllegalStateException | RollbackException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String SQL = "DROP TABLE if exists users";
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createSQLQuery(SQL).executeUpdate();
            tx.commit();
            System.out.println("таблица users удалена ");
        } catch (IllegalStateException | RollbackException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            tx.commit();
            System.out.println("user c именем " + name + " добавлен в таблицу ");
        } catch (IllegalStateException | RollbackException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }

        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            tx.commit();
            System.out.println("user с id " + id + " удалён ");
        } catch (IllegalStateException | RollbackException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = (List<User>) sessionFactory.openSession().createQuery("from users").list();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String SQL = "DELETE FROM users";
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createQuery(SQL).executeUpdate();
            tx.commit();
            System.out.println("Таблица users очищена ");
        } catch (IllegalStateException | RollbackException e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }

}

