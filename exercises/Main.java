package exercises;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        //Uncomment the methods below to see the output for each topic
        dataTypes();
        operators();
        //assignmentOperators();
        //decisionMaking();
        //loops();
        //howToUseScanner();
    }
    public static void dataTypes () {
        /*
        * #Learning Java Fundaments
        * Variables in Java are fundamental containers that represent named memory 
        * locations used to store data values that can be manipulated during program execution.
        * there are two types of variables in Java: primitive data types and reference data types.
        * we can declare variables in Java using the syntax: dataType variableName = value;
        */   

        //Primitives data types in Java
        int age = 18;
        double price = 1500.50;
        boolean verifier = true;
        char grade = 'F';
        short shortNumber = 32767;
        long longNumber = 9223372036854775807L;
        float floatNumber = 3.14f;

        // Output the values of the variables
        System.out.println("Age: " + age); // Output: Age: 18
        System.out.println("Price: " + price); // Output: Price: 1500.5
        System.out.println("Verifier: " + verifier); // Output: Verifier: true
        System.out.println("Grade: " + grade); // Output: Grade: F
        // And so on for the other variables...

        //non-primitive data types in Java
        String name = "John Doe";
        String [] colors = {"Red", "Green", "Blue"};
        

        System.out.println("Name: " + name); // Output: Name: John Doe
        System.out.println("Colors: " + String.join(", ", colors)); // Output: Colors: Red, Green, Blue
    }

    public static void operators() {

        /*
        * #Learning Java Fundaments
        * Operators in Java are special symbols or keywords that perform specific operations 
        * on one or more operands (variables, values, or expressions) and produce a result. 
        * Java provides a wide range of operators that can be categorized into several types, including:
        * 1. Arithmetic Operators: Used for performing basic mathematical operations.
        * 2. Relational Operators: Used for comparing two values.
        * 3. Logical Operators: Used for combining multiple boolean expressions.
        * 4. Assignment Operators: Used for assigning values to variables.
        * 5. Bitwise Operators: Used for performing bit-level operations on integer types.
        * 6. Ternary Operator: A shorthand for an if-else statement.
        */

        int myFirstNumber = 10;
        int mySecondNumber = 5;
        // Arithmetic Operators
        int sum = myFirstNumber + mySecondNumber; // Addition
        int difference = myFirstNumber - mySecondNumber; // Subtraction
        int product = myFirstNumber * mySecondNumber; // Multiplication
        int quotient = myFirstNumber / mySecondNumber; // Division
        int remainder = myFirstNumber % mySecondNumber; // Modulus

        System.out.println("Sum: " + sum); // Output: Sum: 15
        System.out.println("Difference: " + difference); // Output: Difference: 5
        System.out.println("Product: " + product); // Output: Product: 50
        System.out.println("Quotient: " + quotient); // Output: Quotient: 2
        System.out.println("Remainder: " + remainder); // Output: Remainder: 0

        // Relational Operators 
        boolean isEqual = myFirstNumber == mySecondNumber; // Equal to
        boolean isNotEqual = myFirstNumber != mySecondNumber; // Not equal to
        boolean isGreater = myFirstNumber > mySecondNumber; // Greater than
        boolean isLess = myFirstNumber < mySecondNumber; // Less than
        boolean isGreaterOrEqual = myFirstNumber >= mySecondNumber; // Greater than or equal to
        boolean isLessOrEqual = myFirstNumber <= mySecondNumber; // Less
        System.out.println("Is Equal: " + isEqual); // Output: Is Equal: false
        System.out.println("Is Not Equal: " + isNotEqual); // Output: Is Not Equal: true
        System.out.println("Is Greater: " + isGreater); // Output: Is Greater: true
        System.out.println("Is Less: " + isLess); // Output: Is Less: false
        System.out.println("Is Greater Or Equal: " + isGreaterOrEqual); // Output: Is Greater Or Equal: true
        System.out.println("Is Less Or Equal: " + isLessOrEqual); // Output: Is Less Or Equal: false

            // Logical Operators
        boolean isAdult = true;
        boolean hasID = false;
        boolean canEnter = isAdult && hasID; // Logical AND
        boolean canEnterOr = isAdult || hasID; // Logical OR
        boolean cannotEnter = !canEnter; // Logical NOT
        System.out.println("Can Enter (AND): " + canEnter); // Output: Can Enter (AND): false
        System.out.println("Can Enter (OR): " + canEnterOr); // Output: Can Enter (OR): true
        System.out.println("Cannot Enter (NOT): " + cannotEnter); // Output: Cannot Enter (NOT): true

    }
        public static void assignmentOperators() {

        int x = 10; // Simple assignment
        x += 5; // Equivalent to x = x + 5; (Addition assignment)
        int y = 18; // Simple assignment
        y -= 3; // Equivalent to y = y - 3; (Subtraction assignment)
        int z = 10; // Simple assignment
        z *= 2; // Equivalent to z = z * 2; (Multiplication assignment)
        int w = 20; // Simple assignment
        w /= 4; // Equivalent to w = w / 4; (Division assignment)
        int v = 10; // Simple assignment
        v %= 3; // Equivalent to v = v % 3; (Modulus assignment)
        System.out.println("Final value of x: " + x); // Output: Final value of x: 15
        System.out.println("Final value of y: " + y); // Output: Final value of y: 15
        System.out.println("Final value of z: " + z); // Output: Final value of z: 20
        System.out.println("Final value of w: " + w); // Output: Final value of w: 5
        System.out.println("Final value of v: " + v); // Output: Final value of v: 1

    }

    public static void decisionMaking() {
        
        /* 
        * Decision Making
        * Decision making in Java refers to the process of making choices based on certain conditions.
        * It allows the program to execute different blocks of code based on whether a condition is true or false. The main decision-making constructs in Java are:
        * 1. if statement: Executes a block of code if a specified condition is true.
        * 2. if-else statement: Executes one block of code if a specified condition is true, and another block if it is false.
        * 3. else-if ladder: Allows you to check multiple conditions sequentially.
        */

        // Example of if-else statement with an integer variable
        int number = 10;

        if (number > 0) { // while the condition is true, the code inside the if block will be executed
            System.out.println("The number is positive.");
        } else if (number < 0) {
            System.out.println("The number is negative.");
        } else {
            System.out.println("The number is zero.");
        }

        // Example of if-else statement with a String variable
        String dayOfWeek = "Monday";

        if (dayOfWeek.equals("Monday")) {
            System.out.println("It's the start of the week.");
        } else if (dayOfWeek.equals("Friday")) {
            System.out.println("It's almost the weekend.");
        } else {
            System.out.println("It's a regular day.");
        }

        // Example of if statement with a boolean variable
        boolean isRaining = true;

        if(isRaining) {
            System.out.println("Don't forget to take an umbrella!");
        }else {
            System.out.println("Enjoy the sunny weather!");
        }

        // Example of if statement with a char variable
        char grade = 'A';
        if(grade == 'A') {
            System.out.println("Excellent!");
        } else if (grade == 'B') {
            System.out.println("Good job!");
        } else if (grade == 'C') {
            System.out.println("You can do better.");
        } else {
            System.out.println("Keep trying!");
        }


    }

    public static void loops() {
        /*
        * Loops in Java are control flow statements that allow you to execute a block of code repeatedly based on a specified condition. 
        * Java provides several types of loops, including:
        * 1. for loop: Used when the number of iterations is known beforehand.
        * 2. while loop: Used when the number of iterations is not known and the loop needs to continue until a certain condition is met.
        * 3. do-while loop: Similar to the while loop, but guarantees that the block of code will be executed at least once.
        */

        // Example of a for loop
        System.out.println("For Loop:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Iteration: " + i);
        }

        // Example of a nested for loop
        System.out.println("\nNested For Loop:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println("Outer Loop Iteration: " + i + ", Inner Loop Iteration: " + j);
            }
        }

        // Example of a while loop
        System.out.println("\nWhile Loop:");
        int j = 0;
        while (j < 5) {
            System.out.println("Iteration: " + j);
            j++;
        }

        // Example of a do-while loop
        System.out.println("\nDo-While Loop:");
        int k = 0;
        do {
            System.out.println("Iteration: " + k);
            k++;
        } while (k < 5);
    

    // Example of a switch statement
    System.out.println("\nSwitch Statement:");
    int day = 3;

    switch (day) {
        case 1:
            System.out.println("Monday");
            break;
        case 2:
            System.out.println("Tuesday");
            break;
        case 3:
            System.out.println("Wednesday");
            break;
        case 4:
            System.out.println("Thursday");
            break;
        case 5:
            System.out.println("Friday");
            break;
        default:
            System.out.println("Weekend");
    
    }  
            
    }

    public static void howToUseScanner (){
        Scanner myScanner = new Scanner(System.in);

        System.out.println("Please enter your name:");
        String name = myScanner.nextLine();
        System.out.println("Hello, " + name + "!");

        System.out.println("Please enter your age:");
        int age = myScanner.nextInt();
        System.out.println("You are " + age + " years old.");

        //Example of using scanner with if statement
        System.out.println("Please enter your favorite color:");
        String color = myScanner.next();// we can also use nextLine() if we want to read the entire line of input, including spaces

        if(color.equalsIgnoreCase("blue")) {
            System.out.println("Blue is a great color!");
        } else {
            System.out.println(color + " is a nice color too!");
        }

        //Counter example of using scanner with a while loop
        System.out.println("How many times do you want to see the message?");
        int count = myScanner.nextInt();
        int i = 0;

        while (i < count) {
            System.out.println("This is message number " + (i + 1));
            i++;
        }

        myScanner.close();
    }
}

