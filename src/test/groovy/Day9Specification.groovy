import com.ComputerMode
import com.IntcodeComputer
import spock.lang.Specification

class Day9Specification extends Specification {

    def 'should return the input program'() {
        given:
        def testDataFileName = "src/test/resources/Day9/testInput.txt"
        def computer = new IntcodeComputer(testDataFileName, ComputerMode.SINGLE)

        when:
        computer.run()
        def outputs = computer.getOutputs()

        then:
        assert outputs == [109, 1, 204, -1, 1001, 100, 1, 100, 1008, 100, 16, 101, 1006, 101, 0, 99]
    }
}
