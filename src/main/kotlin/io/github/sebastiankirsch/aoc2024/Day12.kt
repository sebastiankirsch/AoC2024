package io.github.sebastiankirsch.aoc2024

import io.github.sebastiankirsch.aoc2024.util.CharMap
import kotlin.math.cos

fun main() {
    val chars = charsArrayFromInputOf(object {}.javaClass)

    Day12(chars).calculateFences()
}

class Day12(chars: Array<CharArray>) : CharMap(chars) {

    fun calculateFences(): Int {
        val visitedFields = mutableSetOf<Pair<Int, Int>>()
        val fieldsToVisit = mutableSetOf<Pair<Int, Int>>(0 to 0)
        var costs = 0
        do {
            val visitedFieldsBefore = visitedFields.size
            val perimeter = identifyPlots(fieldsToVisit.first(), visitedFields, fieldsToVisit)
            val area = visitedFields.size - visitedFieldsBefore
            costs += area * perimeter
        } while (fieldsToVisit.isNotEmpty())

        println("Total fence costs are $costs")
        return costs
    }

    private fun identifyPlots(
        point: Pair<Int, Int>,
        visitedFields: MutableSet<Pair<Int, Int>>,
        fieldsToVisit: MutableSet<Pair<Int, Int>>
    ): Int {
        if (visitedFields.contains(point)) {
            return 0
        }
        fieldsToVisit.remove(point)
        visitedFields.add(point)

        val plantType = charAt(point)
        var fencesRequired = 4
        val neighbors = neighborsOf(point)
        neighbors.forEach {
            if (charAt(it) == plantType) {
                fencesRequired += identifyPlots(it, visitedFields, fieldsToVisit) -1
            } else {
                if (!visitedFields.contains(it))
                fieldsToVisit.add(it)
            }
        }
        return fencesRequired
    }

}