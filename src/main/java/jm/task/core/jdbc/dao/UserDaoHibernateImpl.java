package jm.task.core.jdbc.dao;

import jakarta.persistence.Query;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        // не особо понятно что тут писать, таблица итак автоматически создается
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {
            Transaction tx = session.beginTransaction();

            session.createNativeQuery(sql).executeUpdate();

            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to drop table\n", e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {
            Transaction tx = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.persist(user);

            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user\n", e);
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {
            Transaction tx = session.beginTransaction();

            User user = session.get(User.class, id);
            session.delete(user);

            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user\n", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {
            Transaction tx = session.beginTransaction();

            users = session.createQuery("from User ", User.class).list();

            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get users list\n", e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = HibernateUtil
                .getSessionFactory()
                .openSession()) {
            Transaction tx = session.beginTransaction();

            session.createQuery("delete from User u")
                    .executeUpdate();

            tx.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to clean table\n", e);
        }
    }
}
