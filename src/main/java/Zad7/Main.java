package Zad7;

import com.IntcodeComputer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String inputFileName = "src/main/resources/Zad7/input.txt";
    private static int firstInputSignal = 0;
    private static final int minPhaseSettingValue = 0;
    private static final int maxPhaseSettingValue = 4;

    private static List<List<Integer>> allPermutations;

    public static void main(String[] args) {
        allPermutations = new ArrayList<>();
        generateAllPhaseSettingsPermutations();

        int highestFinalSignal = 0;
        List<Integer> bestSequence = null;

        for(List<Integer> phaseSettings : allPermutations) {
            int finalSignal = getFinalSignal(phaseSettings);
            if(finalSignal > highestFinalSignal) {
                highestFinalSignal = finalSignal;
                bestSequence = phaseSettings;
            }
        }

        System.out.println("Highest signal that can be sent to the thrusters: " + highestFinalSignal);
        System.out.println(bestSequence);
    }

    private static int getFinalSignal(List<Integer> phaseSettings) {
        int amplifierInputSignal = firstInputSignal;

        for (int i = 0; i < 5; i++) {
            List<Integer> amplifierInputs = new ArrayList<>();
            amplifierInputs.add(phaseSettings.get(i));
            amplifierInputs.add(amplifierInputSignal);
            IntcodeComputer amplifier = new IntcodeComputer(inputFileName, amplifierInputs);
            amplifier.run();
            List<Integer> outputs = amplifier.getOutputs();
            amplifierInputSignal = outputs.get(outputs.size()-1);
        }
        return amplifierInputSignal;
    }

    private static void generateAllPhaseSettingsPermutations() {
        ArrayList phaseSettings = new ArrayList<>();
        for(int i = minPhaseSettingValue; i <= maxPhaseSettingValue; i++)
            phaseSettings.add(i);
        getPermutations(new ArrayList<>(), phaseSettings);
    }


    private static void getPermutations(List<Integer> alreadyPermuted, List<Integer> toBePermuted) {
        if(toBePermuted.size() == 1) {
            alreadyPermuted.add(toBePermuted.get(0));
            List<Integer> finalPermutation = new ArrayList<>(alreadyPermuted);
            allPermutations.add(finalPermutation);
            alreadyPermuted.clear();
        } else {
            for (int i = 0; i < toBePermuted.size(); i++) {
                List<Integer> copyOfAlreadyPermuted = new ArrayList<>(alreadyPermuted);
                copyOfAlreadyPermuted.add(toBePermuted.get(i));
                List<Integer> copyOfToBePermuted = new ArrayList<>(toBePermuted);
                copyOfToBePermuted.remove(i);
                getPermutations(copyOfAlreadyPermuted, copyOfToBePermuted);
            }
        }
    }
}