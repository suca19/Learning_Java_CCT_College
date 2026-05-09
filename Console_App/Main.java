import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Connection connectToDatabase = connectToDatabase();
        if(connectToDatabase == null){
            System.out.println("Failed to connect to the database. Exiting...");
            return;
        }
        start(connectToDatabase);

    }

    static Connection connectToDatabase() {

        String url = "jdbc:mysql://localhost:3306/car_rental";
        String user ="root";
        String password = "root123";
        
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to db");
                return connection;
            } catch( SQLException e) {
            System.out.println("Connection failed" + e.getMessage());
        }
        return null;
    }

    static void start(Connection connectToDatabase) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Car Rental System!");
        System.out.println("Please select an option:");
        boolean exit = false;

        while(!exit){

        displayMenu();

        if(scanner.hasNextInt()){

            int userChoice = scanner.nextInt();
            scanner.nextLine();
            if(userInputOptionValidator(userChoice)){
                queryMenu(userChoice, connectToDatabase, scanner);
                if(userChoice == 8){
                    exit = true;
                }
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
        }

        }
        scanner.close();
    }

    static void displayMenu() {

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

    static boolean userInputOptionValidator(int userChoice){ 
        if(userChoice >= 1 && userChoice <= 8){
            return true;
        } else {
            return false;
        }
    }

    static void queryMenu(int userChoice, Connection connectToDatabase, Scanner scanner){
        switch(userChoice){
            case 1:
                queries.find_bookings_by_customer_name(connectToDatabase, scanner);
                break;
            case 2:
                queries.find_customers_who_rented_for_more_than_x_days(connectToDatabase, scanner);
                break;
            case 3:
                queries.list_all_services_for_a_given_booking(connectToDatabase, scanner);
                break;
            case 4:
                queries.update_customer_information(connectToDatabase, scanner);
                break;
            case 5:
                queries.count_bookings_per_customer_given_two_years(connectToDatabase, scanner);
                break;
            case 6:
                queries.calculate_total_revenue_for_a_specific_period(connectToDatabase, scanner);
                break;
            case 7:
                queries.update_return_date_of_a_car_for_a_specific_booking(connectToDatabase, scanner);
                break;
            default:
                System.out.println("Exiting.");
        }
    }

                
    

}
