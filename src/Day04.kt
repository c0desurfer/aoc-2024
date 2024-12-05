fun main() {
    val grid = readWordInputLines().toList().toTypedArray()
    //val word = "XMAS"

    val startTime = System.nanoTime()
    val occurrences = countXShapeMASOccurrences(grid)
    val endTime = System.nanoTime()
    val executionTime = endTime - startTime

    //println("Found total of $occurrences of $word, found in $executionTime ns.")
    println("Found total of $occurrences of MAS, found in $executionTime ns.")
}

fun readWordInputLines(): Sequence<String> {
    return generateSequence(::readlnOrNull).takeWhile { it.isNotBlank() }
}

fun countWordOccurrences(grid: Array<String>, word: String): Int {
    val wordLength = word.length
    val directions = listOf(
        Pair(0, 1),  // right
        Pair(1, 0),  // down
        Pair(0, -1), // left
        Pair(-1, 0), // up
        Pair(1, 1),  // down right
        Pair(1, -1), // down left
        Pair(-1, 1), // up right
        Pair(-1, -1) // up left
    )

    fun isWithinBounds(x: Int, y: Int, dx: Int, dy: Int): Boolean {
        val endX = x + (wordLength - 1) * dx
        val endY = y + (wordLength - 1) * dy
        return endX in grid.indices && endY in grid[x].indices
    }

    fun isWordAtPosition(x: Int, y: Int, dx: Int, dy: Int): Boolean {
        return (0..<wordLength).all { i ->
            val nx = x + i * dx
            val ny = y + i * dy
            grid[nx][ny] == word[i]
        }
    }

    return grid.indices.flatMap { x ->
        grid[x].indices.flatMap { y ->
            directions.mapNotNull { (dx, dy) ->
                if (isWithinBounds(x, y, dx, dy) && isWordAtPosition(x, y, dx, dy)) 1 else null
            }
        }
    }.count()
}

fun countXShapeMASOccurrences(grid: Array<String>): Int {
    return grid.indices.flatMap { x ->
        grid[x].indices.mapNotNull { y ->
            if (isValidXShape(grid, x, y)) 1 else null
        }
    }.count()
}

fun isValidXShape(grid: Array<String>, x: Int, y: Int): Boolean {
    // "A" is center
    if (grid[x][y] != 'A') return false

    // skip edges
    if (x <= 0 || x >= grid.size - 1 || y <= 0 || y >= grid[x].length - 1) return false

    // possible patterns
    val patterns = listOf(
        listOf(Pair(-1, -1) to 'M', Pair(1, 1) to 'S', Pair(1, -1) to 'M', Pair(-1, 1) to 'S'),
        listOf(Pair(-1, -1) to 'S', Pair(1, 1) to 'M', Pair(1, -1) to 'S', Pair(-1, 1) to 'M'),
        listOf(Pair(-1, -1) to 'M', Pair(1, 1) to 'S', Pair(1, -1) to 'S', Pair(-1, 1) to 'M'),
        listOf(Pair(-1, -1) to 'S', Pair(1, 1) to 'M', Pair(1, -1) to 'M', Pair(-1, 1) to 'S')
    )

    // matching patterns
    return patterns.any { pattern ->
        pattern.all { (offset, char) ->
            val nx = x + offset.first
            val ny = y + offset.second
            nx in grid.indices && ny in grid[nx].indices && grid[nx][ny] == char
        }
    }
}
