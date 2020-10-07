package Day1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String fileName = "src/main/resources/Zad1/input.txt";

    public static void main(String[] args) {

        List<Integer> loadedMasses = loadData(fileName);
        int totalFuel = FuelCalculator.calculateTotalFuel(loadedMasses);

        System.out.println("Fuel needed for equipment: " + totalFuel);
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
