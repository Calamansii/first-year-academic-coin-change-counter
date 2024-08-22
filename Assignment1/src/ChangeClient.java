import java.util.Scanner;
import java.util.Arrays;

/**
 * Title: ICT167-Assignment1-TJA2022
 * @author      Nicholas Tan Wei Heng<br><br>
 * File Name: ChangeClient<br><br>
 * Purpose: To act as an end-user client for the Change Class, where the end-user can input their own data
 *          of persons' names, and their change amount, into multiple instances of the Change class object.
 *          From there, they can choose up to 5 functions:
 *          1. To find a person's name and display their change to be given for each denomination.
 *          2. Find the name with the largest amount and display change to be given for each denomination.
 *          3. Find the name with the smallest amount and display change to be given for each denomination.
 *          4. Calculate and display the total number of coins for each denomination.
 *          5. Calculate and display the total amount (i.e. NOT the total number of coins) for the sum of all
 *             denominations.<br><br>
 *
 * Assumptions: 1. All change inputs are in cents value format, and not in dollar value format.
 *              2. All change outputs will be in cents value format, standardised with input formats.
 *              3. Names are non-case sensitive and will be considered the same person if input are the same.
 *              4. Names can be alphanumeric.<br><br>
 */
public class ChangeClient {
    /**
     * Scanner input object using java.util.Scanner
     */
    static Scanner input = new Scanner(System.in);

    /**
     * Change object array used to store user input Change objects
     */
    private static Change[] changeArray;

    public static void main(String[] args) {
        changeArray = new Change[100];
        studentInfo();
        initDataMethod();
        findSameNamesAndAddChangeAmount();
        run();
    }

    /**
     * Method that runs the menu and calls functions that end-users can choose from.
     *
     * <p>
     *     This "run" method is the main method that links all functions together.
     *     This displays the menu and gets the choice of function that the user wants using
     *     {@link #printMenuAndGetChoice()} method. A switch case then calls the function method
     *     according to the choice of 1-6. <br><br>
     *     Pre-Conditions - Change class objects array data has been initialised before hand, either by
     *                      hardcode or user inputs. <br>
     *     Post-Conditions - Corresponding function methods of user's choice are called, or to exit the program.
     * </p>
     * @see #findNameAndDisplayDetails()
     * @see #findLargestAndDisplayDetails()
     * @see #findSmallestAndDisplayDetails()
     * @see #displayTotalCoinsForEachDenomination()
     * @see #displayTotalSumForAllDenomination()
     */
    private static void run() {
        boolean isRunning = true;
        while(isRunning) {
            int choice = printMenuAndGetChoice();
            switch(choice) {
                case 1:
                    findNameAndDisplayDetails();
                    break;
                case 2:
                    findLargestAndDisplayDetails();
                    break;
                case 3:
                    findSmallestAndDisplayDetails();
                    break;
                case 4:
                    displayTotalCoinsForEachDenomination();
                    break;
                case 5:
                    displayTotalSumForAllDenomination();
                    break;
                case 6:
                    isRunning = false;
                    println("Exiting");
                    break;
                default:
                    println("Invalid Choice! Please try again.");
            }
        }
    }

    /**
     * Prints the function menu and prompts user for their Choice of function.
     * <p>
     *     Prints out the list of functions available to the user with a numbering list.
     *     Users can input the numbers corresponding to the functions they want through {@link #getInt(String msg)}.
     *     <br><br>
     *     Pre-Conditions - No pre-conditions <br>
     *     Post-Conditions - Integer of user's choice is returned
     * </p>
     *
     * @return Integer value of the corresponding action/function that user desires.
     */
    private static int printMenuAndGetChoice() {

        println("1. Enter a name and display change to be given for each denomination.");
        println("2. Find the name with the largest amount and display change to be given for each denomination.");
        println("3. Find the name with the smallest amount and display change to be given for each denomination.");
        println("4. Calculate and display the total number of coins for each denomination.");
        println("5. Calculate and display the total amount (i.e. NOT the total number of coins) for the sum of all denominations.");
        println("6. Exit");
        int choice = getInt("Enter Choice: ");
        return choice;
    }

