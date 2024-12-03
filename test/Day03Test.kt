import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day03Test {
    private val regexMul = Regex("""mul\((\d+),(\d+)\)""")
    private val regexDo = Regex("""do\(\)""")
    private val regexDont = Regex("""don't\(\)""")

    @Test
    fun testWithInstructions() {
        val inputLine = sequenceOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")
        val expectedSum = 48

        val result = parseAndSumMultiplications(regexMul, regexDo, regexDont, inputLine, considerInstructions = true)
        assertEquals(expectedSum, result, "The sum with instructions should be $expectedSum.")
    }

    @Test
    fun testWithoutInstructions() {
        val inputLine = sequenceOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")
        val expectedSum = 161

        val result = parseAndSumMultiplications(regexMul, regexDo, regexDont, inputLine, considerInstructions = false)
        assertEquals(expectedSum, result, "The sum without instructions should be $expectedSum.")
    }
}