public class Main {

    public static void main(String[] args) {
        datatypes();
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

    


    
}