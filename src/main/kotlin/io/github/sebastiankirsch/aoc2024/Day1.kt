package io.github.sebastiankirsch.aoc2024

import java.util.stream.Collectors
import kotlin.math.abs

fun main() {
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()
    val scanner = scannerForInputOf(object {}.javaClass)
    while (scanner.hasNext()) {
        leftList.add(scanner.nextInt())
        rightList.add(scanner.nextInt())
    }

    val distances = leftList.sorted().zip(rightList.sorted()).sumOf { abs(it.first - it.second) }
    println("Sum of distances: $distances")

    val occurrences = rightList.stream().collect(Collectors.toMap({ it }, { 1 }, { a, b -> a + b }))
    val similarity = leftList.sumOf { it * occurrences.getOrDefault(it, 0) }
    println("Similarity: $similarity")
}