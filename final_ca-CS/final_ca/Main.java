import db.Pool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import service.CarRentalService;
import ui.Menu;


// Main class: here it starts the app
public class Main {
    //method main -> creates a DB connection, initializes service and menu, and starts the menu loop.
    public static void main(String[] args) {
        Connection connection = Pool.getConnection();

        // If the connection is null, it means the database connection failed, so we print an error message and exit the application.
        if (connection == null) {
            System.out.println("Unable to start the application because the database connection failed.");
            return;
        }

        // We use a try-with-resources block to ensure that the Scanner is closed when we're done. 
        // Inside the block, we initialize the CarRentalService and Menu with the shared Scanner and DB connection, and then we start the menu loop.
        try (Scanner scanner = new Scanner(System.in)) {
            CarRentalService service = new CarRentalService(connection, scanner);
            Menu menu = new Menu(scanner, service);
            menu.start();
        } finally {
            // we close the DB connection when the app exits.
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing database connection: " + e.getMessage());
            }
        }

    }

    

                
    

}