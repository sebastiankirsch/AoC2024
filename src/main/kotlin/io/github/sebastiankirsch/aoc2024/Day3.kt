package io.github.sebastiankirsch.aoc2024

fun main() {
    var scanner = scannerForInputOf(object {}.javaClass)
    var result = scanner.findAll("mul\\((\\d{1,3}),(\\d{1,3})\\)")
        .map { it.group(1).toInt() * it.group(2).toInt() }
        .reduce(0) { a, b -> a + b }

    println("Sum: $result")
}