    /**
     * Finds Change object with user's input of name and display their Change Denominations.
     *
     * <p>
     *     Finds Change object in the array with user's name input and displays their Change denominations through
     *     {@link #findAndPrintCoinsArray(int[] coinsArray)}.
     *     Pre-Condition - Change class objects array data has to be initialised before hand, either by
     *                     hardcode or user inputs. User also have to choose this function to run through
     *                     {@link #run()}.<br>
     *     Post-Condition - Prints user's input name, as well as the change denominations to be given to that name.
     * </p>
     * @see #nameInputPrompt(String promptMessage)
     * @see Change#isSameName(String nameToFind)
     * @see Change#getChangeToGive()
     * @see #findAndPrintCoinsArray(int[] coinsArray)
     */
    private static void findNameAndDisplayDetails() {
        boolean isName = false;
        String nameToFind;

        //User input for name to find in changeArray
        nameToFind = nameInputPrompt("Name of person to display change: ");

        //Finding user's name input in changeArray then printing change denominations
        for(int i = 0; i < changeArray.length; i++) {
            if(changeArray[i] == null) { continue; }

            if(changeArray[i].isSameName(nameToFind)) {
                isName = true;
                println(changeArray[i].getName() + "'s change to get: ");
                findAndPrintCoinsArray(changeArray[i].getChangeToGive());
            }

        }

        if(!isName) {
            println("Name not found, please try again.");
        }
    }

    /**
     * Finds object with the largest change amount in the Change Object Array, and displays the number of coins to be given.
     *
     * <p>
     *     Pre-Conditions - Change class objects array data has to be initialised before hand, either by
     *                      hardcode or user inputs. User also have to choose this function to run through
     *                      {@link #run()}.<br>
     *     Post-Condition - Prints name with the largest change amount, and displays the number of coins to be given.
     * </p>
     * @see Change#getChangeAmount()
     * @see Change#isSameAmount(int changeAmountToCompare)
     * @see Change#getChangeToGive()
     * @see #findAndPrintCoinsArray(int[] coinsArray)
     */
    private static void findLargestAndDisplayDetails() {
        int largest = 0;

        //Finding largest change amount
        for(int i = 0; i < changeArray.length; i++) {
            if(changeArray[i] == null) { continue; }

            if (changeArray[i].getChangeAmount() > largest) {
                largest = changeArray[i].getChangeAmount();
            }
        }

        //Matching largest change amount with persons in Array and print their details
        for(int i = 0; i < changeArray.length; i++) {
            if(changeArray[i] == null) { continue; }

            boolean isSameAmount = changeArray[i].isSameAmount(largest);
            if (isSameAmount) {
                println("Largest Change Amount\n" + changeArray[i].getName());
                findAndPrintCoinsArray(changeArray[i].getChangeToGive());
            }
        }

    }

    /**
     * Finds object with the smallest change amount in the Change Object Array, and displays the number of coins to be given.
     *
     * <p>
     *     Pre-Conditions - Change class objects array data has to be initialised before hand, either by
     *                      hardcode or user inputs. User also have to choose this function to run through
     *                      {@link #run()}.<br>
     *     Post-Condition - Prints name with the smallest change amount, and displays the number of coins to be given.
     * </p>
     * @see Change#getChangeAmount()
     * @see Change#isSameAmount(int changeAmountToCompare)
     * @see Change#getChangeToGive()
     * @see #findAndPrintCoinsArray(int[] coinsArray)
     */
    private static void findSmallestAndDisplayDetails() {
        boolean isSameAmount;
        int smallest = changeArray[0].getChangeAmount();

        //Finding Smallest
        for(int i = 0; i < changeArray.length; i++) {
            if(changeArray[i] == null) { continue; }

            if (changeArray[i].getChangeAmount() < smallest) {
                smallest = changeArray[i].getChangeAmount();
            }
        }

        for(int i = 0; i < changeArray.length; i++) {
            if(changeArray[i] == null) { continue; }

            isSameAmount = changeArray[i].isSameAmount(smallest);
            if (isSameAmount) {
                println(changeArray[i].getName());
                findAndPrintCoinsArray(changeArray[i].getChangeToGive());
            }

        }
    }

