package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.IntcodeComputer.Status.*;

public class IntcodeComputer {

    private final String dataFileName;
    private long currentAddress;
    private HashMap<Long, Long> memory;
    private final List<Long> inputs;
    private int currentInput;
    private final List<Long> outputs;
    private Status status;
    private int relativeBase;
    private final ComputerMode computerMode;


    public IntcodeComputer(String fileName, ComputerMode computerMode) {
        this.dataFileName = fileName;
        this.computerMode = computerMode;
        memory = DataLoader.loadDataFromFile(dataFileName);
        currentAddress = 0;
        inputs = new ArrayList<>();
        currentInput = 0;
        outputs = new ArrayList<>();
        relativeBase = 0;
        status = PAUSED;
    }

    public void reset() {
        memory = DataLoader.loadDataFromFile(dataFileName);
        currentAddress = 0;
        relativeBase = 0;
    }

    public void run() {

        status = RUNNING;

        while (status == RUNNING) {

            int instructionAndParametersModes = (int) getValueFromMemory(currentAddress);
            int instruction = getInstruction(instructionAndParametersModes);
            int[] parametersModes = getModes(instructionAndParametersModes);
            int numberOfParameters;

            switch (instruction) {
                case 1:
                    numberOfParameters = 3;
                    long destinationAddress = getAddressOfParameterValueBasedOnMode(parametersModes, numberOfParameters);
                    long value = addValues(parametersModes);
                    setMemoryValue(destinationAddress,value);
                    moveCurrentAddressToNextInstruction(numberOfParameters);
                    break;
                case 2:
                    numberOfParameters = 3;
                    setMemoryValue(getAddressOfParameterValueBasedOnMode(parametersModes, numberOfParameters), multiplyValues(parametersModes));
                    moveCurrentAddressToNextInstruction(numberOfParameters);
                    break;
                case 3:
                    numberOfParameters = 1;
                    setMemoryValue(getAddressOfParameterValueBasedOnMode(parametersModes, numberOfParameters), readSystemInput());
                    moveCurrentAddressToNextInstruction(numberOfParameters);
                    break;
                case 4:
                    numberOfParameters = 1;
                    outputValue(parametersModes);
                    moveCurrentAddressToNextInstruction(numberOfParameters);
                    if(computerMode == ComputerMode.MULTI)
                        status = PAUSED;
                    break;
                case 5:
                    setCurrentAddress(calculateNextAddressComparingToZero(false, parametersModes));
                    break;
                case 6:
                    setCurrentAddress(calculateNextAddressComparingToZero(true, parametersModes));
                    break;
                case 7:
                    numberOfParameters = 3;
                    setMemoryValue(getAddressOfParameterValueBasedOnMode(parametersModes,numberOfParameters), getNewValueBasedOnLessThan(parametersModes));
                    moveCurrentAddressToNextInstruction(numberOfParameters);
                    break;
                case 8:
                    numberOfParameters = 3;
                    setMemoryValue(getAddressOfParameterValueBasedOnMode(parametersModes, numberOfParameters), getNewValueBasedOnEquality(parametersModes));
                    moveCurrentAddressToNextInstruction(numberOfParameters);
                    break;
                case 9:
                    numberOfParameters = 1;
                    adjustRelativeBase(parametersModes);
                    moveCurrentAddressToNextInstruction(numberOfParameters);
                    break;
                case 99:
                    status = FINISHED;
                    System.out.println("Program finished successfully.");
                    break;
                default:
                    throw new IllegalArgumentException("Faulty data.");
            }
        }
    }

    private void moveCurrentAddressToNextInstruction(int numberOfAddressesToSkip) {
        currentAddress += numberOfAddressesToSkip + 1;
    }

    private void setCurrentAddress(long address) {
        currentAddress = address;
    }

    private int getInstruction(int instructionAndModes) {
        return instructionAndModes % 100;
    }

    private Long readSystemInput() {
        return inputs.get(currentInput++);
    }

