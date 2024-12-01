import org.junit.Test
import org.junit.jupiter.api.Assertions.*
class Day01Test {
    @Test
    fun testCalculateTotalDistance() {
        val testCases = arrayOf(
            Pair(
                arrayOf(
                    mutableListOf(3, 4, 2, 1, 3, 3),
                    mutableListOf(4, 3, 5, 3, 9, 3)
                ),
                11
            ),
            Pair(
                arrayOf(
                    mutableListOf(1, 1, 1),
                    mutableListOf(1, 1, 1)
                ),
                0
            )
        )

        for ((input, expected) in testCases) {
            val result = calculateTotalDistance(input)
            assertEquals(expected, result)
        }
    }
}