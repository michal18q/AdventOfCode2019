package com;

public class MemoryManager {

    ComputerMemory computerMemory;

    public MemoryManager(ComputerMemory computerMemory) {
        this.computerMemory = computerMemory;
    }

    public long getValueBasedOnMode (int indexOfParameter, int mode) {
        return computerMemory.getValue(getAddressBasedOnMode(indexOfParameter, mode));
    }

    public long getAddressBasedOnMode(int indexOfParameter, int mode) {

        switch (mode) {
            case 0 :
                return computerMemory.getAddressInPositionMode(indexOfParameter);
            case 1 :
                return computerMemory.getAddressInImmenseMode(indexOfParameter);
            case 2 :
                return computerMemory.getAddressInRelativeMode(indexOfParameter);
            default:
                throw new IllegalStateException("Unexpected mode value: " + mode);
        }
    }

    public long getCurrentValue() {
        return computerMemory.getCurrentValue();
    }

    public void setValue(long destinationAddress, long value) {
        computerMemory.setValue(destinationAddress, value);
    }

    public long getCurrentAddress() {
        return computerMemory.getInstructionPointer();
    }

    public void skipParametersToGetToNextInstruction(int numberOfParameters) {
        computerMemory.moveInstructionPointerBy(numberOfParameters + 1);
    }

    public long getValueFromMemory(long index) {
        return computerMemory.getValue(index);
    }

    public void moveInstructionPointerTo(long newAddress) {
        computerMemory.moveInstructionPointerTo(newAddress);
    }

    public void adjustRelativeBase(long value1) {
        computerMemory.adjustRelativeBase(value1);
    }

    public void resetMemory() {
        computerMemory.reset();
    }
}
