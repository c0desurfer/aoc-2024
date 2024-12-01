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
        if (numbers.size == 2) {
            lists[0].add(numbers[0])
            lists[1].add(numbers[1])
        } else {
            println("Invalid input on line $line. Please ensure each line contains exactly two numbers.")
        }
    }

    return lists
}

fun calculateTotalDistance(input: Array<MutableList<Int>>): Int {
    input[0].sort()
    input[1].sort()

    var sum = 0
    val size = minOf(input[0].size, input[1].size)

    for (i in 0..<size) {
        sum += abs(input[0][i] - input[1][i])
    }

    return sum
}

fun calculateSimilarityScore(input: Array<MutableList<Int>>): Int {
    require(input.size == 2) { "Input must contain exactly two lists." }

    var score = 0

    for (element in input[0]) {
        val count = input[1].count { it == element }
        score += element * count
    }

    return score
}