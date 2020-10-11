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

    def 'should calculate proper BOOST keycode'() {
        given:
        def testDataFileName = "src/test/resources/Day9/input.txt"
        def computer = new IntcodeComputer(testDataFileName, ComputerMode.SINGLE)
        def testModeInput = 1

        when:
        computer.addInputValue(testModeInput)
        computer.run()
        def output = computer.getLastOutput()

        then:
        assert output == 2594708277
    }

    def 'should calculate proper coordinates of the distress signal'() {
        given:
        def testDataFileName = "src/test/resources/Day9/input.txt"
        def computer = new IntcodeComputer(testDataFileName, ComputerMode.SINGLE)
        def sensorBOOSTModeInput = 2

        when:
        computer.addInputValue(sensorBOOSTModeInput)
        computer.run()
        def output = computer.getLastOutput()

        then:
        assert output == 87721
    }
}
