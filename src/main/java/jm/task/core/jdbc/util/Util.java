package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД



    public static Connection getConnectionJDBC() throws SQLException, ClassNotFoundException {
        String DB_USERNAME = "postgres";
        String DB_PASSWORD = "postgres";
        String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(DB_URL,DB_USERNAME, DB_PASSWORD);
    }
    public static   SessionFactory getConnectionHibernate() {
        Configuration conf = new Configuration();
        SessionFactory sessionFactory;
        conf.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        conf.setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/postgres");
        conf.setProperty("hibernate.connection.username","postgres");
        conf.setProperty("hibernate.connection.password","postgres");
        conf.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        conf.addAnnotatedClass(User.class);
        sessionFactory = conf.buildSessionFactory();
        return sessionFactory;
    }
}
