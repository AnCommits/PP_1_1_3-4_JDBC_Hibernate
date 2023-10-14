package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String TABLE_NAME = "users";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LASTNAME = "lastName";
    private static final String COLUMN_AGE = "age";

    private static final Util UTIL = new Util();

    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                        "(" +
                        COLUMN_ID + " BIGINT PRIMARY KEY AUTO_INCREMENT," +
                        COLUMN_NAME + " VARCHAR(30)," +
                        COLUMN_LASTNAME + " VARCHAR(30)," +
                        COLUMN_AGE + " TINYINT" +
                        ");";

        try (Connection connection = UTIL.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        final String SAVE_USER = String.format("INSERT %s(%s, %s, %s) VALUES ('%s', '%s', %d);",
                TABLE_NAME, COLUMN_NAME, COLUMN_LASTNAME, COLUMN_AGE, name, lastName, age);

        try (Connection connection = UTIL.getConnection();
             Statement statement = connection.createStatement()) {
            if (statement.executeUpdate(SAVE_USER) == 1) {
                System.out.printf("User с именем – %s добавлен в базу данных%n", name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        final String GET_ALL_USERS = String.format("SELECT * FROM %s;", TABLE_NAME);

        List<User> listOfAllUsers = new ArrayList<>();

        try (Connection connection = UTIL.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()) {
                User user = new User(resultSet.getString(COLUMN_NAME),
                        resultSet.getString(COLUMN_LASTNAME),
                        resultSet.getByte(COLUMN_AGE));
                user.setId(resultSet.getLong(COLUMN_ID));
                listOfAllUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfAllUsers;
    }

    @Override
    public void cleanUsersTable() {

    }
}