    /**
     * Calculates and displays the total number of coins per denomination.
     *
     * <p>
     *      Pre-Conditions - Change class objects array data has to be initialised before hand, either by
     *                       hardcode or user inputs. User also have to choose this function to run through
     *                       {@link #run()}.<br>
     *      Post-Conditions - Prints the total number of coins per denomination.
     * </p>
     * @see Change#getChangeAmount()
     * @see #arrayAddition(int[] firstArray, int[] arrayToAdd, int length)
     * @see #findAndPrintCoinsArray(int[] coinsArray)
     */
    private static void displayTotalCoinsForEachDenomination() {
        int[] coinsArray = new int[changeArray[0].getChangeToGive().length];
        for(int i = 0; i < changeArray.length; i++) {
            if(changeArray[i] == null) { continue; }
            Change change = changeArray[i];
            coinsArray = arrayAddition(change.getChangeToGive(), coinsArray, coinsArray.length);
        }

        findAndPrintCoinsArray(coinsArray);
    }

    /**
     * Calculates and displays the total sum value per denomination, as well as the total sum value.
     *
     * <p>
     *      Pre-Conditions - Change class objects array data has to be initialised before hand, either by
     *                       hardcode or user inputs. User also have to choose this function to run through
     *                       {@link #run()}.<br>
     *      Post-Conditions - Prints the total sum value per denomination, and total sum value of all objects.
     * </p>
     * @see Change#getChangeToGive()
     * @see #arrayAddition(int[] firstArray, int[] arrayToAdd, int length)
     * @see Change#getCoinValue(int forLoopIteration)
     */
    private static void displayTotalSumForAllDenomination() {
        double totalSum = 0;
        int[] coinsArray = new int[changeArray[0].getChangeToGive().length];
        for(int i = 0; i < changeArray.length; i++) {
            if(changeArray[i] == null) { continue; }
            Change change = changeArray[i];
            coinsArray = arrayAddition(change.getChangeToGive(), coinsArray, coinsArray.length);
        }

        for(int j = 0; j < coinsArray.length; j++) {
            double coinValue = Change.getCoinValue(j);
            int coinTotal = (int)(coinsArray[j] * coinValue);
            System.out.printf("Total Sum for $%.2f: %d \n", (coinValue/100), coinTotal);
            totalSum += coinTotal;
        }
        System.out.printf("Total Sum for all coins: %.2f \n", totalSum);
    }

    /**
     * User to choose the method of data initialisation.
     *
     * <p>
     *     Pre-Conditions - No Pre-Conditions
     *     Post-Conditions - User choice of 1 to use hardcoded data and choice of 2 to manually enter data.
     * </p>
     * @see #initArrayByHardCode()
     * @see #initArrayByUser()
     */
    private static void initDataMethod() {
        int choice = getInt("1. Use hardcoded data\n2. Enter Data");
        switch (choice) {
            case 1:
                initArrayByHardCode();
                break;

            case 2:
                initArrayByUser();
                break;

            default:
                println("Invalid choice, please try again.");
        }
    }

    /**
     * Initiates Change Object Array through hardcoded data, then truncates the Array to 8 indexes.
     *
     * <p>
     *     Pre-Conditions - User has to choose this method of initialisation through {@link #initDataMethod()}.
     *     Post-Conditions - Change Object Array is initialised through hardcoded data and truncated.
     * </p>
     */
    private static void initArrayByHardCode() {
        //Note please use this set of test data
        Change change1 = new Change("A", 100);
        Change change2 = new Change("B", 25);
        Change change3 = new Change("C", 10);
        Change change4 = new Change("D", 255);
        Change change5 = new Change("D", 55);
        Change change6 = new Change("E", 155);
        Change change7 = new Change("F", 50);
        Change change8 = new Change("G", 90);

        changeArray[0] = change1;
        changeArray[1] = change2;
        changeArray[2] = change3;
        changeArray[3] = change4;
        changeArray[4] = change5;
        changeArray[5] = change6;
        changeArray[6] = change7;
        changeArray[7] = change8;

        changeArray = Arrays.copyOf(changeArray, 8);
    }