    private int[] getModes(int instructionAndModes) {
        int modesGroupedAsOneNumber = Math.floorDiv(instructionAndModes, 100);
        int[] modes = new int[3];
        for(int i = 0; i < 3; i++)
            modes[i] = Math.floorDiv(modesGroupedAsOneNumber, (int) Math.pow(10, i)) % 10;
        return modes;
    }

    private long getNewValueBasedOnEquality(int[] modes) {
        return performOperation(modes, (v1, v2) -> v1.equals(v2) ? (long) 1 : 0);
    }

    private long getNewValueBasedOnLessThan(int[] modes) {
        return performOperation(modes, (v1, v2) -> v1 < v2 ? (long) 1 : 0);
    }

    private void outputValue(int[] modes) {
        long value = getValueFromMemory(getAddressOfParameterValueBasedOnMode(modes, 1));
        System.out.println("Output: " + value);
        outputs.add(value);
    }

    private long multiplyValues(int[] modes) {
        return performOperation(modes, (v1, v2) -> v1 * v2);
    }

    private long addValues(int[] modes) {
        return performOperation(modes, Long::sum);
    }

    private long performOperation(int[] modes, BiFunction<Long, Long, Long> condition) {
        long value1 = getValueFromMemory(getAddressOfParameterValueBasedOnMode(modes, 1));
        long value2 = getValueFromMemory(getAddressOfParameterValueBasedOnMode(modes, 2));
        return condition.apply(value1, value2);
    }

    private long getCurrentAddressBasedOnCondition(int[] modes, Function<Long, Boolean> condition) {
        long valueToBeChecked = getValueFromMemory(getAddressOfParameterValueBasedOnMode(modes, 1));
        return condition.apply(valueToBeChecked) ? getValueFromMemory(getAddressOfParameterValueBasedOnMode(modes, 2)) : currentAddress + 3;
    }

    private long calculateNextAddressComparingToZero(boolean equalsToZero, int[] modes) {

        if(equalsToZero) {
            return getCurrentAddressBasedOnCondition(modes, value -> value == 0);
        } else {
            return getCurrentAddressBasedOnCondition(modes, value -> value != 0);
        }
    }

    private void adjustRelativeBase(int[] modes) {
        relativeBase += getValueFromMemory(getAddressOfParameterValueBasedOnMode(modes, 1));
    }

    private long getAddressOfParameterValueBasedOnMode(int[] modes, int parameterDistanceFromCurrentAddress) {

        long address;
        int mode = modes[parameterDistanceFromCurrentAddress-1];

        switch (mode) {
            case 0 :
                address = getAddressInPositionMode(parameterDistanceFromCurrentAddress);
                break;
            case 1 :
                address = getAddressInImmenseMode(parameterDistanceFromCurrentAddress);
                break;
            case 2 :
                address = getAddressInRelativeMode(parameterDistanceFromCurrentAddress);
                break;
            default:
                throw new IllegalStateException("Unexpected mode value: " + mode);
        }

        return address;
    }

    private long getAddressInPositionMode(int distanceFromCurrentAddress) {
        return getValueFromMemory(currentAddress + distanceFromCurrentAddress);
    }

    private long getAddressInImmenseMode(int distanceFromCurrentAddress) {
        return currentAddress + distanceFromCurrentAddress;
    }

    private long getAddressInRelativeMode(int distanceFromCurrentAddress) {
        return relativeBase + getAddressInPositionMode(distanceFromCurrentAddress);
    }

    public void setMemoryValue(long index, long value) {
        memory.put(index, value);
    }

    public long getValueFromMemory(long index) {
        return memory.containsKey(index) ? memory.get(index) : 0;
    }

    public void addInputValue(long inputValue) {
        inputs.add(inputValue);
    }

    public long getLastOutput() {
        return outputs.get(outputs.size()-1);
    }

    public List<Long> getOutputs() {
        return outputs;
    }

    public boolean hasFinished(){
        return status == FINISHED;
    }
    
    enum Status {
        FINISHED, RUNNING, PAUSED
    }
}