package Day9;

import com.ComputerMode;
import com.IntcodeComputer;

public class Main {

    private static final String dataFileName = "src/main/resources/Day9/input.txt";

    public static void main(String[] args) {

        IntcodeComputer computer = new IntcodeComputer(dataFileName, ComputerMode.SINGLE);
        computer.addInputValue(1);
        computer.run();
        System.out.println("BOOST keycode: " + computer.getLastOutput() + "\n");

        computer.reset();
        computer.addInputValue(2);
        computer.run();
        System.out.println("Coordinates of the distress signal: " + computer.getLastOutput());
    }
}
