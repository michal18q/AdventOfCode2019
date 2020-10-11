package Day7;

import com.ComputerMode;
import com.IntcodeComputer;

import java.util.ArrayList;
import java.util.List;

public class AmplifierSystem {

    private List<IntcodeComputer> amplifiers;

    public AmplifierSystem(int numberOfAmplifiers, String inputFileName, List<Integer> phaseSettings) {
        amplifiers = new ArrayList<>();
        for (int i = 0; i < numberOfAmplifiers; i++) {
            IntcodeComputer amplifier = new IntcodeComputer(inputFileName, ComputerMode.MULTI);
            amplifier.addInputValue(phaseSettings.get(i));
            amplifiers.add(amplifier);
        }
    }

    public Long generateFeedbackLoopSignalToThrusters(int firstInputSignal) {

        long currentSignal = firstInputSignal;

        while (true) {
            for (IntcodeComputer amplifier : amplifiers) {
                amplifier.addInputValue(currentSignal);
                amplifier.run();
                currentSignal = amplifier.getLastOutput();
            }

            if (amplifiers.get(amplifiers.size() - 1).hasFinished())
                return amplifiers.get(amplifiers.size() - 1).getLastOutput();
        }
    }

    public Long generateSingleSignalToThrusters(int firstInputSignal) {

        long currentSignal = firstInputSignal;

        for (IntcodeComputer amplifier : amplifiers) {
            amplifier.addInputValue(currentSignal);
            amplifier.run();
            currentSignal = amplifier.getLastOutput();
        }

        return currentSignal;
    }
}
