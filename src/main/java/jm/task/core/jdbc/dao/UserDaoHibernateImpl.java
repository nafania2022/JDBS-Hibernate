package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sessionFactory = Util.getConnectionHibernate();
    Session session;


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users  " +
                "(id serial PRIMARY KEY, " +
                " firstName VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age INTEGER )";
//        String sql = "CREATE SEQUENCE hibernate_sequence START 1";
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createNativeQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users  ";
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createNativeQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        if (user != null) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("Пользователь успешно удален.");
        } else {
            System.out.println("Пользователь с ID " + id + " не найден.");
        }

    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.openSession();
//        return session.createQuery("SELECT u FROM User u", User.class).getResultList();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> users = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(users);
        Query<User> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE  Users  ";
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createNativeQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();


    }
}
