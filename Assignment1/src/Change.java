/**
 * Title: ICT167-Assignment1-TJA2022
 * @author Nicholas Tan Wei Heng<br><br>
 * File Name: Change<br><br>
 * Purpose: A User-Defined Class to store data related to monetary change, person's names as well as containing
 *          methods to enable the end-user client, file named ChangeClient, to function without the need to
 *          change when the Change Class requires an updating.<br><br>
 *
 * Assumptions: Only a one word String is accepted as the name variable.
 *              The change input from users are in cents format, not dollars.
 *              Names are non-case sensitive and will be considered the same person if input are the same.
 */

public class Change {

    /**
     * Variable to store name of person for each object
     */
    private String name;

    /**
     * Variable to store the amount of change of person for each object
     */
    private int changeAmount;

    /**
     * 1 Dollar coin denomination value
     */
    private final static int DOLLAR_1_VALUE = 100;


    /**
     * 50 cents coin denomination value
     */
    private final static int CENTS_50_VALUE = 50;

    /**
     * 20 cents coin denomination value
     */
    private final static int CENTS_20_VALUE = 20;

    /**
     * 10 cents coin denomination value
     */
    private final static int CENTS_10_VALUE = 10;

    /**
     * 5 cents coin denomination value
     */
    private final static int CENTS_5_VALUE = 5;


    /**
     * Minimum denomination value of change available to be given out
     */
    private final static int MIN_DENOMINATOR = 5;

    /**
     * Change object default constructor
     * <p>
     *     Pre-Condition - none <br>
     *     Post-condition - A Change Class object is created with default values of NoName for name and 0 for changeAmount
     * </p>
     */
    public Change() {
        this.name = "NoName";
        this.changeAmount = 0;
    }

    /**
     * Change object constructor
     * <p>
     *     Pre-Condition - Name has to be in a single word format, changeAmount has to be of the minimum
     *                     denomination value as stated above and not negative in value<br>
     *     Post-condition - A Change Class object is created with default values of NoName for name
     *                      and 0 for changeAmount
     * </p>
     */
    public Change(String name, int changeAmount) {
        this.name = name;
        this.changeAmount = changeAmount;
    }

    /**
     * Object's name variable accessor
     * <p>
     *     Pre-Condition - An object has to be created to access this method<br>
     *     Post-condition - Returns calling object's name variable
     * </p>
     */
    public String getName() {
        return name;
    }

    /**
     * Object's changeAmount variable accessor
     * <p>
     *     Pre-Condition - An object has to be created to access this method<br>
     *     Post-condition - Returns calling object's changeAmount variable
     * </p>
     */
    public int getChangeAmount() {
        return changeAmount;
    }

    /**
     * Change Class' minimum denominator for change variable accessor
     * <p>
     *     Pre-Condition - None<br>
     *     Post-condition - Returns minDenominator variable
     * </p>
     */
    public static int getMinDenominator() {
        return MIN_DENOMINATOR;
    }

    /**
     * Change Class' coin denominator values variable accessor
     * <p>
     *     Change Class' coin denominator values variable accessor through a switch case algorithm
     *     Pre-Condition - To return values to correct corresponding coins in array, coin arrays have to be
     *                     in order of highest value coin denomination to lowest coin denomination<br>
     *     Post-condition - Returns coin denominator values variable
     * </p>
     */
    public static int getCoinValue(int coinType) {
        int coinValueToReturn = 0;
        switch (coinType) {
            case 0:
                coinValueToReturn = DOLLAR_1_VALUE;
                break;
            case 1:
                coinValueToReturn = CENTS_50_VALUE;
                break;
            case 2:
                coinValueToReturn = CENTS_20_VALUE;
                break;
            case 3:
                coinValueToReturn = CENTS_10_VALUE;
                break;
            case 4:
                coinValueToReturn = CENTS_5_VALUE;
                break;
        }
        return coinValueToReturn;
    }

