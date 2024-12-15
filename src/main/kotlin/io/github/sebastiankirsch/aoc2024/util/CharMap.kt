package io.github.sebastiankirsch.aoc2024.util

open class CharMap(val chars: Array<CharArray>) {

    fun findChar(char: Char): Pair<Int, Int> {
        (0..chars.size - 1).forEach { y ->
            (0..chars[0].size - 1).forEach { x ->
                if (chars[y][x] == char)
                    return x to y
            }
        }
        throw RuntimeException()
    }

    fun charAt(point: Pair<Int, Int>): Char {
        if (!isWithinBounds(point))
            throw ArrayIndexOutOfBoundsException("Point ${point.first},${point.second} is out of bounds!")
        return chars[point.second][point.first]
    }

    fun neighborsOf(point: Pair<Int, Int>): List<Pair<Int, Int>> {
        return listOf(
            point.first + 1 to point.second,
            point.first - 1 to point.second,
            point.first to point.second + 1,
            point.first to point.second - 1,
        ).filter { isWithinBounds(it) }
    }

    private fun isWithinBounds(point: Pair<Int, Int>): Boolean {
        return (point.first > -1 && point.second > -1 && point.second < chars.size && point.first < chars[0].size)
    }

}