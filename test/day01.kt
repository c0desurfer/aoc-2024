import org.junit.Test
import org.junit.jupiter.api.Assertions.*
class day01 {

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
            ),
            Pair(
                arrayOf(
                    mutableListOf(10, 20, 30),
                    mutableListOf(30, 20, 10)
                ),
                0
            ),
            Pair(
                arrayOf(
                    mutableListOf(5, 6, 7, 8),
                    mutableListOf(8, 7, 6, 5)
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