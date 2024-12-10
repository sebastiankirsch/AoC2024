package io.github.sebastiankirsch.aoc2024

import java.util.Scanner
import kotlin.math.abs
import kotlin.math.sign


fun isSafe(levels: List<Int>): Boolean {
    var previousLevel: Int? = null
    var trajectory = 0
    var transition = 0
    levels.forEach {
        if (previousLevel == null) {
            previousLevel = it
            return@forEach
        }
        if (!isSafe(previousLevel, it, trajectory)) return false
        transition++
        trajectory = it - previousLevel
        previousLevel = it
    }
    return true
}

fun isSafe(currentLevel: Int, nextLevel: Int, trajectory: Int): Boolean {
    val distance = abs(nextLevel - currentLevel)
    if (distance < 1 || distance > 3)
        return false
    return trajectory == 0 || trajectory.sign == (nextLevel - currentLevel).sign
}

fun main() {
    var scanner = scannerForInputOf(object {}.javaClass)
    var safeReports = 0
    var safeDampenedReports = 0
    while (scanner.hasNextLine()) {
        val lineScanner = Scanner(scanner.nextLine())
        val levels = mutableListOf<Int>()
        while (lineScanner.hasNextInt()) levels.add(lineScanner.nextInt())
        if (isSafe(levels)) {
            safeReports++
            safeDampenedReports++
        } else if ((0..levels.size - 1).any {
                val reducedLevels = levels.toMutableList()
                reducedLevels.removeAt(it)
                isSafe(reducedLevels)
            }) {
            safeDampenedReports++
        }
    }
    println("# of safe reports: $safeReports")
    println("# of safe, dampened reports: $safeDampenedReports")
}