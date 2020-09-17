package Zad6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    private static final String inputFileName = "src/Zad6/input.txt";
    private static ArrayList<String[]> spaceObjectsWithOrbits;
    private static HashMap<String, SpaceObject> mappedSpaceObjects;

    public static void main(String[] args) {
        initializeData();
        createMap();
        int numberOfAllOrbits = countAllOrbits();
        System.out.println("The total number of direct and indirect orbits is: " + numberOfAllOrbits);

        SpaceObject objectYOU = mappedSpaceObjects.get("YOU");
        SpaceObject objectSAN = mappedSpaceObjects.get("SAN");
        int orbitalDistance = calculateOrbitalDistance(objectYOU, objectSAN);

        System.out.println("The minimum number of orbital transfers required to get YOU to SAN: " + orbitalDistance);

    }

    private static int calculateOrbitalDistance(SpaceObject objectYOU, SpaceObject objectSAN) {
        List<SpaceObject> spaceObjectsBetweenYOUandCOM = objectYOU.getAllSpaceObjectsToReachOrbit(null);
        List<SpaceObject> spaceObjectsBetweenSANandCOM = objectSAN.getAllSpaceObjectsToReachOrbit(null);

        for(SpaceObject spaceObjectBetweenYOUandCOM : spaceObjectsBetweenYOUandCOM) {
            for(SpaceObject spaceObjectBetweenSANandCOM : spaceObjectsBetweenSANandCOM) {
                if(spaceObjectBetweenYOUandCOM.equals(spaceObjectBetweenSANandCOM)) {
                    return spaceObjectsBetweenSANandCOM.indexOf(spaceObjectBetweenSANandCOM) + spaceObjectsBetweenYOUandCOM.indexOf(spaceObjectBetweenYOUandCOM);
                }
            }
        }
        return -1;
    }

    private static int countAllOrbits() {
        int numberOfAllOrbits = 0;
        for(SpaceObject spaceObject : mappedSpaceObjects.values())
            numberOfAllOrbits += spaceObject.countNumberOfOrbitsToCenterOfMass();
        return numberOfAllOrbits;
    }

    private static void createMap() {
        for (String[] spaceObjectWithOrbit : spaceObjectsWithOrbits) {
            String centerObjectName = spaceObjectWithOrbit[0];
            String orbitingObjectName = spaceObjectWithOrbit[1];
            SpaceObject centerObject = mappedSpaceObjects.containsKey(centerObjectName) ? mappedSpaceObjects.get(centerObjectName) : new SpaceObject(centerObjectName, null);
            SpaceObject orbitingObject = mappedSpaceObjects.containsKey(orbitingObjectName) ? mappedSpaceObjects.get(orbitingObjectName) : new SpaceObject(orbitingObjectName, null);

            centerObject.addOrbitingObject(orbitingObject);
            orbitingObject.setOrbitCenter(centerObject);

            mappedSpaceObjects.put(centerObjectName, centerObject);
            mappedSpaceObjects.put(orbitingObjectName, orbitingObject);
        }
        System.out.println("Mapa elegancko tego");
    }

    private static void initializeData() {
        spaceObjectsWithOrbits = new ArrayList<>();
        mappedSpaceObjects = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            String line;
            while((line = reader.readLine()) != null)
                spaceObjectsWithOrbits.add(line.split("\\)"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}