package com.operations;

import com.MemoryManager;

public class SumOperation implements Operation {

    public static final int NUMBER_OF_PARAMETERS = 3;
    MemoryManager memoryManager;

    long value1;
    long value2;
    long destinationAddress;

    public SumOperation(MemoryManager memoryManager, int[] modes) {

        this.memoryManager = memoryManager;

        value1 = memoryManager.getValueBasedOnMode(1, modes[0]);
        value2 = memoryManager.getValueBasedOnMode(2, modes[1]);
        destinationAddress = memoryManager.getAddressBasedOnMode(3, modes[2]);
    }

    private long getOperationResult() {
        return value1 + value2;
    }

    @Override
    public void execute() {
        memoryManager.setValue(destinationAddress, getOperationResult());
    }

    @Override
    public void moveToNextOperation() {
        memoryManager.skipParametersToGetToNextInstruction(NUMBER_OF_PARAMETERS);
    }
}