    /**
     * Initiates Change Object Array through manual inputs, then truncates the Array to the amount of User data inputs.
     *
     * <p>
     *     Pre-Conditions - User has to choose this method of initialisation through {@link #initDataMethod()}.
     *     Post-Conditions - Change Object Array is initialised through manual inputs and truncated.
     * </p>
     * @see #nameInputPrompt(String promptMessage)
     * @see #changeAmountInputPrompt(String promptMessage, int minDenominator)
     * @see #isDone()
     */
    private static void initArrayByUser() {
        int minDenominator = Change.getMinDenominator();
        boolean isDone = false;
        String tempName;
        int tempAmount;
        int count = 0;
        println("A minimum of 8 persons' input is recommended for best results.");
        while(!isDone) {
            tempName = nameInputPrompt("Enter Name #" + (count + 1) + ": ");
            tempAmount = changeAmountInputPrompt("Enter your change amount (in denominators of "
                    + minDenominator + ") #" + (count + 1) + ": ", minDenominator);

            isDone = isDone();
            changeArray[count] = new Change(tempName, tempAmount);
            count++;
        }

        changeArray = Arrays.copyOf(changeArray, count);
    }


    /**
     * Checks if name being parsed in is just a single word.
     *
     * <p>
     *     Pre-Condition - No Pre-Condition
     *     Post-Condition - Returns a true/false if name is acceptable.
     * </p>
     * @param name Name that user has manually input to be checked.
     * @return True means that the String being parsed in is a single word, False means that it is either empty or
     *         not a single word.
     */
    private static boolean isName(String name) {
        boolean isName = true;
        if(name.length() == 0 || name.contains(" ")) {
            isName = false;
            println("Invalid Name! Please try again");
        }

        return isName;
    }

    /**
     * Checks if changeAmount being parsed in is in the acceptable denomination.
     *
     * <p>
     *     Pre-Condition - No Pre-Conditions
     *     Post-Condition - Returns true/false if change amount is in acceptable denomination.
     * </p>
     * @param changeAmount Amount of change that user has manually input to be checked.
     * @return If true, the change amount is in acceptable denomination and is not negative, while false means
     *         it is not in acceptable denomination or is negative.
     */
    private static boolean isChange(int changeAmount, int minDenominator) {
        boolean isChange = true;
        if(changeAmount < 0 || changeAmount % minDenominator != 0) {
            isChange = false;
            println("Invalid change amount! Please try again in denominators of " + minDenominator);
        }

        return isChange;
    }

    /**
     * Prompts if user wants to enter more data.
     *
     * <p>
     *     Prompts if user wants to enter more data with a response of non-case sensitive of Y/N,
     *     also prints an error if user has input a response other than Y/N.<br><br>
     *
     *     Pre-Condition - None
     *     Post-Condition - Returns true/false based on if user wants to enter data.
     * </p>
     * @return True means that user does not want to input more data, false means user wants to enter more data.
     */
    private static boolean isDone() {
        boolean isDone = false;
        boolean isYN = false;
        while(!isYN) {
            String response = getString("Do you have more persons to enter? (Y/N)");
            char done = response.trim().toLowerCase().charAt(0);
            switch (done) {
                case 'y':
                    isDone = false;
                    isYN = true;
                    break;
                case 'n':
                    isDone = true;
                    isYN = true;
                    break;
                default:
                    println("Invalid response, please try again!");
                    break;
            }
        }
        return isDone;
    }

    /**
     * Prompts user for a name input and returns it.
     * 
     * <p>
     *     Prompts user for a name input, while looping the prompt if input is deemed invalid by
     *     {@link #isName(String name)}.<br><br>
     *     Pre-Condition - A prompt message String must be parsed into this method.<br>
     *     Post-Conditions - Returns a valid, one word name input from user.
     * </p>
     * @param msg Prompt message to be printed
     * @return One word String name input from user.
     * @see #isName(String nameToCheck)
     */
    private static String nameInputPrompt(String msg) {
        String nameFromUser = "";
        boolean isName = false;
        while(!isName) {
            nameFromUser = getString(msg);
            isName = isName(nameFromUser);
        }

        return nameFromUser;
    }

