package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String PATH_TO_DB_PROPERTIES = "src/main/resources/database.properties";

    private static Connection connection;

    public static Connection getConnection() {
        try (InputStream inputStream = new FileInputStream(PATH_TO_DB_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
