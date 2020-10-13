package com.operations;

import com.MemoryManager;

public class AdjustRelativeBaseOperation implements Operation {

    public static final int NUMBER_OF_PARAMETERS = 1;

    MemoryManager memoryManager;

    long value1;

    public AdjustRelativeBaseOperation(MemoryManager memoryManager, int[] modes) {

        this.memoryManager = memoryManager;
        value1 = memoryManager.getValueBasedOnMode(1, modes[0]);
    }

    @Override
    public void execute() {
        memoryManager.adjustRelativeBase(value1);
    }

    @Override
    public void moveToNextOperation() {
        memoryManager.skipParametersToGetToNextInstruction(NUMBER_OF_PARAMETERS);
    }
}
