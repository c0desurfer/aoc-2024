fun main() {
    val regexMul = Regex("""mul\((\d+),(\d+)\)""")
    val regexDo = Regex("""do\(\)""")
    val regexDont = Regex("""don't\(\)""")

    val inputLines = readInputLines()

    val startTime = System.nanoTime()
    val sum = parseAndSumMultiplications(regexMul, regexDo, regexDont, inputLines)
    val endTime = System.nanoTime()
    val executionTime = endTime - startTime

    println("Sum is $sum, calculated in $executionTime ns.")
}

fun readInputLines(): Sequence<String> {
    return generateSequence(::readlnOrNull).takeWhile { it.isNotBlank() }
}

fun parseAndSumMultiplications(
    regexMul: Regex,
    regexDo: Regex,
    regexDont: Regex,
    inputLines: Sequence<String>,
    considerInstructions: Boolean = true
): Int {
    var isEnabled = true

    return inputLines
        .flatMap { line -> findAllMatches(line, regexMul, regexDo, regexDont) }
        .fold(0) { totalSum, matchResult ->
            isEnabled = if (considerInstructions) {
                updateEnabledStatus(matchResult, regexDo, regexDont, isEnabled)
            } else {
                true
            }
            if (isEnabled && regexMul.matches(matchResult.value)) {
                totalSum + calculateMultiplication(matchResult)
            } else {
                totalSum
            }
        }
}

private fun findAllMatches(line: String, vararg regexes: Regex): List<MatchResult> {
    return regexes.flatMap { it.findAll(line).toList() }.sortedBy { it.range.first }
}

private fun updateEnabledStatus(
    matchResult: MatchResult,
    regexDo: Regex,
    regexDont: Regex,
    currentStatus: Boolean
): Boolean {
    return when {
        regexDo.matches(matchResult.value) -> true
        regexDont.matches(matchResult.value) -> false
        else -> currentStatus
    }
}

private fun calculateMultiplication(matchResult: MatchResult): Int {
    val (x, y) = matchResult.destructured
    return x.toInt() * y.toInt()
}