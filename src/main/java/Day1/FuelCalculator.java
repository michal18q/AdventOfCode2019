package Day1;

import java.util.List;

public class FuelCalculator {

    public static int calculateTotalFuel(List<Integer> loadedMasses) {
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
}