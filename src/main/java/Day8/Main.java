package Day8;

public class Main {

    private static String inputFileName = "src/main/resources/Day8/input.txt";
    private static int imageWidth = 25;
    private static int imageHeight = 6;

    public static void main(String[] args) {

        ImageDecoder imageDecoder = new ImageDecoder(inputFileName, imageHeight, imageWidth);

        long controlNumber = imageDecoder.calculateControlNumber();
        System.out.println("Control number: " + controlNumber);

        System.out.println("\nPassword:");
        imageDecoder.printImage();
    }
}