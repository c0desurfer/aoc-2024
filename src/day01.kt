import kotlin.math.abs

fun main() {
    val input = arrayOf(
        mutableListOf(3, 4, 2, 1, 3, 3),
        mutableListOf(4, 3, 5, 3, 9, 3)
    )

    val startTime = System.nanoTime()
    val sum = calculateTotalDistance(input)
    val endTime = System.nanoTime()
    val executionTime = endTime - startTime

    println("Total distance is $sum, calculated in $executionTime ns.")
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