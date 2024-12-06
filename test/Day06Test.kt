import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day06Test {

    @Test
    fun testSimulateGuardPath() {
        val mapInput = listOf(
            "....#.....",
            ".........#",
            "..........",
            "..#.......",
            ".......#..",
            "..........",
            ".#..^.....",
            "........#.",
            "#.........",
            "......#..."
        )

        val expectedDistinctPositions = 41
        val visitedPositions = simulateGuardPath(mapInput)

        assertEquals(expectedDistinctPositions, visitedPositions.size, "The number of distinct positions visited should be $expectedDistinctPositions.")
    }

    @Test
    fun testFindLoopCausingObstructions() {
        val mapInput = listOf(
            "....#.....",
            ".........#",
            "..........",
            "..#.......",
            ".......#..",
            "..........",
            ".#..^.....",
            "........#.",
            "#.........",
            "......#..."
        )

        val obstructionPositions = findLoopCausingObstructions(mapInput)

        val expectedObstructionPositions = listOf(
            Pair(6, 3), Pair(7, 6), Pair(7, 7), Pair(8, 1), Pair(8, 3), Pair(9, 7)
        )

        assertEquals(expectedObstructionPositions.size, obstructionPositions.size, "The number of possible obstruction positions should match.")
        assertEquals(expectedObstructionPositions.toSet(), obstructionPositions.toSet(), "The obstruction positions should match the expected positions.")
    }
}
