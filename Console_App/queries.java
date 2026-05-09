import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.sql.Statement;

public class queries {

    private static void displayAllCustomerNames(Connection connection){
        String queryToGetAllNames = "select name from customers";
        try(Statement stmt = connection.createStatement();
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

        String queryToGetBookingsAndName = "select co.Booking_ID, cu.Name from  car_orders co " + 
                                            "join customers cu on co.Cust_ID = cu.Cust_ID order by cu.Name;";
        try(Statement stmt = connection.createStatement();
            ResultSet resultOfBookings = stmt.executeQuery(queryToGetBookingsAndName)){
                System.out.println("Printing Bookings and Names");

                while(resultOfBookings.next()){
                    int bookingId = resultOfBookings.getInt("Booking_ID");
                    String name = resultOfBookings.getString("Name");
                    System.out.print("ID: " + bookingId + " Name: " + name+ "--");
                }
            } catch(SQLException e){
                System.out.println("Error fetching bookings and names" + e.getMessage());
            }
        System.out.println();//space for the next question
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
        displayBookings(connection);

        System.out.println("Enter the booking id:");

        if(scanner.hasNext()){
            int bookingID = scanner.nextInt();
            String query = "select co.Date_Of_Booking as Date_Of_Service, group_concat(s.Service order by s.Service separator ', ') " +
                            "as Services from service_bookings s join car_orders co on s.Booking_ID = co.Booking_ID where s.Booking_ID = ? " +
                            "group by co.Date_Of_Booking;";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, bookingID);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    Date dateOfService = resultSet.getDate("Date_Of_Service");
                    String services = resultSet.getString("Services");
                    System.out.println("Date of service: " + dateOfService + " Services: " + services);
                }
            } catch(SQLException e){
                System.out.println("Error executing query: " + e.getMessage());
            }
        }
        else {
            System.out.println("Enter a number");
        }
    }

    static void update_customer_information(Connection connection, Scanner scanner){
        System.out.println("update the information of a customer");
        displayAllCustomerNames(connection);
        System.out.println("Enter the name of the customer you want to update:");
        String oldName = scanner.nextLine();
        System.out.println("Enter the new name:");
        String newName = scanner.nextLine();

        String query = "update customers set Name = ? where Name = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, newName);
            statement.setString(2, oldName);
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Customer information updated successfully.");
            } else {
                System.out.println("No customer found with the name: " + oldName);
            }
        } catch(SQLException e){
            System.out.println("Error executing update: " + e.getMessage());
        }
    }

    static void count_bookings_per_customer_given_two_years(Connection connection, Scanner scanner){
        System.out.println("Count bookings per customer given two years");
        System.out.println("Enter the first year:");
        if(scanner.hasNextInt()){
            int firstYear = scanner.nextInt();
            System.out.println("Enter the second year:");
            if(scanner.hasNextInt()){
                int secondYear = scanner.nextInt();
                String query = "select cu.Name, count(co.Booking_ID) as Total_Bookings from customers cu " +
                               "join car_orders co on cu.Cust_ID = co.Cust_ID where year(co.Date_Of_Booking) between ? and ? " +
                               "group by cu.Name order by Total_Bookings desc;";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setInt(1, firstYear);
                    statement.setInt(2, secondYear);
                    ResultSet resultSet = statement.executeQuery();
                    while(resultSet.next()){
                        String name = resultSet.getString("Name");
                        int totalBookings = resultSet.getInt("Total_Bookings");
                        System.out.println(name + ": " + totalBookings + " bookings");
                    }
                } catch(SQLException e){
                    System.out.println("Error executing query: " + e.getMessage());
                }
            } else {
                System.out.println("Enter a valid year");
            }
        } else {
            System.out.println("Enter a valid year");
        }
    }

    static void calculate_total_revenue_for_a_specific_period(Connection connection, Scanner scanner){
        System.out.println("Calculate total revenue for a specific period");
        System.out.println("Enter the start date (YYYY-MM-DD):");
        String startDate = scanner.nextLine();
        System.out.println("Enter the end date (YYYY-MM-DD):");
        String endDate = scanner.nextLine();

        String query = "select sum(co.Total_Cost) as Total_Revenue from car_orders co where co.Date_Of_Booking between ? and ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                double totalRevenue = resultSet.getDouble("Total_Revenue");
                System.out.println("Total revenue from " + startDate + " to " + endDate + ": $" + totalRevenue);
            } else {
                System.out.println("No bookings found in the specified period.");
            }
        } catch(SQLException e){
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    static void update_return_date_of_a_car_for_a_specific_booking(Connection connection, Scanner scanner){
        System.out.println("Update the return date of a car for a specific booking");
        displayBookings(connection);
        System.out.println("Enter the booking ID:");
        if(scanner.hasNextInt()){
            int bookingID = scanner.nextInt();
            scanner.nextLine(); // Consume the newline
            System.out.println("Enter the new return date (YYYY-MM-DD):");
            String newReturnDate = scanner.nextLine();

            String query = "update car_orders set Return_Date = ? where Booking_ID = ?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, newReturnDate);
                statement.setInt(2, bookingID);
                int rowsUpdated = statement.executeUpdate();
                if(rowsUpdated > 0){
                    System.out.println("Return date updated successfully.");
                } else {
                    System.out.println("No booking found with ID: " + bookingID);
                }
            } catch(SQLException e){
                System.out.println("Error executing update: " + e.getMessage());
            }
        } else {
            System.out.println("Enter a valid booking ID");
        }
    }
}
