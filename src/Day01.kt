import kotlin.math.abs

fun main() {
    val input = parseInput()

    val startTime = System.nanoTime()
    val sum = calculateTotalDistance(input)
    val endTime = System.nanoTime()
    val executionTime = endTime - startTime

    println("Total distance is $sum, calculated in $executionTime ns.")
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