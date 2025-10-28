//package com.example;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DbUtil {
//    private static Connection connection = null;
//
//    public static Connection getConnection() {
//        if (connection != null)
//            return connection;
//        else {
//            try {
//                String driver = "org.postgresql.Driver";
//                String url = "jdbc:postgresql://localhost:5432/web_database";
//                String user = "web_user";
//                String password = "web_password";
//                Class.forName(driver);
//                connection = DriverManager.getConnection(url, user, password);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("Connected to the database successfully.");
//
//            return connection;
//
//        }
//    }
//}




package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/web_database";
    private static final String USER = "web_user";
    private static final String PASSWORD = "web_password";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