    /**
     * Calculates number of coins for each denomination according to the calling object's change amount
     * <p>
     *     Calculates number of coins for each coin denominator through a switch case algorithm in a for loop.<br><br>
     *     Pre-Condition - To be able to return to the client class properly, the coins array in the client class
     *                     has to be initiated with the same length as the returning array from this method<br>
     *     Post-condition - Returns number of coins for each coin denominator from the highest denominator value
     *                      to the lowest
     * </p>
     */
    public int[] getChangeToGive() {
        int[] changeToReturn = new int[5];
        int changeToCalculate = this.changeAmount;

        for (int i = 0; i < changeToReturn.length; i++) {
            switch (i) {
                case 0:
                    changeToReturn[i] = getNoOfCoins(DOLLAR_1_VALUE, changeToCalculate);
                    if(changeToReturn[i] > 0) {
                        changeToCalculate -= changeToReturn[i] * DOLLAR_1_VALUE;
                    }
                    break;

                case 1:
                    changeToReturn[i] = getNoOfCoins(CENTS_50_VALUE, changeToCalculate);
                    if(changeToReturn[i] > 0) {
                        changeToCalculate -= changeToReturn[i] * CENTS_50_VALUE;
                    }
                    break;

                case 2:
                    changeToReturn[i] = getNoOfCoins(CENTS_20_VALUE, changeToCalculate);
                    if(changeToReturn[i] > 0) {
                        changeToCalculate -= changeToReturn[i] * CENTS_20_VALUE;
                    }
                    break;

                case 3:
                    changeToReturn[i] = getNoOfCoins(CENTS_10_VALUE, changeToCalculate);
                    if(changeToReturn[i] > 0) {
                        changeToCalculate -= changeToReturn[i] * CENTS_10_VALUE;
                    }
                    break;

                case 4:
                    changeToReturn[i] = getNoOfCoins(CENTS_5_VALUE, changeToCalculate);
                    if(changeToReturn[i] > 0) {
                        changeToCalculate -= changeToReturn[i] * CENTS_5_VALUE;
                    }
                    break;
            }
        }

        return changeToReturn;
    }

    /**
     * Calculates number of coins for the denomination value and change amount passed in.
     * <p>
     *     Pre-Condition - To be able to return to the client class properly, the coins array in the client class
     *                     has to be initiated with the same length as the returning array from this method<br>
     *     Post-condition - Returns number of coins for the denomination value and change amount passed in
     * </p>
     */
    private int getNoOfCoins(int coinValue, int changeToCalculate) {
        int noOfCoins = 0;
        if(changeToCalculate >= coinValue) {
            int tempAmount = changeToCalculate;
            int remainder = tempAmount % coinValue;
            tempAmount -= remainder;
            noOfCoins = tempAmount/coinValue;
        }

        return noOfCoins;
    }


    /**
     * Adds an amount to the calling object's change amount
     * <p>
     *     Pre-condition - The change amount to be added that is being passed in has to be in denominator of the
     *                     minimum denominator declared in the class<br>
     *     Post-condition - The amount passed in will be added to the change amount of the calling object changeAmount
     *                      variable
     * </p>
     * @param changeAmountToAdd Amount of change to be added to the calling object
     */
    public void addCoinChangeAmount(int changeAmountToAdd) {
        this.changeAmount += changeAmountToAdd;
    }

    /**
     * To compare the name of the calling object and the object being passed in
     * <p>
     *     Pre-condition - None<br>
     *     Post-condition - Returns true/false depending on if both names are the same while ignoring case
     * </p>
     * @param nameToCompare The object to compare the calling object's name to
     * @return Returns True if the names of both objects are the same, returns false if they are not.
     */
    public boolean isSameName(Change nameToCompare) {
        String theOtherName = nameToCompare.getName();
        boolean state = false;

        if(this.name.equalsIgnoreCase(theOtherName)) {
            state = true;
        }

        return state;
    }

    /**
     * To compare the name of the calling object and the String being passed in
     * <p>
     *     Pre-condition - None<br>
     *     Post-condition - Returns true/false depending on if both names are the same while ignoring case
     * </p>
     * @param name The string to compare the calling object's name to.
     * @return Returns True if both names are the same, returns false if they are not.
     */
    public boolean isSameName(String name) {
        boolean state = false;

        if(this.name.equalsIgnoreCase(name)) {
            state = true;
        }

        return state;
    }

    /**
     * To compare the changeAmount of the calling object and the integer being passed in
     * <p>
     *     Pre-condition - None<br>
     *     Post-condition - Returns true/false depending on if both amounts are the same.
     * </p>
     * @param amountToCompare The amount to compare the calling object's changeAmount to.
     * @return Returns True if the object's changeAmount is same as the amount passed in, returns false if they are not.
     */
    public boolean isSameAmount(int amountToCompare) {
        boolean isSameAmount = false;
        if(this.changeAmount == amountToCompare) {
            isSameAmount = true;
        }

        return isSameAmount;
    }

}
