package com;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class IntcodeComputer {

    private String dataFileName;
    private int currentAddress;
    private int[] memory;
    private List<Integer> inputs;
    private int currentInput;
    private List<Integer> outputs;
    private String status;


    public IntcodeComputer(String fileName) {
        this.dataFileName = fileName;
        memory = DataLoader.loadDataFromFile(dataFileName);
        currentInput = 0;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        currentAddress = 0;
        status = "paused";
    }

    public void reset() {
        memory = DataLoader.loadDataFromFile(dataFileName);
        currentAddress = 0;
    }

    public void run() {

        status = "running";

        while (status == "running") {
            int instructionAndModes = memory[currentAddress];

            int instruction = instructionAndModes % 100;
            int[] modes = getModes(Math.floorDiv(instructionAndModes, 100));

            switch (instruction) {
                case 1:
                    setMemoryValue(memory[currentAddress +3], addValues(modes));
                    currentAddress += 4;
                    break;
                case 2:
                    setMemoryValue(memory[currentAddress +3], multiplyValues(modes));
                    currentAddress += 4;
                    break;
                case 3:
                    setMemoryValue(memory[currentAddress+1], readSystemInput());
                    currentAddress += 2;
                    break;
                case 4:
                    outputValue();
                    currentAddress += 2;
                    status = "paused";
                    break;
                case 5:
                    currentAddress = calculateNextAddressIfTrue(modes);
                    break;
                case 6:
                    currentAddress = calculateNextAddressIfFalse(modes);
                    break;
                case 7:
                    setMemoryValue(memory[currentAddress + 3],lessThan(modes));
                    currentAddress += 4;
                    break;
                case 8:
                    setMemoryValue(memory[currentAddress + 3],equalsTo(modes));
                    currentAddress += 4;
                    break;
                case 99:
                    status = "finished";
                    break;
                default:
                    throw new IllegalArgumentException("Faulty data.");
            }
        }
    }

    private Integer readSystemInput() {
        return inputs.get(currentInput++);
    }

    private int[] getModes(int modesInt) {
        int[] modes = new int[3];
        for(int i = 0; i < 3; i++)
            modes[i] = Math.floorDiv(modesInt, (int) Math.pow(10, i)) % 10;
        return modes;
    }

    private int equalsTo(int[] modes) {
        return performOperation(modes, (v1, v2) -> v1.equals(v2) ? 1 : 0);
    }

    private int lessThan(int[] modes) {
        return performOperation(modes, (v1, v2) -> v1 < v2 ? 1 : 0);
    }

    private void outputValue() {
        outputs.add(memory[memory[currentAddress+1]]);
    }

    private int multiplyValues(int[] modes) {
        return performOperation(modes, (v1, v2) -> v1 * v2);
    }

    private int addValues(int[] modes) {
        return performOperation(modes, (v1, v2) -> v1 + v2);
    }

    private int performOperation(int[] modes, BiFunction<Integer, Integer, Integer> callback) {
        int value1 = getValue(modes[0], 1);
        int value2 = getValue(modes[1], 2);
        return callback.apply(value1, value2);
    }

    private int calculateNextAddressIfFalse(int[] modes) {
        return getCurrentAddressBasedOnCondition(modes, value -> value == 0);
    }

    private int calculateNextAddressIfTrue(int[] modes) {
        return getCurrentAddressBasedOnCondition(modes, value -> value != 0);
    }

    private int getCurrentAddressBasedOnCondition(int[] modes, Function<Integer, Boolean> condition) {
        int valueToBeChecked = getValue(modes[0], 1);
        return condition.apply(valueToBeChecked) ? getValue(modes[1], 2) : currentAddress + 3;
    }

    private int getValue(int mode, int parameterPosition) {
        return mode == 0 ? memory[memory[currentAddress + parameterPosition]] : memory[currentAddress +parameterPosition];
    }

    public void setMemoryValue(int index, int value) {
        memory[index] = value;
    }

    public int getValueFromMemory(int index) {
        return memory[index];
    }

    public void addInputValue(int inputValue) {
        inputs.add(inputValue);
    }

    public int getLastOutput() {
        return outputs.get(outputs.size()-1);
    }

    public boolean hasFinished(){
        return status.equals("finished");
    }
}