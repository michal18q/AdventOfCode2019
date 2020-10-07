package Day2;

import com.IntcodeComputer;

public class NounAndVerbFinder {

    public static Integer findNounAndVerb(String fileName, int desiredValue) {

        IntcodeComputer computer = new IntcodeComputer(fileName);
        for(int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                resetComputer(computer, noun, verb);
                computer.run();
                if (computer.getValueFromMemory(0) == desiredValue)
                    return  100 * computer.getValueFromMemory(1) + computer.getValueFromMemory(2);
            }
        }
        return null;
    }

    private static void resetComputer(IntcodeComputer computer, int noun, int verb) {

        computer.reset();
        computer.setMemoryValue(1, noun);
        computer.setMemoryValue(2, verb);
    }
}
