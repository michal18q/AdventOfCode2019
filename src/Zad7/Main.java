package Zad7;

import com.IntcodeComputer;

public class Main {

    public static String inputFileName = "src/Zad7/input.txt";

    public static void main(String[] args) {
        IntcodeComputer intcodeComputer = new IntcodeComputer(inputFileName, 5);
        intcodeComputer.run();
    }
}
