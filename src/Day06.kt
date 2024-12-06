fun main() {
    val mapInput = readMap().toList()

    var startTime = System.nanoTime()
    val distinctPositionsVisited = simulateGuardPath(mapInput)
    var endTime = System.nanoTime()
    var executionTime = endTime - startTime

    println("Calculated ${distinctPositionsVisited.size} positions visited in $executionTime ns.")

    startTime = System.nanoTime()
    val obstructionPositions = findLoopCausingObstructions(mapInput)
    endTime = System.nanoTime()
    executionTime = endTime - startTime

    println("Found ${obstructionPositions.size} potential obstruction positions in $executionTime ns.")
}

val DIRECTIONS = mapOf(
    '^' to Pair(-1, 0),  // up
    '>' to Pair(0, 1),   // right
    'v' to Pair(1, 0),   // down
    '<' to Pair(0, -1)   // left
)
val DIRECTION_ORDER = listOf('^', '>', 'v', '<')

fun readMap(): Sequence<String> {
    return generateSequence(::readlnOrNull).takeWhile { it.isNotBlank() }
}

fun simulateGuardPath(mapInput: List<String>): Set<Pair<Int, Int>> {
    val (initialPos, initialDir) = findGuardStart(mapInput, DIRECTIONS.keys)
    if (initialPos == null || initialDir == null) return emptySet()

    val visitedPositions = mutableSetOf(initialPos)
    var guardPos: Pair<Int, Int> = initialPos
    var guardDir: Char = initialDir

    val maxRow = mapInput.size
    val maxCol = mapInput[0].length

    while (true) {
        val (dr, dc) = DIRECTIONS[guardDir] ?: break
        val nextPos = Pair(guardPos.first + dr, guardPos.second + dc)

        if (nextPos.first !in 0..<maxRow || nextPos.second !in 0..<maxCol) break

        if (mapInput[nextPos.first][nextPos.second] == '#') {
            guardDir = DIRECTION_ORDER[(DIRECTION_ORDER.indexOf(guardDir) + 1) % 4]
        } else {
            guardPos = nextPos
            visitedPositions.add(guardPos)
        }
    }

    return visitedPositions
}

fun findGuardStart(mapInput: List<String>, directionChars: Set<Char>): Pair<Pair<Int, Int>?, Char?> {
    for ((r, line) in mapInput.withIndex()) {
        for ((c, char) in line.withIndex()) {
            if (char in directionChars) {
                return Pair(Pair(r, c), char)
            }
        }
    }
    return Pair(null, null)
}

fun findLoopCausingObstructions(mapInput: List<String>): List<Pair<Int, Int>> {
    val (startPos, startDir) = findGuardStart(mapInput, DIRECTIONS.keys)
    if (startPos == null || startDir == null) return emptyList()

    val maxRow = mapInput.size
    val maxCol = mapInput[0].length

    return mapInput.indices.flatMap { r ->
        mapInput[r].indices.mapNotNull { c ->
            val currentPos = Pair(r, c)
            if (mapInput[r][c] == '.' && currentPos != startPos && causesLoop(mapInput, startPos, startDir, currentPos, maxRow, maxCol)) {
                currentPos
            } else {
                null
            }
        }
    }
}

fun causesLoop(
    mapInput: List<String>,
    startPos: Pair<Int, Int>,
    startDir: Char,
    obstructionPos: Pair<Int, Int>,
    maxRow: Int,
    maxCol: Int
): Boolean {
    var guardPos = startPos
    var guardDir = startDir
    val path = mutableSetOf<Pair<Pair<Int, Int>, Char>>()
    path.add(Pair(guardPos, guardDir))
    var passedObstruction = false

    while (true) {
        val (dr, dc) = DIRECTIONS[guardDir] ?: break
        val nextPos = Pair(guardPos.first + dr, guardPos.second + dc)

        if (nextPos.first !in 0..<maxRow || nextPos.second !in 0..<maxCol) return false

        if (mapInput[nextPos.first][nextPos.second] == '#' || nextPos == obstructionPos) {
            guardDir = DIRECTION_ORDER[(DIRECTION_ORDER.indexOf(guardDir) + 1) % 4]
        } else {
            guardPos = nextPos
            if (!passedObstruction && guardPos != startPos) {
                passedObstruction = true
            }
            if (passedObstruction && !path.add(Pair(guardPos, guardDir))) {
                return true
            }
        }
    }
    return false
}