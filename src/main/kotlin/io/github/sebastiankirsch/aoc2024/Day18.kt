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

    val memory = Array(71) { CharArray(71) { '.' } }
    fallingBytes.subList(0, 1024).forEach { memory[it.second][it.first] = '#' }

    Day18(memory).doStuff()
}

class Day18(chars: Array<CharArray>) : CharMap(chars) {
    fun doStuff(): Int? {
        chars.forEach(::println)
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


fun findShortestPath(
    path: List<Int>,
    vertexes: Map<Int, Set<Int>>,
    knownTiles: List<Set<Int>> = emptyList(),
    shortestPath: List<Int>? = null
): List<Int>? {
    shortestPath?.let { if (it.size <= path.size) return it }
    var newShortestPath: List<Int>? = shortestPath
    vertexes.getOrElse(path.last()) { emptySet() }.forEach { nextNode ->
        if (knownTiles.none { path.containsAll(it) }) {
            if (path.size > 2 && nextNode == path.first()) {
                newShortestPath = shortestPath(newShortestPath, path)
            } else if (!path.contains(nextNode)) {
                newShortestPath = findShortestPath(path.plus(nextNode), vertexes, knownTiles, newShortestPath)
            }
        }
    }
    return newShortestPath
}

private fun shortestPath(shortestPath: List<Int>?, candidate: List<Int>) =
    if (shortestPath == null || shortestPath.size > candidate.size) candidate else shortestPath
