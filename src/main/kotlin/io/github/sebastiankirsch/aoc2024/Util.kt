package io.github.sebastiankirsch.aoc2024

import java.util.Scanner

fun scannerForInputOf(clazz: Class<*>): Scanner {
    val className = clazz.name.substring(clazz.packageName.length + 1, clazz.name.indexOf("Kt$"))
    return Scanner(clazz.getResource("/${className}.txt")!!.openStream())
}