package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Data access layer-> contains SQL queries, only operations for reads and updates.
public class Queries {

    // Row projection for booking search results.
    public static class BookingRow {
        private final int bookingId;
        private final String name;
        private final String carType;
        private final Date dateOfBooking;
        private final Date returnDate;

        // We build the BookingRow with all the fields returned by the query, and we provide getters for each field to allow access to the data.
        public BookingRow(int bookingId, String name, String carType, Date dateOfBooking, Date returnDate) {
            this.bookingId = bookingId;
            this.name = name;
            this.carType = carType;
            this.dateOfBooking = dateOfBooking;
            this.returnDate = returnDate;
        }

        // Getters for each field to allow access to the data in the BookingRow.
        public int getBookingId() {
            return bookingId;
        }
        
        public String getName() {
            return name;
        }

        public String getCarType() {
            return carType;
        }

        public Date getDateOfBooking() {
            return dateOfBooking;
        }

        public Date getReturnDate() {
            return returnDate;
        }
    }

    // Row projection for rental duration report.
    public static class RentedCustomerRow {
        private final String name;
        private final String carType;
        private final int rentedDays;

        // We build the RentedCustomerRow with all the fields returned by the query, and we provide getters for each field to allow access to the data.
        public RentedCustomerRow(String name, String carType, int rentedDays) {
            this.name = name;
            this.carType = carType;
            this.rentedDays = rentedDays;
        }

        // Getters for each field to allow access to the data in the RentedCustomerRow.
        public String getName() {
            return name;
        }

        public String getCarType() {
            return carType;
        }

        public int getRentedDays() {
            return rentedDays;
        }
    }

    // Row projection for grouped services by booking.
    public static class ServiceBookingRow {
        private final Date dateOfService;
        private final String services;

        // We build the ServiceBookingRow with all the fields returned by the query,
        // and we provide getters for each field to allow access to the data.
        public ServiceBookingRow(Date dateOfService, String services) {
            this.dateOfService = dateOfService;
            this.services = services;
        }

        public Date getDateOfService() {
            return dateOfService;
        }

        public String getServices() {
            return services;
        }
    }

    // Row projection for booking counts by customer.
    public static class CustomerBookingCountRow {
        private final String name;
        private final int totalBookings;

        // We build the CustomerBookingCountRow with all the fields returned by the query, 
        // and we provide getters for each field to allow access to the data.
        public CustomerBookingCountRow(String name, int totalBookings) {
            this.name = name;
            this.totalBookings = totalBookings;
        }

        public String getName() {
            return name;
        }

        public int getTotalBookings() {
            return totalBookings;
        }
    }

    // Finds bookings by customer name pattern.
    public static List<BookingRow> findBookingsByCustomerName(Connection connection, String customerName) {
        String query = "select co.Booking_ID, cu.Name, co.Car_Type, co.Date_Of_Booking, co.Return_Date " +
                "from car_orders co " +
                "join customers cu on co.Cust_ID = cu.Cust_ID " +
                "where cu.Name like ?";
        List<BookingRow> bookings = new ArrayList<>();

        // We use a prepared statement to safely inject the customer name parameter into the query, 
        // and we execute the query to retrieve the results. 
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customerName);
            ResultSet resultSet = statement.executeQuery();
            
