package Connecting_to_my_db.src;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);

        try {
            // Ensure table exists
            userDao.createTable();
            System.out.println("✅ Table ready");
            
            // Insert a user
            System.out.println("Enter name and email to insert a new user:");
            String name = scanner.nextLine();
            String email = scanner.nextLine();

            User newUser = new User(name, email);
            userDao.insert(newUser);
            System.out.println("✅ Inserted user with ID: " + newUser.getId());
            
            // List all users
            List<User> allUsers = userDao.findAll();
            System.out.println("\n📋 All users:");
            for (User u : allUsers) {
                System.out.printf("  %d | %s | %s | %s%n",
                    u.getId(), u.getName(), u.getEmail(), u.getCreatedAt());
            }
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseConfig.closePool(); // important for applications that exit
        }
        scanner.close();
    }
}