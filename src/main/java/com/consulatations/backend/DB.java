package com.consulatations.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by Роман on 05.02.2016.
 */
public class DB {
    static final String url = "jdbc:mysql://localhost:3306/?user=root";
    static final String user_name = "root";
    static final String user_password = "    ";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {

                return DriverManager.getConnection(url, user_name, user_password);
            } catch (SQLException e) {
                Logger.getGlobal().severe("can't connect to database (" + url + ")");
                throw e;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getGlobal().severe("Can't load mysql driver");
            System.out.println("Connection to DB error!");
        }
        return null;
    }
}
