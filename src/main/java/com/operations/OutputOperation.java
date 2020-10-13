package com.operations;

import com.IntcodeComputer;
import com.MemoryManager;
import com.SystemOutputs;

public class OutputOperation implements Operation {

    public static final int NUMBER_OF_PARAMETERS = 1;
    MemoryManager memoryManager;

    private SystemOutputs systemOutputs;
    private long value;
    private IntcodeComputer computer;

    public OutputOperation(IntcodeComputer computer, int[] modes) {
        this.computer = computer;
        memoryManager = computer.getMemoryManager();
        systemOutputs = computer.getSystemOutputs();
        this.value = memoryManager.getValueBasedOnMode(NUMBER_OF_PARAMETERS, modes[0]);
    }

    @Override
    public void execute() {
        systemOutputs.addOutput(value);
        if(computer.isInMultiMode()) {
            computer.pause();
        }
    }

    @Override
    public void moveToNextOperation() {
        memoryManager.skipParametersToGetToNextInstruction(NUMBER_OF_PARAMETERS);
    }
}
