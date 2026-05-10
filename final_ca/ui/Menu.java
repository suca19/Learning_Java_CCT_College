package ui;

import java.util.Scanner;

import service.CarRentalService;

// Handles menu rendering and routes valid choices to service actions.
public class Menu {
    private Scanner scanner;
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
        
        while(!exit){
            displayMenu();
            String input = scanner.nextLine().trim();

            try {
                int userChoice = Integer.parseInt(input);
                if(userInputOptionValidator(userChoice)){
                    queryMenu(userChoice);
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

        String [] options = {"1. Find bookings by customer name", "2. Find customers who rented for more than X days", "3. List all services for a given booking","4. update the infornation of a customer", 
                            "5. Count bookings per customer given two years", "6. Calculate total revenue for a specific period", "7. update the return date of a car for a specific booking", "8. Exit"};
        
        System.out.println("-------------------Menu Options-----------------");
        System.out.println("|                                              |");
        System.out.println("|                                              |");

        for(int i = 0; i < options.length; i++){
            System.out.println("|" + options[i]); 

            }
        System.out.println("|                                              |");
        System.out.println("------------------------------------------------");
    }

    // Validates that the selected menu option exists.
    private boolean userInputOptionValidator(int userChoice){ 
        if(userChoice >= 1 && userChoice <= 8){
            return true;
        } else {
            return false;
        }
    }

    // Dispatches each valid option to the matching service method.
    private void queryMenu(int userChoice){
        switch(userChoice){
            case 1:
                service.findBookingsByCustomerName();
                break;
            case 2:
                service.findCustomersWhoRentedForMoreThanXDays();
                break;
            case 3:
                service.listAllServicesForAGivenBooking();
                break;
            case 4:
                service.updateCustomerInformation();
                break;
            case 5:
                service.countBookingsPerCustomerGivenTwoYears();
                break;
            case 6:
                service.calculateTotalRevenueForASpecificPeriod();
                break;
            case 7:
                service.updateReturnDateOfACarForASpecificBooking();
                break;
            default:
                System.out.println("Exiting.");
        }
    }
}
