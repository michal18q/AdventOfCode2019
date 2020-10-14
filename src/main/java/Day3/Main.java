package Day3;

public class Main {

    private static final String inputFile = "src/main/resources/Day3/input.txt";

    public static void main(String args[]) {

        WiresMap wiresMap = new WiresMap(inputFile);

        Intersection closestIntersection = wiresMap.getIntersectionClosestToStartingPosition()
                .orElseThrow(() -> new RuntimeException("Intersection not found"));
        System.out.println("Closest intersection " + closestIntersection);

        int fewestCombinedStepsToReachIntersection = wiresMap.getFewestCombinedStepsToReachIntersection()
                .orElseThrow(() -> new RuntimeException("Intersection not found"));
        System.out.println("Fewest combined steps to reach intersection is: " + fewestCombinedStepsToReachIntersection);

    }
}