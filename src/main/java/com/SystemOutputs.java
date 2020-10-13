package com;

import java.util.ArrayList;
import java.util.List;

public class SystemOutputs {

    private List<Long> outputs;

    public SystemOutputs() {
        outputs = new ArrayList<>();
    }

    public void addOutput(long value) {
        outputs.add(value);
    }

    public long getLastOutput() {
        return outputs.get(outputs.size()-1);
    }

    public List<Long> getOutputs() {
        return outputs;
    }
}
