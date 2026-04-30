import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    static void menu() {

        Scanner scanner = new Scanner(System.in);
        String userChoice = "";
        boolean exit = false;
        String [] options = {"Option 1", "Option 2", "Option 3","Exit"};

        while (!exit) {
            System.out.println("Please select an option:");
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    System.out.println("You selected Option 1");
                    break;
                case "2":
                    System.out.println("You selected Option 2");
                    break;
                case "3":
                    System.out.println("You selected Option 3");
                    break;
                case "4":
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
