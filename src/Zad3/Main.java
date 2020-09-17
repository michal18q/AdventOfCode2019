package Zad3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Main {

    private static String inputFile = "src/Zad3/input.txt";

    private static List<Instruction> instructionsForWireA = new ArrayList<>();
    private static List<Instruction> instructionsForWireB = new ArrayList<>();
    private static List<Position> pathOfWireA = new ArrayList<>();
    private static List<Position> pathOfWireB = new ArrayList<>();

    private static final int startingPositionX = 0;
    private static final int startingPositionY = 0;
    private static int currentPointerX;
    private static int currentPointerY;


    public static void main(String args[]) {
        loadData();

        setPointersToStatingPosition();
        createWirePath(instructionsForWireA, pathOfWireA);
        System.out.println("Koniec wyznaczania trasy rekrutacja.A.");

        setPointersToStatingPosition();
        createWirePath(instructionsForWireB, pathOfWireB);
        System.out.println("Koniec wyznaczania trasy rekrutacja.B.");

//        Part I
//        Intersection closestIntersection = getIntersectionClosestToStartingPosition();
//        System.out.println("Closest intersection" + closestIntersection);

//        Part II
        int fewestCombinedStepsToReachIntersection = getFewestCombinedStepsToReachIntersection();
        System.out.println("Fewest combined steps to reach intersection is: " + fewestCombinedStepsToReachIntersection);

    }

    private static Intersection getIntersectionClosestToStartingPosition() {
        Intersection closestIntersection = null;
        for (Position positionA : pathOfWireA) {
            for (Position positionB : pathOfWireB) {
                if (positionA.equals(positionB)) {
                    Intersection intersection = new Intersection(positionA.getPositionX(), positionA.getPositionY(), startingPositionX, startingPositionY);
                    System.out.println(intersection);
                    if (closestIntersection == null || intersection.getDistanceFromCenter() < closestIntersection.getDistanceFromCenter()){
                        closestIntersection = intersection;
                    }
                }
            }
        }
        return closestIntersection;
    }

    private static int getFewestCombinedStepsToReachIntersection() {
        int fewestSteps = -1;
        for (Position positionA : pathOfWireA) {
            for (Position positionB : pathOfWireB) {
                if (positionA.equals(positionB)) {
                    int stepsToIntersection = pathOfWireA.indexOf(positionA) + 1 + pathOfWireB.indexOf(positionB) + 1;
                    if (fewestSteps < 0 || stepsToIntersection < fewestSteps)
                        fewestSteps = stepsToIntersection;
                }
            }
        }
        return fewestSteps;
    }


    private static void setPointersToStatingPosition() {
        currentPointerX = startingPositionX;
        currentPointerY = startingPositionY;
    }

    private static void createWirePath(List<Instruction> instructionsForWire, List<Position> pathOfWire) {
        for(Instruction instruction : instructionsForWire) {
            int numberOfSteps = instruction.getNumberOfSteps();
            char direction = instruction.getDirection();

            switch (direction) {
                case 'R' :
                    moveToTheRight(numberOfSteps, pathOfWire);
                    break;
                case 'L' :
                    moveToTheLeft(numberOfSteps, pathOfWire);
                    break;
                case 'D' :
                    moveDown(numberOfSteps, pathOfWire);
                    break;
                case 'U' :
                    moveUp(numberOfSteps, pathOfWire);
                    break;
                default:
                    throw new InvalidParameterException("Zle dane");
            }
        }
    }

    private static void moveToTheRight(int numberOfSteps, List<Position> pathOfWire) {
        for (int i = 1; i <= numberOfSteps; i++) {
            pathOfWire.add(new Position(++currentPointerX, currentPointerY));
//            map[++currentPositionX][currentPositionY] = 1;
        }
    }

    private static void moveToTheLeft(int numberOfSteps, List<Position> pathOfWire) {
        for (int i = 1; i <= numberOfSteps; i++) {
            pathOfWire.add(new Position(--currentPointerX, currentPointerY));
        }
    }

    private static void moveUp(int numberOfSteps, List<Position> pathOfWire) {
        for (int i = 1; i <= numberOfSteps; i++) {
            pathOfWire.add(new Position(currentPointerX,++currentPointerY));
//            map[currentPositionX][++currentPositionY] = 1;
        }
    }

    private static void moveDown(int numberOfSteps, List<Position> pathOfWire) {
        for (int i = 1; i <= numberOfSteps; i++) {
            pathOfWire.add(new Position(currentPointerX,--currentPointerY));
//            map[currentPositionX][--currentPositionY] = 1;
        }
    }

    private static void loadData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            String[] wireA = line.split(",");
            line = reader.readLine();
            String[] wireB = line.split(",");

            for (String wire : wireA) {
                Instruction instruction = new Instruction(wire.charAt(0),parseInt(wire.substring(1)));
                instructionsForWireA.add(instruction);
//                System.out.println(instruction);
            }

            for (String wire : wireB) {
                Instruction instruction = new Instruction(wire.charAt(0),parseInt(wire.substring(1)));
                instructionsForWireB.add(instruction);
//                System.out.println(instruction);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
