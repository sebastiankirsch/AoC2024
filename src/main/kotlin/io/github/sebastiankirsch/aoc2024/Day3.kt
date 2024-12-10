package io.github.sebastiankirsch.aoc2024

fun main() {
    var result = scannerForInputOf(object {}.javaClass).findAll("(?:mul\\((\\d{1,3}),(\\d{1,3})\\))")
        .map { it.group(1).toInt() * it.group(2).toInt() }
        .reduce(0) { a, b -> a + b }
    println("Sum: $result")

    var enabled = true
    result =
        scannerForInputOf(object {}.javaClass).findAll("do\\(()\\)|don't\\(()\\)|(?:mul\\((\\d{1,3}),(\\d{1,3})\\))")
            .map {
                if (it.group(1) != null) {
                    enabled = true
                    0
                } else if (it.group(2) != null) {
                    enabled = false
                    0
                } else if (enabled) {
                    it.group(3).toInt() * it.group(4).toInt()
                } else {
                    0
                }
            }
            .reduce(0) { a, b -> a + b }

    println("Sum of enabled only: $result")
}