            // We loop through the ResultSet and build a list of BookingRow objects to return to the caller.
            while (resultSet.next()) {
                bookings.add(new BookingRow(
                        resultSet.getInt("Booking_ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Car_Type"),
                        resultSet.getDate("Date_Of_Booking"),
                        resultSet.getDate("Return_Date")));
            }
            return bookings;
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            return bookings;
        }
    }

    // Returns customers whose rental duration is above the given day threshold.
    public static List<RentedCustomerRow> findCustomersWhoRentedForMoreThanXDays(Connection connection, int daysRented) {
        String query = "select cu.Name, co.Car_Type, datediff(co.Return_Date, co.Date_Of_Booking) as Rented_Days " +
                "from car_orders co join customers cu on co.Cust_ID = cu.Cust_ID " +
                "where datediff(co.Return_Date, co.Date_Of_Booking) > ? order by Rented_Days asc";
        List<RentedCustomerRow> rentals = new ArrayList<>();

        // We use a prepared statement to safely inject the daysRented parameter into the query,
        // and we execute the query to retrieve the results.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, daysRented);
            ResultSet resultSet = statement.executeQuery();

            // We loop through the ResultSet and build a list of RentedCustomerRow objects to return to the caller.
            while (resultSet.next()) {
                rentals.add(new RentedCustomerRow(
                        resultSet.getString("Name"),
                        resultSet.getString("Car_Type"),
                        resultSet.getInt("Rented_Days")));
            }
            return rentals;
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            return rentals;
        }
    }

    // Lists all services linked to a specific booking.
    public static List<ServiceBookingRow> listAllServicesForAGivenBooking(Connection connection, int bookingID) {
        String query = "select co.Date_Of_Booking as Date_Of_Service, group_concat(s.Service order by s.Service separator ', ') " +
                "as Services from service_bookings s join car_orders co on s.Booking_ID = co.Booking_ID where s.Booking_ID = ? " +
                "group by co.Date_Of_Booking;";
        List<ServiceBookingRow> services = new ArrayList<>();

        // We use a prepared statement to safely inject the bookingID parameter into the query, 
        // and we execute the query to retrieve the results.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookingID);
            ResultSet resultSet = statement.executeQuery();

            // We loop through the ResultSet and build a list of ServiceBookingRow objects to return to the caller.
            while (resultSet.next()) {
                services.add(new ServiceBookingRow(
                        resultSet.getDate("Date_Of_Service"),
                        resultSet.getString("Services")));
            }
            return services;
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            return services;
        }
    }

    // Updates a customer name and returns affected row count.
    public static int updateCustomerInformation(Connection connection, String oldName, String newName) {
        String query = "update customers set Name = ? where Name = ?";

        // We use a prepared statement to safely inject the newName and oldName parameters into the query,
        // and we execute the update to change the customer's name. 
        // We return the number of rows affected by the update to indicate success or failure to the caller.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newName);
            statement.setString(2, oldName);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error executing update: " + e.getMessage());
            return 0;
        }
    }

    // Counts bookings per customer within the requested year range.
    public static List<CustomerBookingCountRow> countBookingsPerCustomerGivenTwoYears(Connection connection, int firstYear, int secondYear) {
        String query = "select cu.Name, count(co.Booking_ID) as Total_Bookings from customers cu " +
                "join car_orders co on cu.Cust_ID = co.Cust_ID where year(co.Date_Of_Booking) between ? and ? " +
                "group by cu.Name order by Total_Bookings desc;";
        List<CustomerBookingCountRow> bookings = new ArrayList<>();

        // We use a prepared statement to safely inject the firstYear and secondYear parameters into the query,
        // and we execute the query to retrieve the results.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, firstYear);
            statement.setInt(2, secondYear);
            ResultSet resultSet = statement.executeQuery();

            // We loop through the ResultSet and build a list of CustomerBookingCountRow objects to return to the caller.
            while (resultSet.next()) {
                bookings.add(new CustomerBookingCountRow(
                        resultSet.getString("Name"),
                        resultSet.getInt("Total_Bookings")));
            }
            return bookings;
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            return bookings;
        }
    }

    // Calculates total revenue in a period from base rent and booked services.
    public static Double calculateTotalRevenueForASpecificPeriod(Connection connection, String startDate, String endDate) {
        String query = "select sum(total_cost) as Total_Revenue from (" +
                "select " +
                "co.Booking_ID, " +
                "datediff(co.Return_Date, co.Date_Of_Booking) * cd.Base_Rent + coalesce(service_total, 0) as total_cost " +
                "from car_orders co " +
                "join car_details cd on co.Car_Type = cd.Car_Type " +
                "left join (" +
                "select Booking_ID, sum(Service_Charge) as service_total " +
                "from service_bookings sb " +
                "join service_details sd on sb.Service = sd.Service " +
                "group by Booking_ID" +
                ") services on co.Booking_ID = services.Booking_ID " +
                "where co.Date_Of_Booking between ? and ?" +
                ") as booking_costs;";

        // We use a prepared statement to safely inject the startDate and endDate parameters into the query,
        // and we execute the query to retrieve the total revenue.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            ResultSet resultSet = statement.executeQuery();

            // We check if the ResultSet has a row (it should have one row with the total revenue), and we retrieve the total revenue value.
            if (resultSet.next()) {
                double totalRevenue = resultSet.getDouble("Total_Revenue");
                if (resultSet.wasNull()) {
                    return null;
                }
                return totalRevenue;
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return null;
    }

    // Updates the return date for a booking and returns affected row count.
    public static int updateReturnDateOfACarForASpecificBooking(Connection connection, int bookingID, String newReturnDate) {
        String query = "update car_orders set Return_Date = ? where Booking_ID = ?";

        // We use a prepared statement to safely inject the newReturnDate and bookingID parameters into the query,
        // and we execute the update to change the return date for the specified booking.
        // We return the number of rows affected by the update to indicate success or failure to the caller.
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newReturnDate);
            statement.setInt(2, bookingID);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error executing update: " + e.getMessage());
            return 0;
        }
    }
}