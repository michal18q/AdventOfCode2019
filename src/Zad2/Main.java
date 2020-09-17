package Zad2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Main {

    private static String fileName = "src/Zad2/input.txt";
    private static int[] memory;
    private static int currentAddress;
    private static int desiredValue = 19690720;

    public static void main(String[] args) {

        findNounAndVerb();
        System.out.println("Szukana para to: " + memory[1] + " i " + memory[2]);
        System.out.println("Wynik ostateczny: " + (100 * memory[1] + memory[2]));

//        System.out.println("Koncowe wartosci:");
//        for (int i : memory) {
//            System.out.print(i +", ");
//        }

    }

    private static void findNounAndVerb() {
        for(int noun = 0; noun <= 99; noun++) {
            for (int verb = 0; verb <= 99; verb++) {
                resetMemory(noun, verb);

                while (memory[currentAddress] != 99)
                {
                    switch (memory[currentAddress]) {
                        case 1:
                            addValuesAndSave();
                            break;
                        case 2:
                            multiplyValuesAndSave();
                            break;
                        default:
                            throw new IllegalArgumentException("Bledne dane.");
                    }
                    currentAddress += 4;
                }

//                System.out.println("Obrot: " + noun + " i " + verb);

                if (memory[0] == desiredValue) {
                    return;
                }
            }
        }
    }

    private static void resetMemory(int noun, int verb) {
        memory = prepareData();
        memory[1] = noun;
        memory[2] = verb;
        currentAddress = 0;
    }

    private static void multiplyValuesAndSave() {
        int value1 = memory[memory[currentAddress +1]];
        int value2 = memory[memory[currentAddress +2]];
        int newValue = value1 * value2;
        memory[memory[currentAddress +3]] = newValue;
    }

    private static void addValuesAndSave() {
        int value1 = memory[memory[currentAddress +1]];
        int value2 = memory[memory[currentAddress +2]];
        int newValue = value1 + value2;
        memory[memory[currentAddress +3]] = newValue;
    }

    private static int[] prepareData() {
        int[] values = new int[0];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String[] program = reader.readLine().split(",");
            values = new int[program.length];
            for (int i =0; i< program.length; i++) {
                values[i] = parseInt(program[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }
}
