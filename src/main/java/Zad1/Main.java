package Zad1;

import java.io.*;

public class Main {

    private static final String fileName = "src/main/resources/Zad1/input.txt";

    public static void main(String[] args) {

        int totalFuel = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = reader.readLine()) != null) {
                int mass = Integer.parseInt(line);
                int fuelAmount = calculateRequiredFuel(mass);
                fuelAmount += calculateFuelRequiredToHandelPreviousFuel(fuelAmount);
                totalFuel += fuelAmount;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Fuel needed for equipment: " + totalFuel);
    }

    private static int calculateRequiredFuel(int mass) {
        return Math.floorDiv(mass, 3) - 2;
    }

    private static int calculateFuelRequiredToHandelPreviousFuel(int fuel) {
        fuel = calculateRequiredFuel(fuel);
        if (fuel > 5)
            fuel += calculateFuelRequiredToHandelPreviousFuel(fuel);
        return fuel;
    }
}
