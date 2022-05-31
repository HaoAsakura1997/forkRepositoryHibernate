package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    static SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(User.class)
            .buildSessionFactory();
    Session session;
    Transaction transactional;

    @Override
    public void createUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            transactional = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users ( ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(255) NOT NULL, LAST_NAME VARCHAR(255) NOT NULL, AGE TINYINT UNSIGNED NOT NULL, PRIMARY KEY (ID) )").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            transactional.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            transactional = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            transactional.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = sessionFactory.getCurrentSession();
            transactional = session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            transactional.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            transactional.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.getCurrentSession();
        transactional = session.beginTransaction();
        List<User> result = session.createQuery("FROM User", User.class).getResultList();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = sessionFactory.getCurrentSession();
            transactional = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE Users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            transactional.rollback();
        }
    }
}
