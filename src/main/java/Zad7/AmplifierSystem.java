package Zad7;

import com.IntcodeComputer;

import java.util.ArrayList;
import java.util.List;

public class AmplifierSystem {

    private List<IntcodeComputer> amplifiers;

    public AmplifierSystem(int numberOfAmplifiers, String inputFileName, List<Integer> phaseSettings) {
        amplifiers = new ArrayList<>();
        for (int i = 0; i < numberOfAmplifiers; i++) {
            amplifiers.add(new IntcodeComputer(inputFileName, phaseSettings.get(i)));
        }
    }

    public Integer generateFeedbackLoopSignalToThrusters(int firstInputSignal) {

        int currentSignal = firstInputSignal;

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

    public Integer generateSingleSignalToThrusters(int firstInputSignal) {

        int currentSignal = firstInputSignal;

        for (IntcodeComputer amplifier : amplifiers) {
            amplifier.addInputValue(currentSignal);
            amplifier.run();
            currentSignal = amplifier.getLastOutput();
        }

        return currentSignal;
    }
}
