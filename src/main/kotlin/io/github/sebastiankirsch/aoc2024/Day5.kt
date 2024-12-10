package io.github.sebastiankirsch.aoc2024

fun main() {
    val scanner = scannerForInputOf(object {}.javaClass)
    val orderingRules = mutableListOf<Pair<Int, Int>>()
    val updates = mutableListOf<List<Int>>()
    scanner.findAll("(\\d+)\\|(\\d+)|(?:\\d+,?)+").forEach {
        if (it.group(1) != null) {
            orderingRules.add(it.group(1).toInt() to it.group(2).toInt())
        } else {
            updates.add(it.group(0).split(',').map(String::toInt))
        }
    }
    println("Found ${orderingRules.size} rules and ${updates.size} updates.")

    Day5(orderingRules).doStuff(updates)
}

class Day5(val orderingRules: List<Pair<Int, Int>>) {

    fun doStuff(updates: List<List<Int>>) {
        val middlePages = mutableListOf<Int>()
        val brokenUpdates = mutableListOf<List<Int>>()
        updates.forEach { update ->
            val bannedNumbers = mutableSetOf<Int>()
            var isOrdered = true
            update.forEach { pageNumber ->
                if (bannedNumbers.contains(pageNumber)) {
                    isOrdered = false
                    return@forEach
                }
                bannedNumbers.addAll(orderingRules.filter { it.second == pageNumber }.map { it.first })
            }
            if (isOrdered) {
                middlePages.add(update[update.size / 2])
            } else {
                brokenUpdates.add(update)
            }
        }
        println("Sum of middle pages: ${middlePages.sum()}")

        middlePages.clear()
        brokenUpdates.forEach { update ->
            val fixedUpdate = mutableListOf<Int>()
            update.forEach { pageNumber ->
                var pageHasBeenAdded = false
                insertLoop@ for (i in 0..fixedUpdate.size - 1) {
                    val currentNumber = fixedUpdate[i]
                    if (orderingRules.any { it.first == pageNumber && it.second == currentNumber }) {
                        fixedUpdate.add(i, pageNumber)
                        pageHasBeenAdded = true
                        break@insertLoop
                    }
                }
                if (!pageHasBeenAdded)
                fixedUpdate.add(pageNumber)
            }
            middlePages.add(fixedUpdate[fixedUpdate.size / 2])
        }
        println("Sum of fixed middle pages: ${middlePages.sum()}")
    }

}