import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class Day02Test {

    @Test
    fun testCheckReportsSafety() {
        val testInput = listOf(
            listOf(7, 6, 4, 2, 1),  // safe
            listOf(1, 2, 7, 8, 9),  // unsafe
            listOf(9, 7, 6, 2, 1),  // unsafe
            listOf(1, 3, 2, 4, 5),  // unsafe
            listOf(8, 6, 4, 4, 1),  // unsafe
            listOf(1, 3, 6, 7, 9)   // safe
        )

        val expectedSafeReports = 2
        val actualSafeReports = checkReportsSafety(testInput, false)

        assertEquals(expectedSafeReports, actualSafeReports,
            "Expected $expectedSafeReports safe reports, but got $actualSafeReports.")
    }

    @Test
    fun testCheckReportsSafetyWithDampener() {
        val testInput = listOf(
            listOf(7, 6, 4, 2, 1),  // safe
            listOf(1, 2, 7, 8, 9),  // unsafe, but with dampener, one violation is allowed
            listOf(9, 7, 6, 2, 1),  // unsafe, but with dampener, one violation is allowed
            listOf(1, 3, 2, 4, 5),  // unsafe
            listOf(8, 6, 4, 4, 1),  // unsafe
            listOf(1, 3, 6, 7, 9)   // safe
        )

        val expectedSafeReports = 4
        val actualSafeReports = checkReportsSafety(testInput, true)

        assertEquals(expectedSafeReports, actualSafeReports,
            "Expected $expectedSafeReports safe reports with dampener, but got $actualSafeReports.")
    }
}
