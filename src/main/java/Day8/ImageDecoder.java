package Day8;

import java.util.*;
import java.util.stream.Collectors;

public class ImageDecoder {

    private List<List<Integer>> pictureLayers;
    private int imageHeight;
    private int imageWidth;

    public ImageDecoder(String inputData, int imageHeight, int imageWidth) {
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.pictureLayers = generatePictureLayers(inputData);
    }

    private List<List<Integer>> generatePictureLayers(String inputData) {
        List<List<Integer>> pictureLayers = new ArrayList<>();

        while(inputData.length() > 0) {
            List<Integer> layer = Arrays.stream(inputData.substring(0, (imageHeight * imageWidth)).split("")).map(Integer::parseInt).collect(Collectors.toList());
            pictureLayers.add(layer);
            inputData = inputData.substring((imageHeight * imageWidth));
        }

        return pictureLayers;
    }

    public int calculateControlNumber() {

        List<Integer> layerWithFewestZeros = getLayerWithLeastZeros();

        int numberOfOnes = countCharactersOfValue(layerWithFewestZeros, 1);
        int numberOfTwos = countCharactersOfValue(layerWithFewestZeros, 2);

        return numberOfOnes * numberOfTwos;
    }

    public void printMessage() {

        List<Integer> picture = new ArrayList<>();

        for(int currentPosition = 0; currentPosition < imageHeight * imageWidth; currentPosition++)
        {
            int currentLayer=0;
            int displayedValue;

            do {
                displayedValue = pictureLayers.get(currentLayer++).get(currentPosition);
            } while(displayedValue == 2);

            picture.add(displayedValue);
        }

        int currentPosition = 0;
        for(Integer pixel : picture) {
            System.out.print(pixel == 0 ? " " : "*");
            if(++currentPosition % imageWidth == 0) {
                System.out.println();
            }
        }
    }

    private List<Integer> getLayerWithLeastZeros() {
        return pictureLayers.stream().min(Comparator.comparing(list -> countCharactersOfValue(list, 0))).get();
    }

    private int countCharactersOfValue(List<Integer> layer, int number) {
        return (int) layer.stream().filter(pixel -> pixel == number).count();
    }
}