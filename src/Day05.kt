fun main() {
    val (rules, updates) = parseInput(readQueueInputLines().toList())

    var startTime = System.nanoTime()
    val correctlyOrderedUpdates = updates.filter { isCorrectlyOrdered(it, rules) }
    val middleSum = correctlyOrderedUpdates.sumOf { findMiddlePageNumber(it) }
    var endTime = System.nanoTime()
    var executionTime = endTime - startTime

    println("Sum is $middleSum, calculated in $executionTime ns.")

    startTime = System.nanoTime()
    val incorrectlyOrderedUpdates = updates.filterNot { isCorrectlyOrdered(it, rules) }
    val correctedUpdates = incorrectlyOrderedUpdates.map { reorderUpdate(it, rules) }
    val middleSumCorrected = correctedUpdates.sumOf { findMiddlePageNumber(it) }
    endTime = System.nanoTime()
    executionTime = endTime - startTime

    println("Sum for corrected updates is $middleSumCorrected, calculated in $executionTime ns.")
}

fun readQueueInputLines(): Sequence<String> {
    return generateSequence(::readlnOrNull).takeWhile { it.isNotBlank() }
}

fun parseInput(input: List<String>): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
    val rules = mutableListOf<Pair<Int, Int>>()
    val updates = mutableListOf<List<Int>>()

    input.forEach { line ->
        when {
            '|' in line -> {
                val (first, second) = line.split("|").map { it.toInt() }
                rules.add(first to second)
            }
            ',' in line -> {
                val update = line.split(",").map { it.toInt() }
                updates.add(update)
            }
        }
    }

    return rules to updates
}

fun reorderUpdate(update: List<Int>, rules: List<Pair<Int, Int>>): List<Int> {
    val graph = mutableMapOf<Int, MutableList<Int>>()
    val inDegree = mutableMapOf<Int, Int>().withDefault { 0 }

    update.forEach { page ->
        graph[page] = mutableListOf()
        inDegree[page] = 0
    }

    rules.forEach { (first, second) ->
        if (first in graph && second in graph) {
            graph[first]?.add(second)
            inDegree[second] = inDegree.getValue(second) + 1
        }
    }

    val queue = ArrayDeque<Int>().apply {
        addAll(inDegree.filterValues { it == 0 }.keys)
    }
    val ordered = mutableListOf<Int>()

    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        ordered.add(node)

        graph[node]?.forEach { neighbor ->
            inDegree[neighbor] = inDegree.getValue(neighbor) - 1
            if (inDegree.getValue(neighbor) == 0) {
                queue.add(neighbor)
            }
        }
    }

    return ordered
}

fun isCorrectlyOrdered(update: List<Int>, rules: List<Pair<Int, Int>>): Boolean {
    val indexMap = update.withIndex().associate { it.value to it.index }
    return rules.all { (first, second) ->
        first !in indexMap || second !in indexMap || indexMap[first]!! <= indexMap[second]!!
    }
}

fun findMiddlePageNumber(update: List<Int>): Int {
    return update[update.size / 2]
}