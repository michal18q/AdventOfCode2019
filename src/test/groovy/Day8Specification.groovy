import Day8.ImageDecoder
import spock.lang.Specification


class Day8Specification extends Specification {

    def 'should return proper image'() {
        given:
        def imageData = "0222112222120000"
        def height = 2
        def width = 2

        when:
        def decoder = new ImageDecoder(imageData, height, width)
        def image = decoder.composeImageFromLayers()

        then:
        assert image == [0,1,1,0]
    }
}
