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

            	String driver = "org.postgresql.Driver";
            	String url = "jdbc:postgresql://databas.c7u228yiax78.eu-north-1.rds.amazonaws.com:5432/web_database";
                //String url = "jdbc:postgresql://localhost:5432/web_database";
            	String user = "web_user";
            	String password = "web_password";
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
