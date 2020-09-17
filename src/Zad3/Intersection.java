package Zad3;

public class Intersection {

    private int x;
    private int y;
    private int distanceFromCenter;

    public Intersection(int x, int y, int startingPointX, int startingPointY) {
        this.x = x;
        this.y = y;
        this.distanceFromCenter = Math.abs(x - startingPointX) + Math.abs(y - startingPointY);
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
