import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import db.Pool;
import service.CarRentalService;
import ui.Menu;


// Main class: here it starts the app
public class Main {
    //method main -> creates DB connection, initializes service and menu, and starts the menu loop.
    public static void main(String[] args) {
        Connection connection = Pool.getConnection();

        if (connection == null) {
            System.out.println("Unable to start the application because the database connection failed.");
            return;
        }

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