import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.sql.Statement;

public class queries {

    private static void displayAllCustomerNames(Connection conn){
        String queryToGetAllNames = "select name from customers";
        try(Statement stmt = conn.createStatement();
            ResultSet resultOfNames = stmt.executeQuery(queryToGetAllNames)){
                System.out.println("Printing Customer Names");
                
            while (resultOfNames.next()) {
            System.out.print( "- " + resultOfNames.getString("Name"));
        }
    } catch (SQLException e){
        System.out.println("Error fetching customer names" + e.getMessage());
    }
    System.out.println();//space for the next question
    }

    private static void displayBookings(Connection connection){
        String queryToGetBookingsAndName = ""
    }

    public static void find_bookings_by_customer_name(Connection connection, Scanner scanner){
        
        displayAllCustomerNames(connection);
        System.out.println("Enter the customer's name:");
        String customerName = scanner.nextLine();
        String query = "select co.Booking_ID, cu.Name, co.Car_Type, co.Date_Of_Booking, co.Return_Date " + 
                        "from car_orders co " + 
                        "join customers cu on co.Cust_ID = cu.Cust_ID " + 
                        "where cu.Name like ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customerName);
            ResultSet resultSet = statement.executeQuery();
            String spacer = "        ";
            System.out.println("Booking ID"+spacer+"Name"+spacer+"Car Type"+spacer+"Date of booking"+spacer+"Retun Date");
            while (resultSet.next()) {
                // Process the results
                int bookingId = resultSet.getInt("Booking_ID");
                String name = resultSet.getString("Name");
                String carType = resultSet.getString("Car_Type");
                Date dateBooked = resultSet.getDate("Date_Of_Booking");
                Date returnDate = resultSet.getDate("Return_Date");
   
                System.out.println(bookingId+spacer+name+spacer+carType+spacer+dateBooked+spacer+returnDate);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    public static void find_customers_who_rented_for_more_than_x_days(Connection connection, Scanner scanner){
        System.out.println("let's find customers who rented for more than X days a car");
        System.out.println("Give us the days please.");
        
        if(scanner.hasNext()){
            int daysRented = scanner.nextInt();
            String query = "select cu.Name, co.Car_Type, datediff(co.Return_Date, co.Date_Of_Booking) as Rented_Days " +
                           "from car_orders co join customers cu on co.Cust_ID = " + 
                           "cu.Cust_ID where datediff(co.Return_Date, co.Date_Of_Booking) > ? order by Rented_Days asc";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, daysRented);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    String name = resultSet.getString("Name");
                    String carType = resultSet.getString("Car_Type");
                    int rentedDays = resultSet.getInt("Rented_Days");
                    
                    System.out.println(name + " "+carType + " " + rentedDays);
                }
                

            } catch(SQLException e){
                System.out.println("Error executing query: " + e.getMessage());

            }
        } else {
            System.out.println("Enter a number");
        }
    }
    
    public static void list_all_services_for_a_given_booking(Connection connection, Scanner scanner){
        System.out.println("list all services of a booking");

    }
}
