package io.github.sebastiankirsch.aoc2024

import io.github.sebastiankirsch.aoc2024.util.CharMap
import java.util.function.UnaryOperator

fun main() {
    val chars = charsArrayFromInputOf(object {}.javaClass)
    var warehouse: Array<CharArray> = arrayOf()
    for (i in chars.indices) {
        if (chars[i].isNotEmpty()) continue
        warehouse = chars.copyOfRange(0, i)
        break
    }
    val commands =
        chars.filterIndexed { i, _ -> i > warehouse.size }.flatMap { it.asIterable() }

    Day15(warehouse, commands).calculateGps()
}

class Day15(chars: Array<CharArray>, val commands: List<Char>) : CharMap(chars) {
    var bot: Pair<Int, Int> = findChar('@')

    fun calculateGps(): Int {
        commands.map(Direction::from).forEach { direction ->
            if (move(bot, direction)) bot = direction.target(bot)
        }

        var gpsSum = 0
        for (y in chars.indices) {
            for (x in chars[y].indices) {
                if (chars[y][x] != 'O') continue
                gpsSum += y * 100 + x
            }
        }
        println("GPS sum is $gpsSum")
        return gpsSum
    }

    private fun move(
        point: Pair<Int, Int>,
        direction: Direction
    ): Boolean {
        val target = direction.target(point)
        var movePossible = false
        when (charAt(target)) {
            '#' -> return false
            'O' -> movePossible = move(target, direction)
            '.' -> movePossible = true
            else -> throw RuntimeException()
        }
        if (movePossible) swapChars(point, target)
        return movePossible
    }

    private fun swapChars(source: Pair<Int, Int>, target: Pair<Int, Int>) {
        val sourceChar = charAt(source)
        chars[source.second][source.first] = chars[target.second][target.first]
        chars[target.second][target.first] = sourceChar
    }


    enum class Direction(val move: UnaryOperator<Pair<Int, Int>>) {
        NORTH({ pair -> pair.first to pair.second - 1 }),
        SOUTH({ pair -> pair.first to pair.second + 1 }),
        WEST({ pair -> pair.first - 1 to pair.second }),
        EAST({ pair -> pair.first + 1 to pair.second });

        fun target(field: Pair<Int, Int>): Pair<Int, Int> {
            return move.apply(field)
        }

        companion object {
            fun from(char: Char): Direction {
                return when (char) {
                    '^' -> NORTH
                    '>' -> EAST
                    'v' -> SOUTH
                    '<' -> WEST
                    else -> throw IllegalArgumentException("$char is no valid direction!")
                }
            }
        }

    }

}