package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuelCalculator {

    public static int calculateTotalFuel(String dataFileName) {
        List<Integer> loadedMasses = loadData(dataFileName);

        int totalFuel = 0;
        for(Integer mass : loadedMasses) {
            int fuelAmount = calculateRequiredFuel(mass);
            fuelAmount += calculateFuelRequiredToHandelPreviousFuel(fuelAmount);
            totalFuel += fuelAmount;
            }
        return totalFuel;
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

    private static List<Integer> loadData(String fileName) {
        List<Integer> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = reader.readLine()) != null) {
                data.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}