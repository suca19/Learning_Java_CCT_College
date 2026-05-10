package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Connection to my Database car_rental
public class Pool {
    // Creates and returns a DB connection. Returns null if connection fails.
    private static Connection connectToDatabase() {
        
        final String url = "jdbc:mysql://localhost:3306/car_rental";
        final String user = "root";
        final String password = "root123";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to db");
                return connection;
            } catch( SQLException e) {
            System.out.println("Connection failed " + e.getMessage());
        }
        return null;
    }

      // Public accessor used by the app
      public static Connection getConnection() {
        return connectToDatabase();
     }
}
