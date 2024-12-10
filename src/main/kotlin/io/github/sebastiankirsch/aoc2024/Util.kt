package io.github.sebastiankirsch.aoc2024

import java.util.Scanner

fun scannerForInputOf(clazz: Class<*>): Scanner {
    val className = clazz.name.substring(clazz.packageName.length + 1, clazz.name.indexOf("Kt$"))
    return Scanner(clazz.getResource("/${className}.txt")!!.openStream())
}

fun linesFromInputOf(clazz: Class<*>): List<String> {
    val scanner = scannerForInputOf(clazz)
    val lines = mutableListOf<String>()
    while (scanner.hasNextLine()) lines.add(scanner.nextLine())
    return lines
}