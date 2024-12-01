import kotlin.math.abs

fun main() {
    val input = parseInput()

    var startTime = System.nanoTime()
    val sum = calculateTotalDistance(input)
    var endTime = System.nanoTime()
    var executionTime = endTime - startTime
    println("Total distance is $sum, calculated in $executionTime ns.")

    startTime = System.nanoTime()
    val score = calculateSimilarityScore(input)
    endTime = System.nanoTime()
    executionTime = endTime - startTime
    println("Similarity score is $score, calculated in $executionTime ns.")
}

fun parseInput(): Array<MutableList<Int>> {
    val input = generateSequence(::readLine).takeWhile { it.isNotBlank() }.toList()

    val lists = arrayOf(mutableListOf<Int>(), mutableListOf())

    for (line in input) {
        val numbers = line.split("\\s+".toRegex()).mapNotNull { it.toIntOrNull() }
        lists[0].add(numbers[0])
        lists[1].add(numbers[1])
    }

    return lists
}

fun calculateTotalDistance(input: Array<MutableList<Int>>): Int {
    return input[0].sorted().zip(input[1].sorted()).sumOf { (a, b) -> abs(a - b)}
}

fun calculateSimilarityScore(input: Array<MutableList<Int>>): Int {
    val frequencyMap = input[1].groupingBy { it }.eachCount()
    return input[0].sumOf { element -> element * (frequencyMap[element] ?: 0) }
}
