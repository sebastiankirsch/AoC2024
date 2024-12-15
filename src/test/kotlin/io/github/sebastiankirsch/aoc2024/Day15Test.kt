package io.github.sebastiankirsch.aoc2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day15Test {
    @Test
    fun justTheBot() {
        val result = Day15(arrayOf(
            "###".toCharArray(),
            "#.#".toCharArray(),
            "#@#".toCharArray(),
            "###".toCharArray(),
        ), "^^>vv<".toList()).calculateGps()

        assertEquals(0, result)
    }
    @Test
    fun simplest() {
        val result = Day15(arrayOf(
            "###".toCharArray(),
            "#.#".toCharArray(),
            "#O#".toCharArray(),
            "#@#".toCharArray(),
            "###".toCharArray(),
        ), "^^>vv<".toList()).calculateGps()

        assertEquals(101, result)
    }

}