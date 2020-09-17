package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class IntcodeComputer {

    private final String dataFileName;
    private int currentAddress;
    private int[] memory;
    private int input;

    public IntcodeComputer(String dataFileName, int input) {
        this.dataFileName = dataFileName;
        this.input = input;
        currentAddress = 0;
        loadDataFromFile(dataFileName);
    }

    public void reset() {
        loadDataFromFile(dataFileName);
        currentAddress = 0;
    }

    public int getValueFromMemory(int index) {
        return memory[index];
    }

    public void loadDataFromFile(String fileName) {
        memory = new int[0];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String[] program = reader.readLine().split(",");
            memory = new int[program.length];
            for (int i =0; i< program.length; i++) {
                memory[i] = parseInt(program[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMemoryValue(int index, int value) {
        memory[index] = value;
    }

    public void run() {
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
                    finish = true;
                    break;
                default:
                    throw new IllegalArgumentException("Bledne dane.");
            }
        }
    }

    private int[] getModes(int modesInt) {
        int[] modes = new int[3];
        for(int i = 0; i < 3; i++)
            modes[i] = Math.floorDiv(modesInt, (int) Math.pow(10, i)) % 10;
        return modes;
    }

    private void equalsTo(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        memory[memory[currentAddress +3]] = value1 == value2 ? 1 : 0;
    }

    private int getValue(int mode, int parameterPosition) {
        return mode == 0 ? memory[memory[currentAddress + parameterPosition]] : memory[currentAddress +parameterPosition];
    }

    private void lessThan(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        memory[memory[currentAddress +3]] = value1 < value2 ? 1 : 0;
    }

    private int jumpIfFalse(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        return value1 == 0 ?  value2 : currentAddress + 3;
    }

    private int jumpIfTrue(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        return value1 != 0 ?  value2 : currentAddress + 3;
    }

    private void outputValue() {
        System.out.println("Output: " + memory[memory[currentAddress+1]]);
    }

    private void inputValue() {
        memory[memory[currentAddress+1]] = input;
    }

    private void multiplyValuesAndSave(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        memory[memory[currentAddress +3]] = value1 * value2;
    }

    private void addValuesAndSave(int[] modes) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        memory[memory[currentAddress +3]] = value1 + value2;
    }
}