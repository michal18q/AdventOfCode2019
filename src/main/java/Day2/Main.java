package Day2;

public class Main {

    private static final String fileName = "src/main/resources/Day2/input.txt";
    private static final int desiredValue = 19690720;

    public static void main(String[] args) {

        long finalValue = NounAndVerbFinder.findNounAndVerbResultValue(fileName, desiredValue)
                .orElseThrow(() -> new IllegalStateException("Result value not found."));
        System.out.println("Final result: " + finalValue);
    }
}