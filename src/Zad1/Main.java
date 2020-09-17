package Zad1;

import java.io.*;

public class Main {

    private static String fileName = "input.txt";

    public static void main(String[] args) {
        System.out.println("Start");
        int totalFuel = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while(line != null) {
                int mass = Integer.parseInt(line);
                System.out.print(mass);

                int fuelAmount = calculateRequiredFuel(mass);
                fuelAmount += calculateFuelRequiredToHandelPreviousFuel(fuelAmount);
                totalFuel += fuelAmount;
                System.out.println("  total: " + totalFuel);
                line = reader.readLine();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Fuel needed for equipment: " + totalFuel);
        //System.out.println("Total fuel needed:" + (totalFuel + calculateFuelRequiredToHandelPreviousFuel(totalFuel)) );
        //4846752
    }

    private static int calculateRequiredFuel(int mass) {
        return Math.floorDiv(mass, 3) - 2;
    }

    private static int calculateFuelRequiredToHandelPreviousFuel(int fuel) {
        System.out.print("For " + fuel + " we need extra ");
        fuel = calculateRequiredFuel(fuel);
        System.out.println(fuel + " of fuel");

        if (fuel > 5) {
            fuel += calculateFuelRequiredToHandelPreviousFuel(fuel);
        }
        System.out.println("Zwracany jest: " + fuel);

        return fuel;
    }
}
