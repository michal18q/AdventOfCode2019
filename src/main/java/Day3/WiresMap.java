package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static java.lang.Integer.parseInt;

public class WiresMap {

    private List<Instruction> instructionsForWireA = new ArrayList<>();
    private List<Instruction> instructionsForWireB = new ArrayList<>();
    private List<Position> pathOfWireA;
    private List<Position> pathOfWireB;

    private final Position startingPosition = new Position(0,0);
    private Position currentPosition;

    public WiresMap(String inputFile) {
        loadInstructions(inputFile);
        pathOfWireA = createWirePath(instructionsForWireA);
        pathOfWireB = createWirePath(instructionsForWireB);
    }

    public Optional<Intersection> getIntersectionClosestToStartingPosition() {
        Optional<Intersection> closestIntersection = Optional.empty();
        for (Position positionA : pathOfWireA) {
            for (Position positionB : pathOfWireB) {
                if (positionA.equals(positionB)) {
                    Intersection intersection = new Intersection(positionA, startingPosition);
                    if (!closestIntersection.isPresent() || intersection.getDistanceFromCenter() < closestIntersection.get().getDistanceFromCenter()){
                        closestIntersection = Optional.of(intersection);
                    }
                }
            }
        }
        return closestIntersection;
    }

    public Optional<Integer> getFewestCombinedStepsToReachIntersection() {
        Optional<Integer> fewestSteps = Optional.empty();
        for (Position positionA : pathOfWireA) {
            for (Position positionB : pathOfWireB) {
                if (positionA.equals(positionB)) {
                    int stepsToIntersection = calculateTotalStepsForTwoWiresToGetToIntersection(positionA, positionB);
                    if (!fewestSteps.isPresent() || stepsToIntersection < fewestSteps.get())
                        fewestSteps = Optional.of(stepsToIntersection);
                }
            }
        }
        return fewestSteps;
    }

    private int calculateTotalStepsForTwoWiresToGetToIntersection(Position positionA, Position positionB) {
        return pathOfWireA.indexOf(positionA) + 1 + pathOfWireB.indexOf(positionB) + 1;
    }

    private void setPointersToStatingPosition() {
        currentPosition = new Position(startingPosition);
    }

    private List<Position> createWirePath(List<Instruction> instructionsForWire) {
        List<Position> pathOfWire = new ArrayList<>();
        setPointersToStatingPosition();
        for(Instruction instruction : instructionsForWire) {
            pathOfWire.addAll(getPathOfOneMove(instruction));
        }
        return pathOfWire;
    }

    private List<Position> getPathOfOneMove(Instruction instruction) {
        char direction = instruction.getDirection();
        int numberOfSteps = instruction.getNumberOfSteps();
        switch (direction) {
            case 'R' :
                return getPathWhenMoving(toTheRight(),numberOfSteps);
            case 'L' :
                return getPathWhenMoving(toTheLeft(), numberOfSteps);
            case 'D' :
                return getPathWhenMoving(down(), numberOfSteps);
            case 'U' :
                return getPathWhenMoving(up(), numberOfSteps);
            default:
                throw new InvalidParameterException("Faulty data");
        }
    }

    private List<Position> getPathWhenMoving(Supplier<Position> direction, int numberOfSteps) {
        List<Position> pathOfWire = new ArrayList<>();
        for (int i = 1; i <= numberOfSteps; i++)
            pathOfWire.add(direction.get());
        return pathOfWire;
    }

    private Supplier<Position> toTheRight() {
        return () -> new Position(currentPosition.moveToTheRight());

    }

    private Supplier<Position> toTheLeft() {
        return () -> new Position(currentPosition.moveToTheLeft());
    }

    private Supplier<Position> up() {
        return () -> new Position(currentPosition.moveUp());
    }

    private Supplier<Position> down() {
        return () -> new Position(currentPosition.moveDown());
    }

    private void loadInstructions(String inputFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            instructionsForWireA = generateInstructions(reader.readLine());
            instructionsForWireB = generateInstructions(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Instruction> generateInstructions(String lineWithInstructions) {
        String[] instructionsAsString = lineWithInstructions.split(",");
        List<Instruction> instructionsForWire = new ArrayList<>();
        for (String instructionAsString : instructionsAsString) {
            Instruction instruction = new Instruction(instructionAsString.charAt(0), parseInt(instructionAsString.substring(1)));
            instructionsForWire.add(instruction);
        }
        return instructionsForWire;
    }
}