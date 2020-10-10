package Day9;

import com.IntcodeComputer;

public class Main {

    private static final String dataFileName = "src/test/resources/Day9/testInput.txt";
//    private static final String dataFileName = "src/main/resources/Day9/input.txt";

    public static void main(String[] args) {

        System.out.println("aaa");

        IntcodeComputer computer = new IntcodeComputer(dataFileName);
        computer.run();

    }
}
