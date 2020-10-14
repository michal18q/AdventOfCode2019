package Day6;

public class Main {

    private static final String inputFileName = "src/main/resources/Day6/input.txt";

    public static void main(String[] args) {
        SpaceMap spaceMap = new SpaceMap(inputFileName);
        spaceMap.createMap();

        int numberOfAllOrbits = spaceMap.countAllOrbits();
        System.out.println("The total number of direct and indirect orbits is: " + numberOfAllOrbits);


        int orbitalDistance = spaceMap.calculateOrbitalDistanceBetweenYOUandSAN();
        System.out.println("The minimum number of orbital transfers required to get YOU to SAN: " + orbitalDistance);
    }
}