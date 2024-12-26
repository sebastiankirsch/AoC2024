package io.github.sebastiankirsch.aoc2024

import io.github.sebastiankirsch.aoc2024.util.CharMap
import kotlin.math.min

fun main() {
    val fallingBytes = with(scannerForInputOf(object {}.javaClass)) {
        val points = mutableListOf<Pair<Int, Int>>()
        while (hasNextLine()) {
            nextLine().split(',').let { points.add(it.component1().toInt() to it.component2().toInt()) }
        }
        points
    }

    Day18(memoryMap(fallingBytes, 1024)).doStuff()

    val firstBadByte = fallingBytes[bisect(fallingBytes)]
    println("First bad byte is $firstBadByte")
}

fun bisect(fallingBytes: List<Pair<Int, Int>>): Int {
    var goodIndex = 0
    var badIndex = fallingBytes.size
    do {
        val nextCandidate = goodIndex + (badIndex - goodIndex) / 2
        if (Day18(memoryMap(fallingBytes, nextCandidate)).doStuff() == null) {
            badIndex = nextCandidate
        } else {
            goodIndex = nextCandidate
        }
    } while (badIndex - goodIndex > 1)
    return badIndex - 1
}

private fun memoryMap(fallingBytes: List<Pair<Int, Int>>, numberOfBytes: Int): Array<CharArray> {
    val memory = Array(71) { CharArray(71) { '.' } }
    fallingBytes.subList(0, numberOfBytes).forEach { memory[it.second][it.first] = '#' }
    return memory
}

class Day18(chars: Array<CharArray>) : CharMap(chars) {
    fun doStuff(): Int? {
        //chars.forEach(::println)
        val minDistance = dijkstra(0 to 0, chars[0].size - 1 to chars.size - 1)
        println("Minimum distance is $minDistance")
        return minDistance
    }

    private fun dijkstra(from: Pair<Int, Int>, to: Pair<Int, Int>): Int? {
        val visitedPoints = mutableMapOf<Pair<Int, Int>, Int>()
        doDijkstra(from, 0, to, visitedPoints)
        return visitedPoints[to]
    }

    private fun doDijkstra(
        current: Pair<Int, Int>,
        distance: Int,
        target: Pair<Int, Int>,
        visitedPoints: MutableMap<Pair<Int, Int>, Int>
    ) {
        if (min(
                visitedPoints.getOrElse(current) { Int.MAX_VALUE },
                visitedPoints.getOrElse(target) { Int.MAX_VALUE }) <= distance
        ) {
            return
        }
        visitedPoints.put(current, distance)
        neighborsOf(current).filter { charAt(it) == '.' }
            .forEach { doDijkstra(it, distance + 1, target, visitedPoints) }
    }
}