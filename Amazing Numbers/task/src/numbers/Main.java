package numbers;

import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!\n");
        displayInstructions();
        userInterface();
    }

    public static void userInterface() {
        Scanner scanner = new Scanner(System.in);
        String request;
        ArrayList<String> availableProperties = new ArrayList<>();

        for (NumberProperty numberProperty : NumberProperty.values()) {
            availableProperties.add(numberProperty.name());
        }

        requestLoop:
        while (true) {
            askForRequest();
            String[] lineParts = scanner.nextLine().split(" ");

            switch (lineParts.length) {
                case 0:
                    displayInstructions();
                    break;

                case 1:
                    if (lineParts[0].equals("0")) {
                        System.out.println("Goodbye!");
                        break requestLoop;
                    } else if (!isNaturalNumber(lineParts[0])) {
                        printErrorMessageOne();
                    } else {
                        printPropertiesOfOne(Long.parseLong(lineParts[0]));
                    }
                    break;

                case 2:
                    if (!isNaturalNumber(lineParts[0])) {
                        printErrorMessageOne();
                    } else if (!isNaturalNumber(lineParts[1])) {
                        printErrorMessageTwo();
                    } else {
                        System.out.println(multipleAmazingNumbers(Long.parseLong(lineParts[0]),
                                Long.parseLong(lineParts[1])));
                    }
                    break;

                case 3:
                    if (!isNaturalNumber(lineParts[0])) {
                        printErrorMessageOne();
                    } else if (!isNaturalNumber(lineParts[1])) {
                        printErrorMessageTwo();
                    } else if (!isProperty(lineParts[2], availableProperties)) {
                        printErrorMessageProperty(lineParts[2], availableProperties);
                    } else {
                        long number1 = Long.parseLong(lineParts[0]);
                        long number2 = Long.parseLong(lineParts[1]);
                        String property = lineParts[2];
                        System.out.println(multipleOfProperty(number1, number2, property));
                    }
                    break;

                case 4:
                    if (!isNaturalNumber(lineParts[0])) {
                        printErrorMessageOne();
                    } else if (!isNaturalNumber(lineParts[1])) {
                        printErrorMessageTwo();
                    } else {
                        long number1 = Long.parseLong(lineParts[0]);
                        long number2 = Long.parseLong(lineParts[1]);
                        String property1 = lineParts[2];
                        String property2 = lineParts[3];

                        if (!isProperty(property1, availableProperties) && !isProperty(property2, availableProperties)) {
                            printErrorBothProperties(property1, property2, availableProperties);
                        } else if (!isProperty(property1, availableProperties)) {
                            printErrorMessageProperty(property1, availableProperties);
                        } else if (!isProperty(property2, availableProperties)) {
                            printErrorMessageProperty(property2, availableProperties);
                        } else if (areMutuallyExclusiveProperties(property1, property2)) {
                            printErrorMutuallyExclusive(property1, property2);
                        } else if (property1.equalsIgnoreCase(property2)) {
                            System.out.println(multipleOfProperty(number1, number2, property1));
                        } else {
                            System.out.println(multipleNumbersMultipleProperties(number1, number2,
                                    property1, property2));
                        }
                    }
                    break;

                default:
                    System.out.println("whoopsies");
                    break;
            }
        }
    }

    /**
     * This method takes in a string that represents a number and prints out
     * all of its properties as long as it is a natural number, otherwise it prints out
     * an error message.
     */

    public static void printPropertiesOfOne(long number) {

        System.out.println("Properties of " + toPrettyFormat(number));
        System.out.println("\t\tbuzz: " + NumberProperty.BUZZ.checkProperty(number));
        System.out.println("\t\tduck: " + NumberProperty.DUCK.checkProperty(number));
        System.out.println(" palindromic: " + NumberProperty.PALINDROMIC.checkProperty(number));
        System.out.println("\t  gapful: " + NumberProperty.GAPFUL.checkProperty(number));
        System.out.println("\t\t spy: " + NumberProperty.SPY.checkProperty(number));
        System.out.println("\t  square: " + NumberProperty.SQUARE.checkProperty(number));
        System.out.println("\t sunny: " + NumberProperty.SUNNY.checkProperty(number));
        System.out.println("\t jumping: " + NumberProperty.JUMPING.checkProperty(number));
        System.out.println("\t\teven: " + NumberProperty.EVEN.checkProperty(number));
        System.out.println("\t\t odd: " + NumberProperty.ODD.checkProperty(number));
        System.out.println("\n\n");

    }

    /**
     * This method prints out a list of amazing numbers and their properties, beginning with the
     * first number given by the user and up to as many consecutive numbers based off of the user's
     * second input. Eg: User gives 100 5 as input the output would be the properties of numbers 100, 101,
     * 102, 103, 104
     */
    public static String multipleAmazingNumbers(long startingNumber, long numberOfConsecutiveNumbers) {

        StringBuilder properties = new StringBuilder();
        long number;

        for (int i = 0; i < numberOfConsecutiveNumbers; i++) {
            number = startingNumber + i;
            properties.append(toPrettyFormat(number) + " is ");

            if (NumberProperty.BUZZ.checkProperty(number)) {
                properties.append("buzz, ");
            }
            if (NumberProperty.DUCK.checkProperty(number)) {
                properties.append("duck, ");
            }
            if (NumberProperty.GAPFUL.checkProperty(number)) {
                properties.append("gapful, ");
            }
            if (NumberProperty.PALINDROMIC.checkProperty(number)) {
                properties.append("palindromic, ");
            }
            if (NumberProperty.SPY.checkProperty(number)) {
                properties.append("spy, ");
            }
            if (NumberProperty.SQUARE.checkProperty(number)) {
                properties.append("square, ");
            }
            if (NumberProperty.SUNNY.checkProperty(number)) {
                properties.append("sunny, ");
            }
            if (NumberProperty.ODD.checkProperty(number)) {
                properties.append("odd\n");
            } else {
                properties.append("even\n");
            }
        }
        return properties.toString();
    }

    public static String multipleNumbersMultipleProperties(long startingNumber, long numOfNumbers,
                                                           String firstProperty, String secondProperty) {
        StringBuilder listOfNumbers = new StringBuilder();
        NumberProperty numberProperty1 = NumberProperty.valueOf(firstProperty.toUpperCase(Locale.ROOT));
        NumberProperty numberProperty2 = NumberProperty.valueOf(secondProperty.toUpperCase(Locale.ROOT));
        long numbersFound = 0;
        long currentNumber = startingNumber;

        while (numbersFound < numOfNumbers) {
            if (numberProperty1.checkProperty(currentNumber) && numberProperty2.checkProperty(currentNumber)) {
                listOfNumbers.append(multipleAmazingNumbers(currentNumber, 1));
                numbersFound++;
            }
            currentNumber++;
        }
        return listOfNumbers.toString();
    }

    /**
     * @param startingNumber : initial natural number
     * @param numOfNumbers   : number of sequential numbers to examine
     * @param property       : property the numbers are being evaluated for
     *                       <p>
     *                       this method returns a list of numbers that contain the property given as input, numOfNumbers determines
     *                       how many numbers get returned in the list, starting examination from startingNumber
     */

    public static String multipleOfProperty(long startingNumber, long numOfNumbers,
                                            String property) {

        StringBuilder listOfNumbers = new StringBuilder();
        NumberProperty numberProperty = NumberProperty.valueOf(property.toUpperCase(Locale.ROOT));
        long numbersFound = 0;
        long currentNumber = startingNumber;

        while (numbersFound < numOfNumbers) {
            if (numberProperty.checkProperty(currentNumber)) {
                listOfNumbers.append(multipleAmazingNumbers(currentNumber, 1));
                numbersFound++;
            }
            currentNumber++;
        }
        return listOfNumbers.toString();
    }

    /**
     * @param naturalNumberAsString : String representation of natural number
     * @return true if it is a natural number, false if not
     */

    public static boolean isNaturalNumber(String naturalNumberAsString) {
        boolean isNatural = true;

        try {
            long naturalNumber = Long.parseLong(naturalNumberAsString);
            if (naturalNumber < 1) {
                isNatural = false;
            }

        } catch (Exception e) {
            isNatural = false;
        }
        return isNatural;
    }

    /**
     * Basic method that outputs the instructions
     */
    public static void displayInstructions() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.\n");
    }

    /**
     * This method returns true if the two properties passed into it are mutually exclusive
     */
    public static boolean areMutuallyExclusiveProperties(String property1, String property2) {
        boolean exclusive = false;

        if ((property1.equalsIgnoreCase("EVEN") && property2.equalsIgnoreCase("ODD"))
                || (property1.equalsIgnoreCase("ODD") && property2.equalsIgnoreCase("EVEN"))
                || (property1.equalsIgnoreCase("DUCK") && property2.equalsIgnoreCase("SPY"))
                || (property1.equalsIgnoreCase("SPY") && property2.equalsIgnoreCase("DUCK"))
                || (property1.equalsIgnoreCase("SUNNY") && property2.equalsIgnoreCase("SQUARE"))
                || (property1.equalsIgnoreCase("SQUARE") && property2.equalsIgnoreCase("SUNNY"))
        ) {
            exclusive = true;
        }
        return exclusive;
    }

    public static boolean isProperty(String word, ArrayList<String> availableProperties) {
        return availableProperties.contains(word.toUpperCase(Locale.ROOT));
    }

    /**
     * Method that asks user for requests
     */
    public static void askForRequest() {
        System.out.println("Enter a request: ");
    }

    /**
     * Method that formats numbers with commas
     */
    public static String toPrettyFormat(long number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

    public static void printErrorMessageOne() {
        System.out.println("The first parameter should be a natural number or zero.");
    }

    public static void printErrorMessageTwo() {
        System.out.println("The second parameter should be a natural number.");
    }

    public static void printErrorMessageProperty(String notAProperty, ArrayList<String> availableProperties) {

        StringBuilder errorMessage = new StringBuilder("The property [");
        errorMessage.append(notAProperty.toUpperCase(Locale.ROOT))
                .append("] is wrong.\n")
                .append("Available properties: ")
                .append(availableProperties)
                .append("\n");

        System.out.println(errorMessage);
        ;
    }

    /**
     * This method takes 2 strings
     *
     * @param property1 - string representing name of a property
     * @param property2 - string representing name of a property
     *                  and prints the proper error message
     */
    public static void printErrorMutuallyExclusive(String property1, String property2) {
        StringBuilder errorMessage = new StringBuilder("The request contains mutually exclusive properties: ");
        errorMessage.append("[")
                .append(property1.toUpperCase(Locale.ROOT))
                .append(", ")
                .append(property2.toUpperCase(Locale.ROOT))
                .append("]\nThere are no numbers with these properties.");

        System.out.println(errorMessage);
    }

    public static void printErrorBothProperties(String property1, String property2,
                                                ArrayList<String> availableProperties) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("The properties [")
                .append(property1.toUpperCase(Locale.ROOT))
                .append(", ")
                .append(property2.toUpperCase(Locale.ROOT))
                .append("] are wrong.")
                .append("\n")
                .append("Available properties: ")
                .append(availableProperties);

        System.out.println(errorMessage);
    }
}
