import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day04Test {

    @Test
    fun testCountOccurrences() {
        val testGrid = arrayOf(
            "MMMSXXMASM",
            "MSAMXMSMSA",
            "AMXSXMAAMM",
            "MSAMASMSMX",
            "XMASAMXAMM",
            "XXAMMXXAMA",
            "SMSMSASXSS",
            "SAXAMASAAA",
            "MAMMMXMMMM",
            "MXMXAXMASX"
        )
        val expectedCount = 18
        val actualCount = countWordOccurrences(testGrid, "XMAS")

        assertEquals(expectedCount, actualCount, "XMAS occurs a total of $actualCount times but should occur $expectedCount times.")
    }

    @Test
    fun testCountXShapeOccurrences() {
        val testGrid = arrayOf(
            ".M.S......",
            "..A..MSMS.",
            ".M.S.MAA..",
            "..A.ASMSM.",
            ".M.S.M....",
            "..........",
            "S.S.S.S.S.",
            ".A.A.A.A..",
            "M.M.M.M.M.",
            ".........."
        )

        val expectedCount = 9
        val actualCount = countXShapeMASOccurrences(testGrid)

        assertEquals(expectedCount, actualCount, "MAS occurs a total of $actualCount times but should occur $expectedCount times.")
    }
}