package com;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.IntcodeComputer.Status.*;

public class IntcodeComputer {

    private String dataFileName;
    private int currentAddress;
    private int[] memory;
    private List<Integer> inputs;
    private int currentInput;
    private List<Integer> outputs;
    private Status status;
    private int relativeBase;


    public IntcodeComputer(String fileName) {
        this.dataFileName = fileName;
        memory = DataLoader.loadDataFromFile(dataFileName);
        currentInput = 0;
        inputs = new ArrayList<>();
        outputs = new ArrayList<>();
        currentAddress = 0;
        status = PAUSED;
        relativeBase = 0;
    }

    public void reset() {
        memory = DataLoader.loadDataFromFile(dataFileName);
        currentAddress = 0;
        relativeBase = 0;
    }

    public void run() {

        status = RUNNING;

        while (status == RUNNING) {
            
            int instructionAndModes = memory[currentAddress];
            int instruction = getInstruction(instructionAndModes);
            int[] modes = getModes(instructionAndModes);

            switch (instruction) {
                case 1:
                    setMemoryValue(getNewValueDestinationAddress(3), addValues(modes));
                    moveCurrentAddressBy(4);
                    break;
                case 2:
                    setMemoryValue(getNewValueDestinationAddress(3), multiplyValues(modes));
                    moveCurrentAddressBy(4);
                    break;
                case 3:
                    setMemoryValue(getNewValueDestinationAddress(1), readSystemInput());
                    moveCurrentAddressBy(2);
                    break;
                case 4:
                    outputValue();
                    moveCurrentAddressBy(2);
//                    status = PAUSED;
                    break;
                case 5:
                    setCurrentAddress(calculateNextAddressComparingToZero(false, modes));
                    break;
                case 6:
                    setCurrentAddress(calculateNextAddressComparingToZero(true, modes));
                    break;
                case 7:
                    setMemoryValue(getNewValueDestinationAddress(3), getNewValueBasedOnLessThan(modes));
                    moveCurrentAddressBy(4);
                    break;
                case 8:
                    setMemoryValue(getNewValueDestinationAddress(3), getNewValueBasedOnEquality(modes));
                    moveCurrentAddressBy(4);
                    break;
                case 9:
                    adjustRelativeBase();
                    moveCurrentAddressBy(2);
                    break;
                case 99:
                    status = FINISHED;
                    break;
                default:
                    throw new IllegalArgumentException("Faulty data.");
            }
        }
    }

    private void adjustRelativeBase() {
        relativeBase += memory[memory[currentAddress+1]];
    }

    private int getNewValueDestinationAddress(int relativeAddressOfDestinationAddress) {
        return memory[currentAddress + relativeAddressOfDestinationAddress];
    }

    private void moveCurrentAddressBy(int relativeAddress) {
        currentAddress += relativeAddress;
    }

    private void setCurrentAddress(int address) {
        currentAddress = address;
    }

    private int getInstruction(int instructionAndModes) {
        return instructionAndModes % 100;
    }

    private Integer readSystemInput() {
        return inputs.get(currentInput++);
    }

    private int[] getModes(int instructionAndModes) {
        int modesGroupedAsOneNumber = Math.floorDiv(instructionAndModes, 100);
        int[] modes = new int[3];
        for(int i = 0; i < 3; i++)
            modes[i] = Math.floorDiv(modesGroupedAsOneNumber, (int) Math.pow(10, i)) % 10;
        return modes;
    }

    private int getNewValueBasedOnEquality(int[] modes) {
        return performOperation(modes, (v1, v2) -> v1.equals(v2) ? 1 : 0);
    }

    private int getNewValueBasedOnLessThan(int[] modes) {
        return performOperation(modes, (v1, v2) -> v1 < v2 ? 1 : 0);
    }

    private void outputValue() {
        int value = memory[memory[currentAddress+1]];
        System.out.println("Output: " + value);
        outputs.add(memory[memory[currentAddress+1]]);
    }

    private int multiplyValues(int[] modes) {
        return performOperation(modes, (v1, v2) -> v1 * v2);
    }

    private int addValues(int[] modes) {
        return performOperation(modes, (v1, v2) -> v1 + v2);
    }

    private int performOperation(int[] modes, BiFunction<Integer, Integer, Integer> condition) {
        int value1 = getValueFromMemoryBasedOnMode(modes[0], 1);
        int value2 = getValueFromMemoryBasedOnMode(modes[1], 2);
        return condition.apply(value1, value2);
    }

    private int getCurrentAddressBasedOnCondition(int[] modes, Function<Integer, Boolean> condition) {
        int valueToBeChecked = getValueFromMemoryBasedOnMode(modes[0], 1);
        return condition.apply(valueToBeChecked) ? getValueFromMemoryBasedOnMode(modes[1], 2) : currentAddress + 3;
    }

    private int calculateNextAddressComparingToZero(boolean equalsToZero, int[] modes) {
        if(equalsToZero) {
            return getCurrentAddressBasedOnCondition(modes, value -> value == 0);
        } else {
            return getCurrentAddressBasedOnCondition(modes, value -> value != 0);
        }
    }

    private int getValueFromMemoryBasedOnMode(int mode, int positionParameter) {

        int value;
        
        switch (mode) {
            case 0 :
                value = getValueInPositionMode(positionParameter);
                break;
            case 1 :
                value = getValueInImmenseMode(positionParameter);
                break;
            case 2 :
                value = getValueInRelativeMode(positionParameter);
                break;
            default:
                throw new IllegalStateException("Unexpected mode value: " + mode);
        }

        return value;
    }

    private int getValueInPositionMode(int position) {
        return memory[memory[currentAddress + position]];
    }

    private int getValueInImmenseMode(int position) {
        return memory[currentAddress + position];
    }

    private int getValueInRelativeMode(int positionParameter) {
        return memory[memory[relativeBase + positionParameter]];
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
        return status == FINISHED;
    }
    
    enum Status {
        FINISHED, RUNNING, PAUSED
    }
}