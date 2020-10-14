import Day2.NounAndVerbFinder
import spock.lang.Specification

class Day2Specification extends Specification {

    def 'should calculate proper final value'() {

        given:
        def fileName = "src/main/resources/Day2/input.txt"
        def desiredValue = 19690720

        when:
        def result = NounAndVerbFinder.findNounAndVerbResultValue(fileName, desiredValue)

        then:
        result == 6979
    }
}