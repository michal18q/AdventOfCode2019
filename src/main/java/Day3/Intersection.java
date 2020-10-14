package Day3;

public class Intersection {

    private int x;
    private int y;
    private int distanceFromCenter;

    public Intersection(Position position, Position startingPosition) {
        this.x = position.getPositionX();
        this.y = position.getPositionY();
        this.distanceFromCenter = Math.abs(x - startingPosition.getPositionX()) + Math.abs(y - startingPosition.getPositionY());
    }

    public int getDistanceFromCenter() {
        return distanceFromCenter;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "x=" + x +
                ", y=" + y +
                ", distanceFromCenter=" + distanceFromCenter +
                '}';
    }
}