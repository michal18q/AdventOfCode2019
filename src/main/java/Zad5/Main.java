package Zad5;

import com.IntcodeComputer;

public class Main {

    public static final String inputFileName = "src/main/resources/Zad5/input.txt";
    public static final int inputValue = 5;

    public static void main(String[] args) {
        IntcodeComputer intcodeComputer = new IntcodeComputer(inputFileName, inputValue);
        intcodeComputer.run();
    }
}