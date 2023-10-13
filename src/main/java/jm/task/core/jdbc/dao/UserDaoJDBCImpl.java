package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String TABLE_NAME = "users";

    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LASTNAME = "lastName";
    private static final String COLUMN_AGE = "age";

    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + '\n' +
                    "(\n" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
                    COLUMN_NAME + " VARCHAR(30),\n" +
                    COLUMN_LASTNAME + " VARCHAR(30),\n" +
                    COLUMN_AGE + " TINYINT\n" +
                    ");";

    private Connection connection;

    public UserDaoJDBCImpl() {
    }

    private Statement getStatement() {
        Statement statement = null;
        try {
            connection = new Util().getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    public void createUsersTable() {
        Statement statement = getStatement();
        if (statement != null) {
            try {
                statement.executeUpdate(CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String SAVE_USER = String.format("INSERT %s(%s, %s, %s)\nVALUES ('%s', '%s', %d);",
                TABLE_NAME, COLUMN_NAME, COLUMN_LASTNAME, COLUMN_AGE, name, lastName, age);
        Statement statement = getStatement();
        if (statement != null) {
            try {
                if (statement.executeUpdate(SAVE_USER) == 1) {
                    System.out.printf("User с именем – %s добавлен в базу данных%n", name);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
