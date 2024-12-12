package io.github.sebastiankirsch.aoc2024

import kotlin.math.pow

fun main() {
    val scanner = scannerForInputOf(object {}.javaClass)
    val equations = mutableListOf<Pair<Long, List<Long>>>()
    scanner.findAll("(\\d+)\\: ((:?\\d+ ?)+)").forEach {
        equations.add(it.group(1).toLong() to it.group(2).split(' ').map(String::toLong))
    }
    println("Found ${equations.size} equations.")

    var solvableSum = 0L
    equations.forEach { equation ->
        val result = equation.first
        val numbers = equation.second
        val firstNumber = numbers.first()
        val otherNumbers = numbers.subList(1, numbers.size)
        solve@ for (permutations in 0..2.0.pow(otherNumbers.size).toInt()) {
            var sum = firstNumber
            for (index in 0..otherNumbers.size - 1) {
                if ((1 shl index) and permutations > 0) {
                    sum *= otherNumbers[index]
                } else {
                    sum += otherNumbers[index]
                }
            }
            if (sum == result) {
                solvableSum += result
                break@solve
            }
        }
    }
    println("Simple calibration result: $solvableSum")

    solvableSum = 0L
    equations.forEach { equation ->
        val result = equation.first
        val numbers = equation.second
        val firstNumber = numbers.first()
        val otherNumbers = numbers.subList(1, numbers.size)
        solve@ for (permutations in 0..3.0.pow(otherNumbers.size).toInt()) {
            var actionIndicator = permutations
            var sum = firstNumber
            for (index in 0..otherNumbers.size - 1) {
                val action = actionIndicator % 3
                if (action == 0) {
                    sum *= otherNumbers[index]
                } else if (action == 1) {
                    sum += otherNumbers[index]
                } else {
                    sum = (sum.toString() + otherNumbers[index]).toLong()
                }
                actionIndicator /=3
            }
            if (sum == result) {
                solvableSum += result
                break@solve
            }
        }
    }
    println("Advanced calibration result: $solvableSum")
}

class Day7() {

}