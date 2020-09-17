package Zad4;

import java.util.HashMap;

public class Main {

    private static final int minNumber = 153517;
    private static final int maxNumber = 630395;

    public static void main(String[] args) {

        int numberOfPossiblePasswords = 0;

//        Part I
//        for(int i = minNumber; i <= maxNumber; i++) {
//            if (areTwoAdjacentDigitsTheSame(i) && !doDigitsDecrease(i)) {
//                numberOfPossiblePasswords++;
//            }
//        }

//        Part II
        for(int i = minNumber; i <= maxNumber; i++) {
            if (areExactlyTwoAdjacentDigitsTheSame(i) && !doDigitsDecrease(i)) {
                numberOfPossiblePasswords++;
            }
        }

        System.out.println("Number of possible passwords: " + numberOfPossiblePasswords);
    }

    private static boolean areExactlyTwoAdjacentDigitsTheSame(int number) {
        char[] digits = String.valueOf(number).toCharArray();
        HashMap<Character, Integer> numberOfRepetitions = new HashMap<>();

        char previousDigit = digits[0];
        int numberOfAppearances = 1;

        for(int i = 1; i < digits.length; i++) {
            if(previousDigit != digits[i]) {
                numberOfRepetitions.put(previousDigit, numberOfAppearances);
                previousDigit = digits[i];
                numberOfAppearances = 1;
            } else {
                numberOfAppearances++;
            }
        }
        numberOfRepetitions.put(previousDigit, numberOfAppearances);


        return numberOfRepetitions.containsValue(2);
    }

    private static boolean areTwoAdjacentDigitsTheSame(int number) {
        char[] digits = String.valueOf(number).toCharArray();
        char previousDigit = digits[0];
        for(int i = 1; i < digits.length; i++) {
            if(previousDigit == digits[i]) {
                return true;
            } else {
                previousDigit = digits[i];
            }
        }
        return false;
    }

    private static boolean doDigitsDecrease(int number) {
        char[] digits = String.valueOf(number).toCharArray();
        char currentDigit = digits[0];
        for(int i = 1; i < digits.length; i++) {
            if(currentDigit > digits[i]) {
                return true;
            } else {
                currentDigit = digits[i];
            }
        }
        return false;
    }

}
