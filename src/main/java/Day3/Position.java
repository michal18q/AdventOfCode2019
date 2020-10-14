package Day3;

import java.util.Objects;

public class Position {

    private int positionX;
    private int positionY;

    public Position(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Position(Position position) {
        positionX = position.getPositionX();
        positionY = position.getPositionY();
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return positionX == position.positionX &&
                positionY == position.positionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY);
    }

    public Position moveToTheRight() {
        positionX++;
        return this;
    }

    public Position moveToTheLeft() {
        positionX--;
        return this;
    }

    public Position moveUp() {
        positionY++;
        return this;
    }

    public Position moveDown() {
        positionY--;
        return this;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionX=" + positionX +
                ", positionY=" + positionY +
                '}';
    }
}
