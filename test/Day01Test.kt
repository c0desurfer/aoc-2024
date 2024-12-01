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
            )
        )

        for ((input, expected) in testCases) {
            val result = calculateTotalDistance(input)
            assertEquals(expected, result)
        }
    }

    @Test
    fun testSimilarityScore() {
        val testCases = arrayOf(
            Pair(
                arrayOf(
                    mutableListOf(3, 4, 2, 1, 3, 3),
                    mutableListOf(4, 3, 5, 3, 9, 3)
                ),
                31
            )
        )

        for ((input, expected) in testCases) {
            val result = calculateSimilarityScore(input)
            assertEquals(expected, result)
        }
    }
}