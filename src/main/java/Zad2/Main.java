package Zad2;


import com.IntcodeComputer;

public class Main {

    private static final String fileName = "src/main/resources/Zad2/input.txt";
    private static final int desiredValue = 19690720;

    public static void main(String[] args) {

        IntcodeComputer computer = new IntcodeComputer(fileName, 0);
        findNounAndVerb(computer);

        System.out.println("Final result: " + (100 * computer.getValueFromMemory(1) + computer.getValueFromMemory(2)));

    }

    private static void findNounAndVerb(IntcodeComputer computer) {
        for(int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                resetComputer(computer, noun, verb);
                computer.run();
                if (computer.getValueFromMemory(0) == desiredValue) {
                    return;
                }
            }
        }
    }

    private static void resetComputer(IntcodeComputer computer, int noun, int verb) {
        computer.reset();
        computer.setMemoryValue(1, noun);
        computer.setMemoryValue(2, verb);
    }
}