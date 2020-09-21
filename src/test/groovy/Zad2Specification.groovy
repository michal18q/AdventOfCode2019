import Zad2.Main
import Zad2.NounAndVerbFinder
import com.IntcodeComputer
import spock.lang.Specification

class Zad2Specification extends Specification {

    def 'should calculate proper final value'() {

        given:
        def fileName = "src/main/resources/Zad2/input.txt"
        def desiredValue = 19690720

        when:
        def result = NounAndVerbFinder.findNounAndVerb(fileName, desiredValue)

        then:
        result == 6979
    }
}