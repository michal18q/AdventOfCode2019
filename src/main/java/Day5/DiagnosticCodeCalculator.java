package Day5;

import com.ComputerMode;
import com.IntcodeComputer;

public class DiagnosticCodeCalculator {

    public static long calculateDiagnosticCode(String inputFileName, int systemID) {
        IntcodeComputer intcodeComputer = new IntcodeComputer(inputFileName, ComputerMode.SINGLE);
        intcodeComputer.addInputValue(systemID);
        intcodeComputer.run();
        return intcodeComputer.getLastOutput();
    }

}
