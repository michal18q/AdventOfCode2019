package com.operations;

import com.IntcodeComputer;

public class FinishOperation implements Operation {

    IntcodeComputer computer;

    public FinishOperation(IntcodeComputer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.finish();
    }

    @Override
    public void moveToNextOperation() {
    }
}
