import kotlin.math.abs

fun main() {
    val input = parseReportsInput()

    val startTime = System.nanoTime()
    val safety = checkReportsSafety(input, true)
    val endTime = System.nanoTime()
    val executionTime = endTime - startTime
    println("Total number of safe reports is $safety, calculated in $executionTime ns.")
}

fun parseReportsInput(): List<List<Int>> {
    return generateSequence(::readLine)
        .takeWhile { it.isNotBlank() }
        .map { line ->
            line.split("\\s+".toRegex()).mapNotNull { it.toIntOrNull() }
        }
        .toList()
}

fun checkReportsSafety(input: List<List<Int>>, dampener: Boolean): Int {
    return input.count { row ->
        isReportSafe(row) || (dampener && canBecomeSafeByRemovingOneLevel(row))
    }
}

fun isReportSafe(row: List<Int>): Boolean {
    return (isStrictlyIncreasing(row) || isStrictlyDecreasing(row)) && allDifferencesCloseEnough(row)
}

fun allDifferencesCloseEnough(row: List<Int>): Boolean {
    return row.zipWithNext().all { (x, y) -> isCloseEnough(x, y) }
}

fun canBecomeSafeByRemovingOneLevel(row: List<Int>): Boolean {
    return row.indices.any { i ->
        val modifiedRow = row.filterIndexed { index, _ -> index != i }
        isReportSafe(modifiedRow)
    }
}

fun isCloseEnough(x: Int, y: Int): Boolean {
    return abs(x - y) <= 3
}

fun isStrictlyIncreasing(row: List<Int>): Boolean {
    return row.zipWithNext().all { (x, y) -> x < y }
}

fun isStrictlyDecreasing(row: List<Int>): Boolean {
    return row.zipWithNext().all { (x, y) -> x > y }
}
