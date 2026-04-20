public class Main {

    public static void main(String[] args) {
        datatypes();
        operators();
    }
    public static void datatypes () {
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


    }



    
}