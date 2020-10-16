package Day10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AsteroidMap {

    private final List<Asteroid> asteroids;
    private Asteroid bestAsteroid;

    public AsteroidMap(String inputDataFileName) {
        asteroids = loadAsteroidsFromFile(inputDataFileName);
    }

    public Asteroid findBestLocationForNewMonitoringStation() {
        Asteroid bestAsteroid = null;
        for(Asteroid asteroid : asteroids) {
            int detectedAsteroids = loadSortedClockwiseDetectedAsteroids(asteroid).size();
            asteroid.setNumberOfDetectedAsteroids(detectedAsteroids);
            if(bestAsteroid == null || detectedAsteroids > bestAsteroid.getNumberOfDetectedAsteroids()) {
                bestAsteroid = asteroid;
            }
        }
        this.bestAsteroid = bestAsteroid;
        return bestAsteroid;
    }

    public Asteroid getAsteroidDeletedAs(int orderIndex) {
        if(orderIndex < 0 || orderIndex >= asteroids.size()){
            throw new IllegalArgumentException("Index of asteroid not correct");
        }
        if(bestAsteroid == null) {
            findBestLocationForNewMonitoringStation();
        }

        int vaporizedAsteroids = 0;
        while(true) {
            Map<Double, Asteroid> anglesWithAsteroids = loadSortedClockwiseDetectedAsteroids(bestAsteroid);
            for(double angle : anglesWithAsteroids.keySet()) {
                if(vaporizedAsteroids == orderIndex-1) {
                    return anglesWithAsteroids.get(angle);
                }
                asteroids.remove(anglesWithAsteroids.get(angle));
                vaporizedAsteroids++;
            }
        }
    }

    private Map<Double, Asteroid> loadSortedClockwiseDetectedAsteroids(Asteroid centerAsteroid) {
        Map<Double, Asteroid> detectedAsteroids = new TreeMap<>();
        for(Asteroid asteroid : asteroids) {
            if(!asteroid.equals(centerAsteroid)) {
                double angle = calculateAngleBetweenAsteroids(asteroid, centerAsteroid);
                if(!detectedAsteroids.containsKey(angle) || !isCloserAsteroidAlreadyDetected(detectedAsteroids.get(angle), asteroid, centerAsteroid)) {
                    detectedAsteroids.put(angle, asteroid);
                }
            }
        }
        return detectedAsteroids;
    }

    private boolean isCloserAsteroidAlreadyDetected(Asteroid alreadyDetectedAsteroid, Asteroid currentAsteroid, Asteroid centerAsteroid) {
        return calculateDistanceBetweenAsteroids(currentAsteroid, centerAsteroid) > calculateDistanceBetweenAsteroids(alreadyDetectedAsteroid, centerAsteroid);
    }

    private double calculateDistanceBetweenAsteroids(Asteroid asteroidA, Asteroid asteroidB) {
        return Math.sqrt(Math.pow(asteroidA.getPositionX()-asteroidB.getPositionX(),2) + Math.pow(asteroidA.getPositionY()-asteroidB.getPositionY(),2));
    }

    private double calculateAngleBetweenAsteroids(Asteroid asteroidA, Asteroid asteroidB) {
        return 180.0 / Math.PI * (Math.atan2(1,0) - Math.atan2(asteroidA.getPositionX() - asteroidB.getPositionX(), asteroidA.getPositionY() - asteroidB.getPositionY())) + 90.0;
    }

    private List<Asteroid> loadAsteroidsFromFile(String inputDataFileName) {
        List<Asteroid> asteroids = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputDataFileName));
            int lineNumber = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] spaceObjects = line.split("");
                for(int i = 0; i < spaceObjects.length; i++) {
                    if(spaceObjects[i].equals("#")) {
                        asteroids.add(new Asteroid(i, lineNumber));
                    }
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Data file not found");
        } catch (IOException e) {
            throw new RuntimeException("Bad data file");
        }
        return asteroids;
    }
}