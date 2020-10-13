package com.operations;

import com.MemoryManager;

public class JumpIfTrueOperation implements Operation {

    public static final int NUMBER_OF_PARAMETERS = 2;
    MemoryManager memoryManager;

    long valueToBeChecked;
    long newAddress;
    int[] modes;

    public JumpIfTrueOperation(MemoryManager memoryManager, int[] modes) {
        this.memoryManager = memoryManager;
        this.modes = modes;
        valueToBeChecked = memoryManager.getValueFromMemory(memoryManager.getAddressBasedOnMode(1, modes[0]));
//                getValueFromMemory(getAddressOfParameterValueBasedOnMode(modes, 1));
    }

    @Override
    public void execute() {
        if(valueToBeChecked != 0) {
            newAddress = memoryManager.getValueFromMemory(memoryManager.getAddressBasedOnMode(2, modes[1]));
        } else {
            newAddress = memoryManager.getCurrentAddress() + NUMBER_OF_PARAMETERS + 1;
        }
    }

    @Override
    public void moveToNextOperation() {
        memoryManager.moveInstructionPointerTo(newAddress);
    }
}
