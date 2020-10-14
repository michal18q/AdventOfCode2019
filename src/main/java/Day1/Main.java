package Day1;

public class Main {

    private static final String fileName = "src/main/resources/Zad1/input.txt";

    public static void main(String[] args) {

        int totalFuel = FuelCalculator.calculateTotalFuel(fileName);

        System.out.println("Fuel needed for equipment: " + totalFuel);
    }
}
