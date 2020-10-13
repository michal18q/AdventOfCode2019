package com;

import com.operations.*;

public class OperationFactory {

    private final IntcodeComputer computer;
    private final MemoryManager memoryManager;

    public OperationFactory(IntcodeComputer computer) {
        this.computer = computer;
        this.memoryManager = computer.getMemoryManager();
    }

    public Operation getCurrentOperation() {

        int instruction = getCurrentInstruction();

        switch(instruction) {
            case 1 :
                return new SumOperation(memoryManager, getParametersModes(SumOperation.NUMBER_OF_PARAMETERS));
            case 2 :
                return new MultiplyOperation(memoryManager, getParametersModes(MultiplyOperation.NUMBER_OF_PARAMETERS));
            case 3 :
                return new ReadInputOperation(memoryManager, computer.getSystemInputs(), getParametersModes(ReadInputOperation.NUMBER_OF_PARAMETERS));
            case 4 :
                return new OutputOperation(computer, getParametersModes(OutputOperation.NUMBER_OF_PARAMETERS));
            case 5 :
                return new JumpIfTrueOperation(memoryManager, getParametersModes(JumpIfTrueOperation.NUMBER_OF_PARAMETERS));
            case 6 :
                return new JumpIfFalseOperation(memoryManager, getParametersModes(JumpIfTrueOperation.NUMBER_OF_PARAMETERS));
            case 7 :
                return new LessThanOperation(memoryManager, getParametersModes(LessThanOperation.NUMBER_OF_PARAMETERS));
            case 8 :
                return new EqualsOperation(memoryManager, getParametersModes(EqualsOperation.NUMBER_OF_PARAMETERS));
            case 9 :
                return new AdjustRelativeBaseOperation(memoryManager, getParametersModes(AdjustRelativeBaseOperation.NUMBER_OF_PARAMETERS));
            case 99 :
                return new FinishOperation(computer);
            default:
                throw new IllegalArgumentException();
        }
    }

    private int getCurrentInstruction() {
        int instructionAndParametersModes = (int) memoryManager.getCurrentValue();
        return instructionAndParametersModes % 100;
    }

    private int[] getParametersModes(int numberOfParameters) {
        int instructionAndParametersModes = (int) memoryManager.getCurrentValue();
        int modesGroupedAsOneNumber = Math.floorDiv(instructionAndParametersModes, 100);
        int[] modes = new int[numberOfParameters];
        for(int i = 0; i < modes.length; i++)
            modes[i] = Math.floorDiv(modesGroupedAsOneNumber, (int) Math.pow(10, i)) % 10;
        return modes;
    }
}