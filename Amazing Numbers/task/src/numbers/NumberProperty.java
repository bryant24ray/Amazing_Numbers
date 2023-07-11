package numbers;

import java.util.ArrayList;
import java.util.List;

public enum NumberProperty {

    BUZZ {
        /**
         * returns true if number is divisible by 7 or ends with the digit 7
         */
        @Override
        public boolean checkProperty(long number) {
            return number % 7 == 0 || number % 10 == 7;
        }
    },

    DUCK {
        /**
         * returns true if number contains the digit 0, as long as it is not leading with 0
         */
        @Override
        public boolean checkProperty(long number) {
            String amIDuck = Long.toString(number);
            return amIDuck.matches("[1-9]+0[0-9]*");
        }
    },

    PALINDROMIC {
        /**
         * Method that returns true if number is palindromic
         */
        @Override
        public boolean checkProperty(long number) {
            boolean palindrome = true;
            String numberAsString = Long.toString(number);
            int startIndex = 0;
            int endIndex = numberAsString.length() - 1;

            palindromeLoop:
            while (startIndex < endIndex) {
                if (numberAsString.charAt(startIndex) != numberAsString.charAt(endIndex)) {
                    palindrome = false;
                    break palindromeLoop;
                }
                startIndex++;
                endIndex--;
            }
            return palindrome;
        }
    },

    GAPFUL {
        /**
         * returns true if the number is multiple of the concat result of the first and last digits.
         * eg: is 108 a multiple of 18? 3485 a multiple of 35? etcetc
         */
        @Override
        public boolean checkProperty(long number) {
            boolean gapful;
            String numberAsString = Long.toString(number);
            long firstDigit = Character.getNumericValue(numberAsString.charAt(0));
            long lastDigit = Character.getNumericValue(numberAsString.charAt(numberAsString.length() - 1));

            if (number < 100) {
                gapful = false;
            } else {
                gapful = (number % ((10 * firstDigit) + lastDigit) == 0);
            }
            return gapful;
        }
    },

    SPY {
        /**
         * this method returns true if the sum of each of the numbers digits is equal to the product of
         * all the number's digits
         */
        @Override
        public boolean checkProperty(long number) {
            String numberAsString = Long.toString(number);
            int length = numberAsString.length();
            long sum = 0;
            long product = 1;

            for (int i = 0; i < length; i++) {
                sum += Character.getNumericValue(numberAsString.charAt(i));
                product *= Character.getNumericValue(numberAsString.charAt(i));
            }
            return sum == product;
        }
    },

    SQUARE {
        @Override
        public boolean checkProperty(long number) {
            long sqrt = (long) Math.sqrt(number);
            return (sqrt * sqrt) == number;
        }
    },

    SUNNY {
        @Override
        public boolean checkProperty(long number) {
            return NumberProperty.SQUARE.checkProperty(number + 1);
        }
    },
    JUMPING {
        @Override
        public boolean checkProperty(long number) {
            boolean isJumping = true;
            String numberAsString = Long.toString(number);
            int length = numberAsString.length();
            int previousDigit;
            int currentDigit;


            if (length > 1) {
                previousDigit = Character.getNumericValue(numberAsString.charAt(0));

                jumpingForLoop:
                for (int i = 1; i < length; i++) {
                    currentDigit = Character.getNumericValue(numberAsString.charAt(i));
                    if (Math.abs(previousDigit  - currentDigit) != 1){
                        isJumping = false;
                        break jumpingForLoop;
                    }
                    previousDigit = currentDigit;
                }
            }
            return isJumping;
        }
    },

    EVEN {
        @Override
        public boolean checkProperty(long number) {
            return number % 2 == 0;
        }
    },

    ODD {
        @Override
        public boolean checkProperty(long number) {
            return number % 2 != 0;
        }
    };

    public abstract boolean checkProperty(long number);

}
