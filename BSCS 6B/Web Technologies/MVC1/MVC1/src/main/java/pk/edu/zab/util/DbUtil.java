package pk.edu.zab.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {

            	String driver = "com.mysql.jdbc.Driver";
            	String url = "jdbc:mysql://127.0.0.1:3306/webtech";
            	String user = "root";
            	String password = "asad";
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            return connection;
        }
    }
}
