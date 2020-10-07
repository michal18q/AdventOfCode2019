import Day7.AmplifierSystem
import spock.lang.Specification

class Day7Specification extends Specification {

    def 'should return proper single signal value' () {

        given:
        def numberOfAmplifiers = 5
        def programDataFile = "src/test/resources/Day7/part1TestInput.txt"
        def phaseSettings = [4,3,2,1,0]
        def firstInputSignal = 0

        when:
        def amplifierSystem = new AmplifierSystem(numberOfAmplifiers, programDataFile, phaseSettings)
        def finalSignal = amplifierSystem.generateSingleSignalToThrusters(firstInputSignal)

        then:
        finalSignal == 43210
    }

    def 'should return proper looped feedback signal value' () {

        given:
        def numberOfAmplifiers = 5
        def programDataFile = "src/test/resources/Day7/part2TestInput.txt"
        def phaseSettings = [9,8,7,6,5]
        def firstInputSignal = 0

        when:
        def amplifierSystem = new AmplifierSystem(numberOfAmplifiers, programDataFile, phaseSettings)
        def finalSignal = amplifierSystem.generateFeedbackLoopSignalToThrusters(firstInputSignal)

        then:
        finalSignal == 139629729
    }
}
