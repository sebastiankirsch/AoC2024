package io.github.sebastiankirsch.aoc2024

fun main() {
    val chars = charsArrayFromInputOf(object {}.javaClass)

    Day8(chars).calculatePathAndObstacles()
}

class Day8(private val chars: Array<CharArray>) {
    fun calculatePathAndObstacles() {
        val antennasByFrequency = findAntennas()
        val antiNodes = mutableSetOf<Pair<Int, Int>>()
        antennasByFrequency.values.forEach { antennas ->
            antennas.forEach { antenna ->
                antennas.forEach { otherAntenna ->
                    if (antenna != otherAntenna) {
                        val point =
                            (antenna.first + 2 * (otherAntenna.first - antenna.first)) to (antenna.second + 2 * (otherAntenna.second - antenna.second))
                        if (charAt(point) != null) antiNodes.add(point)
                    }
                }
            }
        }
        println("# of anti-nodes: ${antiNodes.size}")
    }

    private fun findAntennas(): Map<Char, List<Pair<Int, Int>>> {
        val antennas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        chars.indices.forEach { y ->
            chars[0].indices.forEach { x ->
                if (chars[y][x] != '.') {
                    antennas.getOrPut(chars[y][x]) { mutableListOf() }.add(x to y)
                }
            }
        }
        return antennas
    }

    private fun charAt(point: Pair<Int, Int>): Char? {
        return charAt(chars, point.first, point.second)
    }

}