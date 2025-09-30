package com.plantcare.plantcare_api.infrasctructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public static final String URL = DATABASE_URL;
    public static final String USER = DATABASE_USER;
    public static final String PASSWORD = DATABASE_PASSWORD;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}