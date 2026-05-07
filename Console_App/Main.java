import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        connectToDatabase();
        start();

    }

    static void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/car_rental";
        String user ="root";
        String password = "root123";
        
        try (Connection conn = DriverManager.getConnection(url, user, password)){
            if(conn != null){
                System.out.println("Connected to db");
            }
        } catch( SQLException e) {
            System.out.println("Connection failed" + e.getMessage());
        }
    }

    static void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Car Rental System!");
        System.out.println("Please select an option:");
        displayMenu();
        if(scanner.hasNextInt()){
            int userChoice = scanner.nextInt();
            if(userInputOptionValidator(userChoice)){
                addNewCar();
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
        }
        scanner.close();
    }
    
    static void displayMenu() {

        String [] options = {"1. Add a new car", "2. View all cars", "3. Update car details","4. Delete a car", 
                            "5. Search cars", "6. Rent a car", "7. Return a car", "8. Exit"};
            
        for(int i = 0; i < options.length; i++){
            System.out.println(options[i]); 
            }
        
        
    }

    static boolean userInputOptionValidator(int userChoice){ 
        if(userChoice >= 1 && userChoice <= 8){
            return true;
        } else {
            return false;
        }
    }

    static void addNewCar(){
        System.out.println("Adding a new car...");
        
        while (true) {
            switch (1){
                    case 1:
                    System.out.println("1");
                    break;

                    case 2:
                        System.out.println("2");
                        break;

                    case 3:
                        System.out.println("3");
                        break;
                    
                    case 4:
                        System.out.println("4");
                        break;
                    case 5:
                        System.out.println("5");
                        break;
                    
                    case 6:
                        System.out.println("6");
                        break;
                       
                    case 7:
                        System.out.println("7");
                        break;
                    default:
                        System.out.println("byee bye");
                }
    }
}
}
