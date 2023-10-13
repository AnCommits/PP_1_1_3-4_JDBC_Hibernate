package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBCImpl implements UserDao {

    private static final String TABLE_NAME = "users";

    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    """
                                                
                            (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                name VARCHAR(30),
                                lastName VARCHAR(30),
                                age TINYINT
                            );
                            """;

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

//    public void createUsersTable() {
//
//    }
//
//    public void dropUsersTable() {
//
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//
//    }
//
//    public void removeUserById(long id) {
//
//    }
//
//    public List<User> getAllUsers() {
//        return null;
//    }
//
//    public void cleanUsersTable() {
//
//    }
}
