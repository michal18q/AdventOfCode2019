package Day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static String inputFileName = "src/main/resources/Day8/input.txt";
    private static int imageWidth = 25;
    private static int imageHeight = 6;

    public static void main(String[] args) {

        String inputData = loadDataFromFile(inputFileName).get();
        ImageDecoder imageDecoder = new ImageDecoder(inputData, imageHeight, imageWidth);

        long controlNumber = imageDecoder.calculateControlNumber();
        System.out.println("Control number: " + controlNumber);

        System.out.println("\nPassword:");
        imageDecoder.printImage();
    }


    private static Optional<String> loadDataFromFile(String inputFileName) {
        Optional<String> inputData = Optional.empty();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            inputData = Optional.ofNullable(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputData;
    }
}