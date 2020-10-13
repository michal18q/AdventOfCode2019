package com;

import com.operations.Operation;

import java.util.List;

import static com.IntcodeComputer.Status.*;

public class IntcodeComputer {

    private Status status;
    private final ComputerMode computerMode;
    private MemoryManager memoryManager;
    private SystemInputs systemInputs;
    private SystemOutputs systemOutputs;
    private OperationFactory operationFactory;

    public IntcodeComputer(String dataFileName, ComputerMode computerMode) {
        ComputerMemory computerMemory = new ComputerMemory(dataFileName);
        this.computerMode = computerMode;
        memoryManager = new MemoryManager(computerMemory);
        systemInputs = new SystemInputs();
        systemOutputs = new SystemOutputs();
        operationFactory = new OperationFactory(this);
    }

    public void run() {

        status = RUNNING;

        while (status == RUNNING) {

            Operation operation = operationFactory.getCurrentOperation();
            operation.execute();
            operation.moveToNextOperation();
        }
    }

    public void reset() {
        memoryManager.resetMemory();
    }

    public void setMemoryValue(long index, long value) {
        memoryManager.setValue(index, value);
    }

    public long getValueFromMemory(long index) {
        return memoryManager.getValueFromMemory(index);
    }

    public void addInputValue(long inputValue) {
        systemInputs.addInputValue(inputValue);
    }

    public long getLastOutput() {
        return systemOutputs.getLastOutput();
    }

    public List<Long> getOutputs() {
        return systemOutputs.getOutputs();
    }

    public boolean hasFinished(){
        return status == FINISHED;
    }

    public MemoryManager getMemoryManager() {
        return memoryManager;
    }

    public SystemInputs getSystemInputs() {
        return systemInputs;
    }

    public boolean isInMultiMode() {
        return computerMode == ComputerMode.MULTI;
    }

    public void pause() {
        status = PAUSED;
    }

    public SystemOutputs getSystemOutputs() {
        return systemOutputs;
    }

    public void finish() {
        status = FINISHED;
    }

    enum Status {
        FINISHED, RUNNING, PAUSED
    }
}