package Zad6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpaceObject {

    private String name;
    private SpaceObject orbitCenter;
    private List<SpaceObject> orbitingSpaceObjects;

    public SpaceObject(String name, SpaceObject directlyOrbits) {
        this.name = name;
        this.orbitCenter = directlyOrbits;
        orbitingSpaceObjects = new ArrayList<>();
    }

    public void addOrbitingObject(SpaceObject spaceObject) {
        orbitingSpaceObjects.add(spaceObject);
    }

    public void setOrbitCenter(SpaceObject spaceObject) {
        orbitCenter = spaceObject;
    }

    public int countNumberOfOrbitsToCenterOfMass() {
        return orbitCenter != null ? 1 + orbitCenter.countNumberOfOrbitsToCenterOfMass() : 0;
    }

    public ArrayList getAllSpaceObjectsToReachOrbit(SpaceObject searchedOrbitCenter) {
        ArrayList<SpaceObject> allSpaceObjectsToReachCenterOfMass = new ArrayList<>();
        if(orbitCenter != searchedOrbitCenter) {
            allSpaceObjectsToReachCenterOfMass.add(orbitCenter);
            allSpaceObjectsToReachCenterOfMass.addAll(orbitCenter.getAllSpaceObjectsToReachOrbit(searchedOrbitCenter));
        }
        return allSpaceObjectsToReachCenterOfMass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceObject that = (SpaceObject) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }
}