    /**
     * Prompts user for a change amount input and returns the amount.
     *
     * <p>
     *     Prompts user for a change amount input, while looping the prompt if amount input is
     *     deemed invalid by {@link #isChange(int changeAmountToCheck, int minDenominator)}.<br><br>
     *     Pre-Condition - A prompt message String must be passed into this method.<br>
     *     Post-Condition - Returns change amount input by user.
     * </p>
     * @param msg Prompt message to be printed
     * @return Change amount input by user
     */
    private static int changeAmountInputPrompt(String msg, int minDenominator) {
        int changeAmountFromUser = 0;
        boolean isChange = false;
        while(!isChange) {
            changeAmountFromUser = getInt(msg);
            isChange = isChange(changeAmountFromUser, minDenominator);
        }
        return changeAmountFromUser;
    }

    /**
     * Finds if any Change Object in the Array has the same name, and adds their amount.
     *
     * <p>
     *     Method compares each Change object with others in the array to find ones with the same name,
     *     and adds their amount to the earliest Index of the name. Objects of later indexes with the same
     *     names are set null.<br><br>
     *
     *     Pre-Conditions - Change class objects array data has to be initialised before hand, either by
     *                      hardcode or user inputs.<br>
     *     Post-Conditions - Objects with the same names have their amounts added to the object in the earliest index,
     *                       and objects of later indexes with the same names are set null.
     * </p>
     * @see Change#isSameName(Change instanceToCompare) isSame Comparing Method
     * @see Change#addCoinChangeAmount(int changeAmountToAdd) addCoinChangeAmount Method
     */
    private static void findSameNamesAndAddChangeAmount() {
        for (int i = 0; i < changeArray.length; i++) {
            if (changeArray[i] == null) { continue; }

            Change change1 = changeArray[i];
            for (int j = i + 1; j < changeArray.length; j++) {
                if (changeArray[j] == null) { continue; }
                Change change2 = changeArray[j];
                if (change1.isSameName(change2)) {
                    change1.addCoinChangeAmount(change2.getChangeAmount());
                    changeArray[j] = null;
                }
            }

        }
    }

    /**
     * Prints number of coins of each denominator.
     * <p>
     *     Prints number of coins of each denominator. Coin denominators are taken from
     *     the Change class, without the need of hardcoded denominator values in this method.
     *     <br><br>
     *     Pre-Conditions - There has to be Coin denominator values set in the Change Class. Integer Array
     *                      denoting the amount of coins has to be properly initialised with proper indexes
     *                      according to available types of coins.<br>
     *     Post-Conditions - Number of coins are printed according to their denominators.
     * </p>
     * @param coinsArray Array denoting number of coins in each index starting from the highest value to the lowest.
     * @see Change#getCoinValue(int forLoopIteration)
     */
    private static void findAndPrintCoinsArray(int[] coinsArray) {
        for(int j = 0; j < coinsArray.length; j++) {
            double coinValue = Change.getCoinValue(j);
            System.out.printf("Number of Coins for $ %.2f: %d \n", (coinValue/100), coinsArray[j]);
        }
    }


    /**
     * Prompts and gets a user integer input.
     *
     * <p>
     *     Pre-Condition - A prompt message has to be passed into the the method<br>
     *     Post-Condition - User input integer will be returned
     * </p>
     * @param msg Prompt Message
     * @return User input integer
     * @see Helper#getInt(String promptMessage)
     */
    public static int getInt(String msg) {
        return Helper.getInt(msg);
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
     * @see Helper#getString(String promptMessage)
     */
    public static String getString(String msg) {
        return Helper.getString(msg);
    }

    /**
     * Prints a message
     * <p>
     *     Pre-Condition - A message has to be passed into the the method<br>
     *     Post-Condition - A message will be printed
     * </p>
     * @param msg Message
     * @see Helper#println(String message)
     */
    public static void println(String msg) {
        Helper.println(msg);
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
        return Helper.arrayAddition(firstArray, secondArray, arrayLength);
    }

    /**
     * Prints student's details
     *
     */
    private static void studentInfo() {
        println("Name: Nicholas Tan Wei Heng\nStudent number: 34480999\nMode of study: Online");
        println("Tutor name: Mr Aaron Yeo\nTutorial Attendance Day: Thursday\nTutorial Attendance Time: 1615");
    }

}
