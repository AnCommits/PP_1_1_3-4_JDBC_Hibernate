package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final String TABLE_NAME = "users";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LASTNAME = "lastName";
    private static final String AGE = "age";

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {

        final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                        "(" +
                        ID + " BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                        NAME + " VARCHAR(30), " +
                        LASTNAME + " VARCHAR(30), " +
                        AGE + " TINYINT" +
                        ")";

        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery(CREATE_TABLE, User.class);
//            session.createSQLQuery(CREATE_TABLE).addEntity(User.class);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            if (user != null) {
                session.remove(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        final String DELETE_ALL_ENTRIES = String.format("DELETE FROM %s;", TABLE_NAME);

        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.createNativeQuery(DELETE_ALL_ENTRIES);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }
}
