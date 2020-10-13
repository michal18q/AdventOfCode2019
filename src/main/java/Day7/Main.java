package Day7;

import java.util.List;

public class Main {

    private static String inputFileName = "src/main/resources/Day7/input.txt";
    private static int numberOfAmplifiers = 5;

    public static void main(String[] args) {

        solvePuzzlePartOne();
        solvePuzzlePartTwo();
    }

    private static void solvePuzzlePartTwo() {

        int firstInputSignal = 0;
        int minPhaseSettingValue = 5;
        int maxPhaseSettingValue = 9;
        PhaseSettingsGenerator generator = new PhaseSettingsGenerator();
        List<List<Integer>> allPhaseSettingsPermutations = generator.generateAllPhaseSettingsPermutations(minPhaseSettingValue, maxPhaseSettingValue);

        long highestFinalSignal = 0;
        List<Integer> bestSequence = null;

        for(List<Integer> phaseSettings : allPhaseSettingsPermutations) {
            AmplifierSystem amplifierSystem = new AmplifierSystem(numberOfAmplifiers, inputFileName, phaseSettings);
            long signalToThrusters = amplifierSystem.generateFeedbackLoopSignalToThrusters(firstInputSignal);
            if(signalToThrusters > highestFinalSignal) {
                highestFinalSignal = signalToThrusters;
                bestSequence = phaseSettings;
            }
        }

        System.out.println("Highest signal from feedback loop system that can be sent to the thrusters: " + highestFinalSignal);
        System.out.println("Phase settings sequence: " + bestSequence + "\n");
    }

    private static void solvePuzzlePartOne() {

        int firstInputSignal = 0;
        int minPhaseSettingValue = 0;
        int maxPhaseSettingValue = 4;
        PhaseSettingsGenerator generator = new PhaseSettingsGenerator();
        List<List<Integer>> allPhaseSettingsPermutations = generator.generateAllPhaseSettingsPermutations(minPhaseSettingValue, maxPhaseSettingValue);

        long highestFinalSignal = 0;
        List<Integer> bestSequence = null;

        for(List<Integer> phaseSettings : allPhaseSettingsPermutations) {
            AmplifierSystem amplifierSystem = new AmplifierSystem(numberOfAmplifiers, inputFileName, phaseSettings);
            long finalSignal = amplifierSystem.generateSingleSignalToThrusters(firstInputSignal);
            if(finalSignal > highestFinalSignal) {
                highestFinalSignal = finalSignal;
                bestSequence = phaseSettings;
            }
        }

        System.out.println("Highest signal that can be sent to the thrusters: " + highestFinalSignal);
        System.out.println("Phase settings sequence: " + bestSequence + "\n");
    }
}