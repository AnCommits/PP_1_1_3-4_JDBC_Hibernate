package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
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

    private static int execute(String query, Object... objects) {
        try (Connection connection = UTIL.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] instanceof String) {
                    preparedStatement.setString(i + 1, (String) objects[i]);
                }
                if (objects[i] instanceof Byte) {
                    preparedStatement.setInt(i + 1, (byte) objects[i]);
                }
            }
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
        execute(CREATE_TABLE);
    }

    @Override
    public void dropUsersTable() {
        final String DELETE_TABLE = String.format("DROP TABLE IF EXISTS %s;", TABLE_NAME);
        execute(DELETE_TABLE);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        final String SAVE_USER = String.format("INSERT %s(%s, %s, %s) VALUES (?, ?, ?)",
                TABLE_NAME, COLUMN_NAME, COLUMN_LASTNAME, COLUMN_AGE);
        if (execute(SAVE_USER, name, lastName, age) == 1) {
            System.out.printf("User с именем – %s добавлен в базу данных%n", name);
        }
    }

    @Override
    public void removeUserById(long id) {
        final String REMOVE_BY_ID = String.format("DELETE FROM %s WHERE id = %d;", TABLE_NAME, id);
        execute(REMOVE_BY_ID);
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
        final String DELETE_ALL_ENTRIES = String.format("DELETE FROM %s;", TABLE_NAME);
        execute(DELETE_ALL_ENTRIES);
    }
}
