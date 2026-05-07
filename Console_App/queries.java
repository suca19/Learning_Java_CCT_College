import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.sql.Statement;

public class queries {

    private static void displayAllCustomerNames(Connection conn){
        String queryToGetAllNames = "SELECT name from customers";
        try(Statement stmt = conn.createStatement();
            ResultSet resultOfNames = stmt.executeQuery(queryToGetAllNames)){
                System.out.println("Printing Customer Names");
                int count = 1;
            while (resultOfNames.next()) {
            System.out.println(count + ". " + resultOfNames.getString("Name"));
            count++;

        }
    } catch (SQLException e){
        System.out.println("Error fetching customer names" + e.getMessage());
    }
}

    public static void find_bookings_by_customer_name(Connection connection, Scanner scanner){
        
        displayAllCustomerNames(connection);
        System.out.println("Enter the customer's name:");
        String customerName = scanner.nextLine();
        String query = "SELECT co.Booking_ID, cu.Name, co.Car_Type, co.Date_Of_Booking, co.Return_Date\n" + 
                        "FROM car_orders co\n" + 
                        "JOIN customers cu ON co.Cust_ID = cu.Cust_ID\n" + 
                        "WHERE cu.Name LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customerName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Process the results
                int bookingId = resultSet.getInt("Booking_ID");
                String name = resultSet.getString("Name");
                String carType = resultSet.getString("Car_Type");
                Date dateBooked = resultSet.getDate("Date_Of_Booking");
                Date returnDate = resultSet.getDate("Return_Date");

                System.out.printf("%-10d %-20s %-15s %-15s %-15s%n", bookingId, name, carType, dateBooked.toString(), returnDate.toString());
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    
    
    
}
