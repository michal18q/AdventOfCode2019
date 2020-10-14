package Day2;

import com.ComputerMode;
import com.IntcodeComputer;

import java.util.Optional;

public class NounAndVerbFinder {

    public static Optional<Long> findNounAndVerbResultValue(String fileName, int desiredValue) {

        IntcodeComputer computer = new IntcodeComputer(fileName, ComputerMode.SINGLE);

        for(int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                resetComputerWithNewStartingValues(computer, noun, verb);
                computer.run();
                if (computer.getValueFromMemory(0) == desiredValue)
                    return Optional.of(calculateResultValue(computer));
            }
        }
        return Optional.empty();
    }

    private static void resetComputerWithNewStartingValues(IntcodeComputer computer, int noun, int verb) {

        computer.reset();
        computer.setMemoryValue(1, noun);
        computer.setMemoryValue(2, verb);
    }

    private static long calculateResultValue(IntcodeComputer computer) {
        return 100 * computer.getValueFromMemory(1) + computer.getValueFromMemory(2);
    }
}
