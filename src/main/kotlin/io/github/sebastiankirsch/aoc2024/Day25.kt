package io.github.sebastiankirsch.aoc2024

fun main() {
    val (locks, keys) = with(scannerForInputOf(object {}.javaClass)) {
        val locks = mutableListOf<IntArray>()
        val keys = mutableListOf<IntArray>()
        var array: IntArray? = null
        var pins = 0
        while (hasNextLine()) {
            val line = nextLine()
            if (line.isEmpty()) {
                array = null
            } else if (array == null) {
                array = IntArray(line.length)
                if ("#####" == line) locks else {
                    keys
                }.add(array)
            } else {
                if (pins++ < 5) {
                    for (i in array.indices) {
                        array[i] += if (line[i] == '#') 1 else 0
                    }
                } else pins = 0
            }
        }
        locks to keys
    }

    println("There are ${locks.size} locks and ${keys.size} keys.")

    var fittingPairs = 0
    locks.forEach { lock ->
        keys.forEach { key ->
            var match = true
            for (pin in 0..4) {
                if (lock[pin] + key[pin] > 5) match = false
            }
            if (match) fittingPairs++
        }
    }

    println("There are $fittingPairs lock/key pairs that don't overlap.")

}