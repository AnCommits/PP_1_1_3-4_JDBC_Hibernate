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

    private static final String PATH_TO_PROPERTIES = "src/main/resources/database.properties";

    public Connection getConnection() {
        try (InputStream fis = new FileInputStream(PATH_TO_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(fis);
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            return DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
