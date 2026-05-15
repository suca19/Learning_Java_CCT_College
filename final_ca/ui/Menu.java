package ui;

import java.util.Scanner;
import service.CarRentalService;

// Handles menu rendering and routes valid choices to service actions.
public class Menu {
    //We inject the shared Scanner and CarRentalService dependencies through the constructor.
    private  final Scanner scanner;
    private final CarRentalService service;

    // Builds the menu with shared scanner and service dependencies.
    public Menu(Scanner scanner, CarRentalService service) {
        this.scanner = scanner;
        this.service = service;
    }

    // Method to start the menu loop -> displays options, validates input, and dispatches actions.
    public void start() {
        boolean exit = false;
        System.out.println("Welcome to the Car Rental System!");
        System.out.println("Please select an option:");
        
        // Main menu loop: continues until the user chooses to exit.
        while(!exit){
            displayMenu();
            String input = scanner.nextLine().trim();

            // Validate that the input is a number and corresponds to a valid menu option.
            try {
                int userChoice = Integer.parseInt(input);
                if(userInputOptionValidator(userChoice)){
                    queryMenu(userChoice);
                    // If the user selects the exit option, we set exit to true to break the loop.
                    if(userChoice == 8){
                        exit = true;
                    }
                } else {
                    System.out.println("Invalid option. Please try again."); 
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Prints all available menu options
    private void displayMenu() {

        // We define the menu options in an array for easy maintenance and display.
        String [] options = {"1. Find bookings by customer name", "2. Find customers who rented for more than X days", "3. List all services for a given booking","4. update the infornation of a customer", 
                            "5. Count bookings per customer given two years", "6. Calculate total revenue for a specific period", "7. update the return date of a car for a specific booking", "8. Exit"};
        
        System.out.println("-------------------Menu Options-----------------");
        System.out.println("|                                              |");
        System.out.println("|                                              |");

        for (String option : options) {
            System.out.println("|" + option);
        }
        System.out.println("|                                              |");
        System.out.println("------------------------------------------------");
    }

    // Validates that the selected menu option exists.
    private boolean userInputOptionValidator(int userChoice){ 
        return userChoice >= 1 && userChoice <= 8;
    }

    // Dispatches each valid option to the matching service method.
    private void queryMenu(int userChoice){
        switch(userChoice){
            case 1 -> service.findBookingsByCustomerName();
            case 2 -> service.findCustomersWhoRentedForMoreThanXDays();
            case 3 -> service.listAllServicesForAGivenBooking();
            case 4 -> service.updateCustomerInformation();
            case 5 -> service.countBookingsPerCustomerGivenTwoYears();
            case 6 -> service.calculateTotalRevenueForASpecificPeriod();
            case 7 -> service.updateReturnDateOfACarForASpecificBooking();
            default -> System.out.println("Exiting.");
        }
    }
}
