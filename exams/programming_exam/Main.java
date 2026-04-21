/*
*name: Carlos Enrique Sucapuca Aracayo
*studentID: 2026003
*course: Higher Diploma in Computing
 */

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main (String[] args){
        
        //We create an Array Lsit to store each value of the file
        ArrayList<Double> validSalaries = new ArrayList<Double>();
        int invalidCount = 0;
        
        // Read the file and store valid salaries
        try{
            Scanner myReader = new Scanner(new FileReader("./dataNumbers.txt"));
            
            while (myReader.hasNextLine()) {
                
                //We check there is not any empty line
                String line = myReader.nextLine().trim();
                
                if (line.isEmpty()) {
                    continue;
                }
                
                try {
                    double salary = Double.parseDouble(line);
                    // Check if it's a valid salary, not negative
                    if (salary >= 0) {
                        validSalaries.add(salary);
                    } else {
                        invalidCount++;
                    }
                } catch (NumberFormatException e) {
                    invalidCount++;
                }
            }
            myReader.close();
        } catch(FileNotFoundException e){
            System.out.println("File not found");// In case a file is not found

        }
        
        
        // Scanner for user input
        Scanner myScanner = new Scanner(System.in);
        boolean stopSignal = false;
        
        // Main menu loop
        while (!stopSignal) {
            // Display menu
            System.out.println("Option 1 => Valid and Invalid entries");
            System.out.println("Option 2 => The highest salary");
            System.out.println("Option 3 => Count of Salaries over 250.0");
            System.out.println("Option 4 => Exit");
            System.out.println("Please choose an option (1-4): ");
            
            // Verify user input is an integer
            if (myScanner.hasNextInt()) {
                int userChoice = myScanner.nextInt();
                
                // Check if choice is valid
                if (userChoice >= 1 && userChoice <= 4) {
                    
                    switch (userChoice) {
                        case 1:
                            // Display count of valid and invalid entries
                            System.out.println("-------------------------------------");
                            System.out.println("Number of VALID entries: " + validSalaries.size());
                            System.out.println("Number of INVALID entries: " + invalidCount);
                            System.out.println("-------------------------------------");
                            break;
                            
                        case 2:
                            // Display The highest salary
                            if (validSalaries.size() < 2) {
                                System.out.println("Error");
                            } else {
                                ArrayList<Double> sortedSalaries = new ArrayList<Double>(validSalaries);
                                Collections.sort(sortedSalaries, Collections.reverseOrder());
                                System.out.println("-------------------------------------");
                                System.out.println("The HIGHEST salary is: " + sortedSalaries.get(0));
                                System.out.println("-------------------------------------");
                            }
                            break;
                            
                        case 3:
                            // Display count of salaries over 250.0
                            int countOver250 = 0;
                            for (double salary : validSalaries) {
                                if (salary > 250.0) {
                                    countOver250++;
                                }
                            }
                            System.out.println("-------------------------------------");
                            System.out.println("Count of salaries OVER 250.0 is =>: " + countOver250);
                            System.out.println("-------------------------------------");
                            break;
                            
                        case 4:
                            // Exit
                            System.out.println("------------------------------------------");
                            System.out.println("Thank you for using the program. Goodbye!");
                            System.out.println("------------------------------------------");
                            stopSignal = true;
                            break;
                    }
                } else {
                    // Invalid menu choice
                    System.out.println("-------------------------------------");
                    System.out.println("Error: Invalid option! Please choose 1, 2, 3, or 4.");
                    System.out.println("-------------------------------------");
                }
            } else {
                // Invalid input, not an integer
                System.out.println("-------------------------------------");
                System.out.println("Error: Invalid input! Please enter a number between 1 and 4.");
                System.out.println("-------------------------------------");
                myScanner.next();  // Clear the invalid input
            }
        }
        
        // Close the scanner
        myScanner.close();
    }
}
