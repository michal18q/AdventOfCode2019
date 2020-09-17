package Zad5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Main {

    private static final String fileName = "src/Zad5/input.txt";
    private static final int input = 5;
    private static int[] memory;
    private static int currentAddress;

    public static void main(String[] args) {
        initializeProgramData();

        boolean finish = false;
        while (!finish) {
            int instructionAndModes = memory[currentAddress];

            int instruction = instructionAndModes % 100;
            int[] modes = getModes(Math.floorDiv(instructionAndModes, 100));

            switch (instruction) {
                case 1:
                    addValuesAndSave(modes);
                    currentAddress += 4;
                    break;
                case 2:
                    multiplyValuesAndSave(modes);
                    currentAddress += 4;
                    break;
                case 3:
                    inputValue();
                    currentAddress += 2;
                    break;
                case 4:
                    outputValue();
                    currentAddress += 2;
                    break;
                case 5:
                    currentAddress = jumpIfTrue(modes);
                    break;
                case 6:
                    currentAddress = jumpIfFalse(modes);
                    break;
                case 7:
                    lessThan(modes);
                    currentAddress += 4;
                    break;
                case 8:
                    equalsTo(modes);
                    currentAddress += 4;
                    break;
                case 99:
                    System.out.println("Poprawne zakonczenie programu");
                    finish = true;
                    break;
                default:
                    throw new IllegalArgumentException("Bledne dane.");
            }
        }

    }

    private static void initializeProgramData() {
        memory = prepareData();
        currentAddress = 0;
    }

    private static int[] getModes(int modesInt) {
        int[] modes = new int[3];
        for(int i = 0; i < 3; i++)
            modes[i] = Math.floorDiv(modesInt, (int) Math.pow(10, i)) % 10;
        return modes;
    }

    private static void equalsTo(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        memory[memory[currentAddress +3]] = value1 == value2 ? 1 : 0;
    }

    private static int getValue(int mode, int parameterPosition) {
        return mode == 0 ? memory[memory[currentAddress + parameterPosition]] : memory[currentAddress +parameterPosition];
    }

    private static void lessThan(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        memory[memory[currentAddress +3]] = value1 < value2 ? 1 : 0;
    }

    private static int jumpIfFalse(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        return value1 == 0 ?  value2 : currentAddress + 3;
    }

    private static int jumpIfTrue(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        return value1 != 0 ?  value2 : currentAddress + 3;
    }

    private static void outputValue() {
        System.out.println("Output: " + memory[memory[currentAddress+1]]);
    }

    private static void inputValue() {
        memory[memory[currentAddress+1]] = input;
    }

    private static void multiplyValuesAndSave(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        memory[memory[currentAddress +3]] = value1 * value2;
    }

    private static void addValuesAndSave(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        memory[memory[currentAddress +3]] = value1 + value2;
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