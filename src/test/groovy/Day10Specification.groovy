import Day10.AsteroidMap
import spock.lang.Specification

class Day10Specification extends Specification {

    def 'should find the best new location for small map' () {
        given:
        def inputDataFile = "src/test/resources/Day10/testInput1.txt"
        def map = new AsteroidMap(inputDataFile)

        when:
        def bestLocationAsteroid = map.findBestLocationForNewMonitoringStation()

        then:
        assert bestLocationAsteroid.getNumberOfDetectedAsteroids() == 8
    }

    def 'should find the best new location for big map' () {
        given:
        def inputDataFile = "src/main/resources/Day10/input.txt"
        def map = new AsteroidMap(inputDataFile)

        when:
        def bestLocationAsteroid = map.findBestLocationForNewMonitoringStation()

        then:
        assert bestLocationAsteroid.getNumberOfDetectedAsteroids() == 263
    }

    def 'should find asteroid to be vaporized as 200th' () {
        given:
        def inputDataFile = "src/main/resources/Day10/input.txt"
        def map = new AsteroidMap(inputDataFile)
        def searchedIndex = 200

        when:
        def asteroidToBeDeleted = map.getAsteroidDeletedAs(searchedIndex)

        then:
        assert asteroidToBeDeleted.positionX == 11
        assert asteroidToBeDeleted.positionY == 10
    }
}
