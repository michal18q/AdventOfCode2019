package Day7;

import java.util.ArrayList;
import java.util.List;

public class PhaseSettingsGenerator {

    private List<List<Integer>> allPhaseSettingsPermutations;

    public PhaseSettingsGenerator () {
        allPhaseSettingsPermutations = new ArrayList<>();
    }

    public List<List<Integer>> generateAllPhaseSettingsPermutations(int minPhaseSettingValue, int maxPhaseSettingValue) {
        ArrayList phaseSettings = new ArrayList<>();
        for(int i = minPhaseSettingValue; i <= maxPhaseSettingValue; i++)
            phaseSettings.add(i);
        getPermutations(new ArrayList<>(), phaseSettings);
        return allPhaseSettingsPermutations;
    }

    private void getPermutations(List<Integer> alreadyPermuted, List<Integer> toBePermuted) {

        if(toBePermuted.size() == 1) {
            alreadyPermuted.add(toBePermuted.get(0));
            List<Integer> finalPermutation = new ArrayList<>(alreadyPermuted);
            allPhaseSettingsPermutations.add(finalPermutation);
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