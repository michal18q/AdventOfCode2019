package Day2;

public class Main {

    private static final String fileName = "src/main/resources/Zad2/input.txt";
    private static final int desiredValue = 19690720;

    public static void main(String[] args) {

        int finalValue = NounAndVerbFinder.findNounAndVerb(fileName, desiredValue);
        System.out.println("Final result: " + finalValue);
    }
}