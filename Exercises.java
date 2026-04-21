import java.util.Scanner;
public class Exercises {
    public static void main(String[] args) {
        
    
        //Q1();
        Q2();
        //Q3();
        
    }

    public static void Q1 (){
    
    /*
    Q1
    Write a program that will ask the user to enter their initials.
    If they enter numbers, or more than two letters, you should output a clear error message and ask
    them again
    When they enter valid data, output their initials with the word “super” in between them. 
    */
    
        Scanner scanner = new Scanner(System.in);
        String initials = "";
        boolean valid = false;

        System.out.println("Please enter your initials:");

        while(!valid){
        initials = scanner.nextLine();
            if(initials.length() == 2 && initials.matches("[a-zA-Z]+")){
            valid =true;
            System.out.println(initials.charAt(0) + "super" + initials.charAt(1));
                
         } else {
            System.out.println("error, try again");
            }
         }
         scanner.close();
    }

    public static void Q2 (){
    
    /*
    Q2
    Create a program that will ask the user to enter a valid Boarding Pass number.
    If they enter invalid data, you should output a clear error message and ask them again
    If they enter a valid number, say “Clear to board. Have a nice trip!”
    The validation rules are as follows:
    - Boarding Passes must contain a mix of letters and numbers only. No other characters are allowed.
    - Boarding passes must have a total of 10 or 11 characters
    - The first two characters but be the letters “EI” or “FR” only. This does not need to be case sensitive.
    - The other characters must be numbers
    - The last two characters must be 22
    */

    Scanner scanner = new Scanner(System.in);
    String boardingPass = "";
    boolean valid = false;

    System.out.println("Please enter your boarding pass number:");
    while(!valid){
        boardingPass = scanner.nextLine();
        if(boardingPass.length() == 10 || boardingPass.length() == 11){
            if(boardingPass.substring(0,2).equalsIgnoreCase("EI") || boardingPass.substring(0,2).equalsIgnoreCase("FR")){
                if(boardingPass.substring(2,boardingPass.length()-2).matches("[0-9]+")){
                    if(boardingPass.substring(boardingPass.length()-2).equals("22")){
                        valid = true;
                        System.out.println("Clear to board. Have a nice trip!");
                    }
                }
            }
        } 
        if(!valid){
            System.out.println("error, try again");
        }
    }
    scanner.close();
    }

    public static void Q3 (){

    /*
    Q3
    Create a Program that will ask the user to enter 10 words. If they enter anything with a space
    character then output an error and do not count this as one of the words. Keep a count of all the
    words that have more than 8 letters. When the user has entered all 10 words, output the total of
    your count.
    */
        int validWordsEntered = 0;
        int longWordsCount = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 10 words:");

        while(validWordsEntered < 10){
            String input = scanner.nextLine();
            if(input.contains(" ")){
                System.out.println("error, try again");
            } else {
                if(input.length() > 8){
                    longWordsCount++;        
                }
                validWordsEntered++;
            }
        }
        System.out.println("Total words with more than 8 letters: " + longWordsCount);
        scanner.close();
    }
}