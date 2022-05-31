package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Rubina","Izipchuk", (byte) 21);
        userDaoHibernate.saveUser("Alexey","Kokoulin", (byte) 24);
        userDaoHibernate.saveUser("Vladislav","Shevchenko", (byte) 24);
        userDaoHibernate.saveUser("Darya","Kurochkina", (byte) 22);

        List<User> userList = userDaoHibernate.getAllUsers();
        for (User user : userList) {
           System.out.println(user);
        }

        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();

        /* UserServiceImpl userDaoJDBC = new UserServiceImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Rubina","Izipchuk", (byte) 21);
        userDaoJDBC.saveUser("Alexey","Kokoulin", (byte) 24);
        userDaoJDBC.saveUser("Vladislav","Shevchenko", (byte) 24);
        userDaoJDBC.saveUser("Darya","Kurochkina", (byte) 22);
        userDaoJDBC.removeUserById(2);

        List<User> users = userDaoJDBC.getAllUsers();
        for (User u : users) {
            System.out.println(u);
        }
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();*/
    }
}
