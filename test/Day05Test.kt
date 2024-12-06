import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day05Test {

    val input = listOf(
        "47|53", "97|13", "97|61", "97|47", "75|29", "61|13", "75|53", "29|13",
        "97|29", "53|29", "61|53", "97|53", "61|29", "47|13", "75|47", "97|75",
        "47|61", "75|61", "47|29", "75|13", "53|13",
        "75,47,61,53,29", "97,61,53,29,13", "75,29,13", "75,97,47,61,53",
        "61,13,29", "97,13,75,29,47"
    )

    @Test
    fun testCorrectlyOrderedUpdates() {
        val (rules, updates) = parseInput(input)

        val correctlyOrderedUpdates = updates.filter { isCorrectlyOrdered(it, rules) }
        val middleSum = correctlyOrderedUpdates.sumOf { findMiddlePageNumber(it) }

        val expectedMiddleSum = 143
        assertEquals(expectedMiddleSum, middleSum, "The sum should be $expectedMiddleSum.")
    }

    @Test
    fun testIncorrectlyOrderedUpdates() {
        val (rules, updates) = parseInput(input)

        val incorrectlyOrderedUpdates = updates.filterNot { isCorrectlyOrdered(it, rules) }
        val correctedUpdates = incorrectlyOrderedUpdates.map { reorderUpdate(it, rules) }
        val middleSumCorrected = correctedUpdates.sumOf { findMiddlePageNumber(it) }

        val expectedMiddleSumCorrected = 123

        assertEquals(expectedMiddleSumCorrected, middleSumCorrected, "The sum for corrected updates should be $expectedMiddleSumCorrected.")
    }
}
