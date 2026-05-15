package service;

import dao.Queries;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

// Service layer: handles input/output orchestration and delegates SQL work to DAO.
public class CarRentalService {
    private final Connection connection;
    private final Scanner scanner;

    // Builds the service with shared DB connection and console scanner.
    public CarRentalService(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    // Menu action: search bookings by customer name and print table output.
    public void findBookingsByCustomerName() {
        String customerName = readRequiredLine("Enter the customer's name:");
        List<Queries.BookingRow> bookings = Queries.findBookingsByCustomerName(connection, customerName);

        if (bookings.isEmpty()) {
            System.out.println("No bookings found for " + customerName + ".");
            return;
        }

        System.out.println("--------------------------------------------------------------------------------");
        System.out.printf("%-12s %-15s %-15s %-20s %-20s%n", "Booking ID", "Name", "Car Type", "Date of booking", "Return Date");
        System.out.println("--------------------------------------------------------------------------------");
        for (Queries.BookingRow booking : bookings) {
            System.out.printf("%-12s %-15s %-15s %-20s %-20s%n",
                    booking.getBookingId(),
                    booking.getName(),
                    booking.getCarType(),
                    booking.getDateOfBooking(),
                    booking.getReturnDate());
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    // Menu action -> print customers who rented longer than X days.
    public void findCustomersWhoRentedForMoreThanXDays() {
        System.out.println("let's find customers who rented for more than X days a car");
        int daysRented = readInt("Give us the days please.");
        List<Queries.RentedCustomerRow> rentals = Queries.findCustomersWhoRentedForMoreThanXDays(connection, daysRented);

        // If the query returns an empty list, we print a message and return early to avoid printing an empty table.
        if (rentals.isEmpty()) {
            System.out.println("No customers matched that filter.");
            return;
        }

        printDivider();
        System.out.printf("%-20s %-20s %-20s%n", "Name", "Car Type", "Rented Days");
        printDivider();
        // We loop through the results and print each row in a formatted manner.
        for (Queries.RentedCustomerRow rental : rentals) {
            System.out.printf("%-20s %-20s %-20s%n", rental.getName(), rental.getCarType(), rental.getRentedDays());
        }
        printDivider();
    }

    // Menu action -> show grouped services for one booking.
    public void listAllServicesForAGivenBooking() {
        int bookingId = readInt("Enter the booking id:");
        List<Queries.ServiceBookingRow> services = Queries.listAllServicesForAGivenBooking(connection, bookingId);

        if (services.isEmpty()) {
            System.out.println("No services found for booking ID: " + bookingId);
            return;
        }

        printDivider();
        System.out.printf("%-20s %-40s%n", "Date of service", "Services");
        printDivider();
        // We print each service row with the date and a description of the services provided.
        for (Queries.ServiceBookingRow service : services) {
            System.out.printf("%-20s %-40s%n", service.getDateOfService(), service.getServices());
        }
        printDivider();
    }

    // Menu action -> rename a customer.
    public void updateCustomerInformation() {
        String oldName = readRequiredLine("Enter the name of the customer you want to update:");
        String newName = readRequiredLine("Enter the new name:");
        int rowsUpdated = Queries.updateCustomerInformation(connection, oldName, newName);

        if (rowsUpdated > 0) {
            System.out.println("Customer information updated successfully.");
        } else {
            System.out.println("No customer found with the name: " + oldName);
        }
    }

    // Menu action -> list booking counts for customers between two years.
    public void countBookingsPerCustomerGivenTwoYears() {
        System.out.println("Count bookings per customer given two years");
        int firstYear = readInt("Enter the first year:");
        int secondYear = readInt("Enter the second year:");
        List<Queries.CustomerBookingCountRow> bookings = Queries.countBookingsPerCustomerGivenTwoYears(connection, firstYear, secondYear);

        // If the query returns an empty list, we print a message and return early to avoid printing an empty table.
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for the selected years.");
            return;
        }

        printDivider();
        System.out.printf("%-30s %-15s%n", "Name", "Bookings");
        printDivider();
        // We loop through the results and print each customer's name and their total booking count in a formatted manner.
        for (Queries.CustomerBookingCountRow booking : bookings) {
            System.out.printf("%-30s %-15s%n", booking.getName(), booking.getTotalBookings());
        }
        printDivider();
    }

    // Menu action -> calculate and print total revenue for a date range.
    public void calculateTotalRevenueForASpecificPeriod() {
        System.out.println("Calculate total revenue for a specific period");
        String startDate = readDate("Enter the start date (YYYY-MM-DD):");
        String endDate = readDate("Enter the end date (YYYY-MM-DD):");
        Double totalRevenue = Queries.calculateTotalRevenueForASpecificPeriod(connection, startDate, endDate);

        // If the query returns null, it means there were no bookings in that period, so we print a message and return early.
        if (totalRevenue == null) {
            System.out.println("No bookings found in the specified period.");
            return;
        } 
        System.out.println("Total revenue from " + startDate + " to " + endDate + ": $" + totalRevenue);
    }

    // Menu action -> update return date for an existing booking.
    public void updateReturnDateOfACarForASpecificBooking() {
        int bookingId = readInt("Enter the booking ID:");
        String newReturnDate = readDate("Enter the new return date (YYYY-MM-DD):");
        int rowsUpdated = Queries.updateReturnDateOfACarForASpecificBooking(connection, bookingId, newReturnDate);

        // If rowsUpdated is greater than 0, it means the update was successful, so we print a success message. Otherwise, we print a message indicating that no booking was found with the provided ID.
        if (rowsUpdated > 0) {
            System.out.println("Return date updated successfully.");
        } else {
            System.out.println("No booking found with ID: " + bookingId);
        }
    }

    // Reads a non empty string value from console.
    private String readRequiredLine(String prompt) {
        // We loop until the user provides a non-empty input, printing the prompt each time.
        while (true) {
            System.out.println(prompt);
            String value = scanner.nextLine().trim();

            // If the input is not empty, we return it. Otherwise, we print an error message and prompt again.
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Enter a valid value");
        }
    }

    // Reads a date string and validates the YYYY-MM-DD structure.
    private String readDate(String prompt) {
        // We loop until the user provides a valid date string. 
        // We attempt to parse the input as a LocalDate, and if it succeeds, we return the original string. 
        // If parsing fails, we catch the exception and prompt the user again.
        while (true) {
            System.out.println(prompt);
            String value = scanner.nextLine().trim();

            try {
                // Converts the text into a date to verify it matches a real YYYY-MM-DD value.
                LocalDate.parse(value);
                return value;
            } catch (DateTimeParseException e) {
                System.out.println("Enter a valid date in YYYY-MM-DD format");
            }
        }
    }

    // Reads an integer value, retrying until valid input is entered.
    private int readInt(String prompt) {
        // We loop until the user provides a valid integer. 
        // We attempt to parse the input as an integer, and if it succeeds, we return the integer value. 
        // If parsing fails, we catch the exception and prompt the user again.
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
            }
        }
    }

    // Shared visual separator.
    private void printDivider() {
        System.out.println("--------------------------------------------------------------------------------");
    }
}