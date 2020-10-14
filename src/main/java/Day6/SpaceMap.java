package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpaceMap {

    private ArrayList<String[]> spaceObjectsWithOrbits;
    private HashMap<String, SpaceObject> mappedSpaceObjects;

    public SpaceMap(String inputDataFileName) {
        initializeData(inputDataFileName);
    }

    private void initializeData(String inputFileName) {
        spaceObjectsWithOrbits = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            String line;
            while((line = reader.readLine()) != null)
                spaceObjectsWithOrbits.add(line.split("\\)"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMap() {
        mappedSpaceObjects = new HashMap<>();
        for (String[] spaceObjectWithOrbit : spaceObjectsWithOrbits) {
            String centerObjectName = spaceObjectWithOrbit[0];
            SpaceObject centerObject = mappedSpaceObjects.containsKey(centerObjectName) ? mappedSpaceObjects.get(centerObjectName) : new SpaceObject(centerObjectName);

            String orbitingObjectName = spaceObjectWithOrbit[1];
            SpaceObject orbitingObject = mappedSpaceObjects.containsKey(orbitingObjectName) ? mappedSpaceObjects.get(orbitingObjectName) : new SpaceObject(orbitingObjectName);

            centerObject.addOrbitingObject(orbitingObject);
            orbitingObject.setOrbitCenter(centerObject);

            mappedSpaceObjects.put(centerObjectName, centerObject);
            mappedSpaceObjects.put(orbitingObjectName, orbitingObject);
        }
        System.out.println("Map generated.");
    }

    public int countAllOrbits() {
        int numberOfAllOrbits = 0;
        for(SpaceObject spaceObject : mappedSpaceObjects.values())
            numberOfAllOrbits += spaceObject.countNumberOfOrbitsToCenterOfMass();
        return numberOfAllOrbits;
    }

    public int calculateOrbitalDistanceBetweenYOUandSAN() {
        SpaceObject objectYOU = mappedSpaceObjects.get("YOU");
        SpaceObject objectSAN = mappedSpaceObjects.get("SAN");
        return calculateOrbitalDistance(objectYOU, objectSAN);
    }

    private int calculateOrbitalDistance(SpaceObject objectYOU, SpaceObject objectSAN) {

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
}