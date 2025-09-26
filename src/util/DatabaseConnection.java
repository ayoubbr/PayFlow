package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;


    static {
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/payflow_db";

        try {
//          deprecated
//          Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Problem with the database connection: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

}
