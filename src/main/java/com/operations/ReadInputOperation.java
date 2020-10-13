package com.operations;

import com.MemoryManager;
import com.SystemInputs;

public class ReadInputOperation implements Operation {

    public static final int NUMBER_OF_PARAMETERS = 1;
    MemoryManager memoryManager;

    private long destinationAddress;
    private SystemInputs systemInputs;

    public ReadInputOperation(MemoryManager memoryManager, SystemInputs systemInputs, int[] modes) {
        this.memoryManager = memoryManager;
        this.systemInputs = systemInputs;
        this.destinationAddress = memoryManager.getAddressBasedOnMode(NUMBER_OF_PARAMETERS, modes[0]);
    }

    @Override
    public void execute() {
        long systemInput = systemInputs.readInput();
        memoryManager.setValue(destinationAddress, systemInput);
    }

    @Override
    public void moveToNextOperation() {
        memoryManager.skipParametersToGetToNextInstruction(NUMBER_OF_PARAMETERS);
    }
}