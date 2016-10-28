package com.consulatations.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DB {
    private static final String url = "jdbc:mysql://localhost:3306/?user=root";
    private static final String user_name = "root";
    private static final String user_password = "    ";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user_name, user_password);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
