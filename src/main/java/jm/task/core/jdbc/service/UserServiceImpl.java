package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();


    public void createUsersTable() {
//        userDao.createUsersTable();
        userDaoHibernate.createUsersTable();

    }

    public void dropUsersTable() {
//        userDao.dropUsersTable();
        userDaoHibernate.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {

//        userDao.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
//        userDao.removeUserById(id);
        userDaoHibernate.removeUserById(id);

    }

    public List<User> getAllUsers() {

//        return userDao.getAllUsers();
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
//        userDao.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();

    }
}
