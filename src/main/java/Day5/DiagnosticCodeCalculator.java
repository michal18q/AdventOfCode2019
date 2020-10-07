package Day5;

import com.IntcodeComputer;

public class DiagnosticCodeCalculator {

    public static int calculateDiagnosticCode(String inputFileName, int systemID) {
        IntcodeComputer intcodeComputer = new IntcodeComputer(inputFileName, systemID);
        intcodeComputer.run();
        return intcodeComputer.getLastOutput();
    }

}
