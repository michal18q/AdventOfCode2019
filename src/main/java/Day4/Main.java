package Day4;

public class Main {

    private static final int minNumber = 153517;
    private static final int maxNumber = 630395;

    public static void main(String[] args) {

        System.out.println("Number of possible passwords for part I : "
                + PasswordCalculator.calculateNumberOfPossiblePasswordsForPartI(minNumber, maxNumber));

        System.out.println("Number of possible passwords for part II : "
                + PasswordCalculator.calculateNumberOfPossiblePasswordsForPartII(minNumber, maxNumber));
    }
}