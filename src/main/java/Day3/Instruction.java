package Day3;

public class Instruction {

    private char direction;
    private int numberOfSteps;

    public Instruction(char direction, int numberOfSteps) {
        this.direction = direction;
        this.numberOfSteps = numberOfSteps;
    }

    public char getDirection() {
        return direction;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "direction=" + direction +
                ", numberOfSteps=" + numberOfSteps +
                '}';
    }
}
