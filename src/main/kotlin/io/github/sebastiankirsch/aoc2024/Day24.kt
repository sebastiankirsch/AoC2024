package io.github.sebastiankirsch.aoc2024

interface Value {
    fun get(values: MutableMap<String, Value>): Boolean
}

data class FixedValue(val value: Boolean) : Value {
    override fun get(values: MutableMap<String, Value>): Boolean {
        return value
    }
}

data class AndGate(val inputs: Pair<String, String>) : Value {
    override fun get(values: MutableMap<String, Value>): Boolean {
        return values[inputs.first]!!.get(values) and values[inputs.second]!!.get(values)
    }
}

data class OrGate(val inputs: Pair<String, String>) : Value {
    override fun get(values: MutableMap<String, Value>): Boolean {
        return values[inputs.first]!!.get(values) || values[inputs.second]!!.get(values)
    }
}

data class XorGate(val inputs: Pair<String, String>) : Value {
    override fun get(values: MutableMap<String, Value>): Boolean {
        return values[inputs.first]!!.get(values) xor values[inputs.second]!!.get(values)
    }
}

fun main() {
    val valuesAndGates = with(scannerForInputOf(object {}.javaClass)) {
        val fixedValue = "([^:]{3}): ([01])".toRegex()
        val gate = "([^:]{3}) (AND|OR|XOR) ([^:]{3}) -> ([^:]{3})".toRegex()
        val gates = mutableMapOf<String, Value>()
        while (hasNextLine()) {
            val line = nextLine()
            fixedValue.matchEntire(line)?.let { gates.put(it.groupValues[1], FixedValue("1" == it.groupValues[2])) }
            gate.matchEntire(line)?.let {
                val gate = when (it.groupValues[2]) {
                    "AND" -> AndGate(it.groupValues[1] to it.groupValues[3])
                    "OR" -> OrGate(it.groupValues[1] to it.groupValues[3])
                    "XOR" -> XorGate(it.groupValues[1] to it.groupValues[3])
                    else -> throw RuntimeException()
                }
                gates.put(it.groupValues[4], gate)
            }
        }
        gates
    }

    var result = 0L
    var counter = -1
    while (true) {
        val gate = valuesAndGates["z" + "%02d".format(++counter)]
        if (gate == null) break
        if (gate.get(valuesAndGates)) {
            result += 1L.shl(counter)
        }
    }
    println("Result: $result")
}