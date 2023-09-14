package com.codecrafters.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// temporary has to be updated into better code...
public class DataSourceConnectionFactory {
    public static Connection getConnection() throws SQLException {
        String url="jdbc:mysql://localhost:3306/testDB";
        String user="root";
        String password="root12345";
        return DriverManager.getConnection(url, user, password);
    }
}
