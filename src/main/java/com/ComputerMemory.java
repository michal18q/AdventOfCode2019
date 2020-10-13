package com;

import java.util.HashMap;

public class ComputerMemory {

    private String dataSourceFileName;
    private HashMap<Long, Long> memory;
    private long instructionPointer;
    private long relativeBase;

    public ComputerMemory(String dataSourceFileName) {
        this.dataSourceFileName = dataSourceFileName;
        initialize();
    }

    private void initialize() {
        memory = DataLoader.loadDataFromFile(dataSourceFileName);
        instructionPointer = 0;
        relativeBase = 0;
    }

    public void reset() {
        initialize();
    }

    public long getCurrentValue() {
        return getValue(instructionPointer);
    }

    public long getAddressInPositionMode(int distanceFromCurrentAddress) {
        return getValue(instructionPointer + distanceFromCurrentAddress);
    }

    public long getAddressInImmenseMode(int distanceFromCurrentAddress) {
        return instructionPointer + distanceFromCurrentAddress;
    }

    public long getAddressInRelativeMode(int distanceFromCurrentAddress) {
        return relativeBase + getAddressInPositionMode(distanceFromCurrentAddress);
    }

    public long getValue(long address) {
        return memory.containsKey(address) ? memory.get(address) : 0;
    }

    public void setValue(long destinationAddress, long value) {
        memory.put(destinationAddress, value);
    }

    public long getInstructionPointer() {
        return instructionPointer;
    }

    public void moveInstructionPointerBy(int index) {
        instructionPointer += index;
    }

    public void moveInstructionPointerTo(long index) {
        instructionPointer = index;
    }

    public void adjustRelativeBase(long value) {
        relativeBase += value;
    }
}
