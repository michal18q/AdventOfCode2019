package com;

import java.util.ArrayList;
import java.util.List;

public class SystemInputs {

    private List<Long> inputs;
    private int currentInput;

    public SystemInputs() {
        this.inputs = new ArrayList<>();
        this.currentInput = 0;
    }

    public void addInputValue(long value) {
        inputs.add(value);
    }

    public long readInput() {
        return inputs.get(currentInput++);
    }
}
