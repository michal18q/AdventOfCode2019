package Day10;

public class Main {

    private static final String inputDataFile = "src/main/resources/Day10/input.txt";
    private static final int searchedAsteroidIndex = 200;
//    private static final String inputDataFile = "src/test/resources/Day10/testInput1.txt";

    public static void main(String[] args) {

        AsteroidMap map = new AsteroidMap(inputDataFile);
        Asteroid bestLocationAsteroid = map.findBestLocationForNewMonitoringStation();
        System.out.println("Asteroid with best location: \n" + bestLocationAsteroid);
        System.out.println("ASTEROID to be vaporized as " + searchedAsteroidIndex + "th : " + map.getAsteroidDeletedAs(searchedAsteroidIndex));
    }
}
