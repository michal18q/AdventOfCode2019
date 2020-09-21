package Zad5;

public class Main {

    public static final String inputFileName = "src/main/resources/Zad5/input.txt";
    public static final int systemID = 5;

    public static void main(String[] args) {
        int diagnosticCode = DiagnosticCodeCalculator.calculateDiagnosticCode(inputFileName, systemID);
        System.out.println("Diagnostic code for system ID 5: " + diagnosticCode);
    }
}