import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Title: ICT167-Assignment1-TJA2022
 * @author Nicholas Tan Wei Heng<br><br>
 * File Name: Helper<br><br>
 * Purpose: Includes helper methods that other classes can call to complete arbitrary tasks repetitively.
 */
public class Helper {

    /**
     * Prompts and gets a user integer input.
     *
     * <p>
     *     Prompts and gets a user integer input while ensuring input to be an integer using {@link #isInt()}. Prompt
     *     loops until user inputs an integer.<br><br>
     *     Pre-Condition - A prompt message has to be passed into the the method<br>
     *     Post-Condition - User input integer will be returned
     * </p>
     * @param msg Prompt Message
     * @return User input integer
     * @see #println(String promptMessage)
     * @see #isInt()

    public static int getInt(String msg) {
        Scanner input = new Scanner(System.in);
        int n = -1;
        while(n < 0) {
            println(msg);
            n = isInt();
        }
        return n;
    }*/

    //NEW CODE
    public static int getInt(String msg) {
        Scanner input = new Scanner(System.in);
        boolean isInt = false;
        int n = 0;

        while(!isInt) {
            println(msg);
            try {
                n = input.nextInt();

                isInt = true;
            } catch (InputMismatchException e) {
                println("Invalid Input! Please try again");
                input.next();
            }
        }

        return n;
    }

    /**
     * Checks and returns User Input if it is an Integer, while displaying error message if it isn't.
     *
     * <p>
     *     Pre-Condition - None
     *     Post-Condition - Returns User input integer, or displays error message if user didn't input an integer.
     * </p>
     *
     * @return Returns User Input if it was an integer, but returns -1 indicating that User did not input an integer.
     */
    public static int isInt() {
        Scanner input = new Scanner(System.in);
        int intToReturn = -1;
        if(input.hasNextInt()) {
            intToReturn = input.nextInt();

            if(intToReturn < 0) {
                println("Please input a positive number");
            }
        } else {
            println("Invalid Input! Please try again");
        }
        return intToReturn;
    }

    /**
     * Prompts and gets a user String input.
     *
     * <p>
     *     Pre-Condition - A prompt message has to be passed into the the method<br>
     *     Post-Condition - User input String will be returned
     * </p>
     * @param msg Prompt Message
     * @return User input String
     * @see #println(String promptMessage)
     */
    public static String getString(String msg) {
        Scanner input = new Scanner(System.in);
        println(msg);
        String s = input.nextLine();
        return s;
    }

    /**
     * Prints a message
     * <p>
     *     Pre-Condition - A message has to be passed into the the method<br>
     *     Post-Condition - A message will be printed
     * </p>
     * @param msg Message
     */
    public static void println(String msg) {
        System.out.println(msg);
    }

    /**
     * Adds integers in 2 Integers arrays of the same length together
     *
     * <p>
     *     Pre-Condition - Both Arrays have to be of the same length<br>
     *     Post-Condition - Integers of both arrays will be added together and the result wil be returned
     * </p>
     * @param firstArray Array to be added into
     * @param secondArray Array to add
     * @param arrayLength Length of both arrays
     * @return Resultant array of both arrays added together.
     * @see Helper#arrayAddition(int[] firstArray, int[] secondArray, int arrayLength)
     */
    public static int[] arrayAddition(int[] firstArray, int[] secondArray, int arrayLength) {
        int[] result = new int[arrayLength];
        for(int i = 0; i < arrayLength; i++) {
            result[i] = firstArray[i] + secondArray[i];
        }
        return result;
    }
}
