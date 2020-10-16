package Day10;

public class Asteroid {

    private int positionX;
    private int positionY;
    private int numberOfDetectedAsteroids;

    public Asteroid(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getNumberOfDetectedAsteroids() {
        return numberOfDetectedAsteroids;
    }

    public void setNumberOfDetectedAsteroids(int numberOfDetectedAsteroids) {
        this.numberOfDetectedAsteroids = numberOfDetectedAsteroids;
    }

    @Override
    public String toString() {
        return "Asteroid{" +
                "distanceX=" + positionX +
                ", distanceY=" + positionY +
                ", numberOfDetectedAsteroids=" + numberOfDetectedAsteroids +
                '}';
    }
}