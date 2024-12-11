package io.github.sebastiankirsch.aoc2024

import io.github.sebastiankirsch.aoc2024.Day6.Direction.NORTH
import java.util.function.UnaryOperator

fun main() {
    val chars = charsArrayFromInputOf(object {}.javaClass)

    Day6(chars).calculatePathAndObstacles()
}

class Day6(val chars: Array<CharArray>) {
    fun calculatePathAndObstacles() {
        var guardPosition = findGuard()
        var direction = NORTH

        val visitedFields = trackPath(guardPosition, direction)
        println("Guard walked over ${visitedFields.size} fields.")

        var obstacleRuns = 0
        visitedFields.toMutableList().subList(1, visitedFields.size).forEach {
            chars[it.second][it.first] = '#'
            if (trackPath(guardPosition, direction).isEmpty())
                obstacleRuns++
            chars[it.second][it.first] = '.'
        }
        println("Found $obstacleRuns obstacle positions.")
    }

    private fun trackPath(
        guardPosition: Pair<Int, Int>,
        direction: Direction
    ): Set<Pair<Int, Int>> {
        var currentDirection = direction
        val visitedFields = mutableMapOf<Pair<Int, Int>, MutableSet<Direction>>()
        var currentField: Pair<Int, Int>? = guardPosition
        traverse@ while (currentField != null) {
            if (!visitedFields.getOrPut(currentField) { mutableSetOf() }.add(currentDirection))
                return emptySet<Pair<Int, Int>>()
            val nextField = currentDirection.nextField(currentField)
            val charAtNextField = charAt(nextField)
            if (charAtNextField == '#') {
                currentDirection = currentDirection.rotate()
            } else if (charAtNextField == null) {
                break@traverse
            } else {
                currentField = nextField
            }
        }
        return visitedFields.keys
    }

    private fun findGuard(): Pair<Int, Int> {
        (0..chars.size - 1).forEach { y ->
            (0..chars[0].size - 1).forEach { x ->
                if (chars[y][x] == '^')
                    return x to y
            }
        }
        throw RuntimeException()
    }

    private fun charAt(point: Pair<Int, Int>): Char? {
        return charAt(chars, point.first, point.second)
    }

    enum class Direction(val move: UnaryOperator<Pair<Int, Int>>) {
        NORTH({ pair -> pair.first to pair.second - 1 }),
        SOUTH({ pair -> pair.first to pair.second + 1 }),
        WEST({ pair -> pair.first - 1 to pair.second }),
        EAST({ pair -> pair.first + 1 to pair.second });

        fun nextField(field: Pair<Int, Int>): Pair<Int, Int> {
            return move.apply(field)
        }

        fun rotate(): Direction {
            return when (this) {
                NORTH -> EAST
                EAST -> SOUTH
                SOUTH -> WEST
                WEST -> NORTH
            }
        }

    }

}