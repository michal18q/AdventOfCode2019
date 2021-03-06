package Day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ImageDecoder {

    private List<List<Integer>> imageLayers;
    private int imageHeight;
    private int imageWidth;

    public ImageDecoder(String dataFileName, int imageHeight, int imageWidth) {
        String inputData = loadDataFromFile(dataFileName).orElseThrow(() -> new IllegalArgumentException("Faulty input data"));
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.imageLayers = generateImageLayers(inputData);
    }

    private List<List<Integer>> generateImageLayers(String inputData) {
        List<List<Integer>> imageLayers = new ArrayList<>();
        while(inputData.length() > 0) {
            List<Integer> layer = Arrays.stream(inputData.substring(0, (imageHeight * imageWidth)).split("")).map(Integer::parseInt).collect(Collectors.toList());
            imageLayers.add(layer);
            inputData = inputData.substring((imageHeight * imageWidth));
        }
        return imageLayers;
    }

    public int calculateControlNumber() {
        List<Integer> layerWithFewestZeros = getLayerWithFewestZeros();
        int numberOfOnes = countCharactersOfValue(layerWithFewestZeros, 1);
        int numberOfTwos = countCharactersOfValue(layerWithFewestZeros, 2);
        return numberOfOnes * numberOfTwos;
    }

    public void printImage() {
        List<Integer> image = composeImageFromLayers();
        int currentPosition = 0;
        for(Integer pixel : image) {
            System.out.print(pixel == 0 ? " " : "*");
            if(++currentPosition % imageWidth == 0) {
                System.out.println();
            }
        }
    }

    public List<Integer> composeImageFromLayers() {
        List<Integer> image = new ArrayList<>();
        for(int currentPosition = 0; currentPosition < imageHeight * imageWidth; currentPosition++)
            image.add(getDisplayedValueFromStackedLayers(currentPosition));
        return image;
    }

    private int getDisplayedValueFromStackedLayers(int currentPosition) {
        int currentLayer=0;
        int displayedValue;
        do {
            displayedValue = imageLayers.get(currentLayer++).get(currentPosition);
        } while(displayedValue == 2);
        return displayedValue;
    }

    private List<Integer> getLayerWithFewestZeros() {
        return imageLayers.stream().min(Comparator.comparing(list -> countCharactersOfValue(list, 0))).get();
    }

    private int countCharactersOfValue(List<Integer> layer, int number) {
        return (int) layer.stream().filter(pixel -> pixel == number).count();
    }

    private Optional<String> loadDataFromFile(String inputFileName) {
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