package io.github.sebastiankirsch.aoc2024

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class Day12Test {

    @Test
    fun simplest() {
        val result = Day12(arrayOf(charArrayOf('A'))).calculateFences()

        assertEquals(4, result)
    }

    @Test
    fun rowWithOnePlant() {
        val result = Day12(
            arrayOf(
                charArrayOf('A', 'A'),
            )
        ).calculateFences()

        assertEquals(12, result)
    }

    @Test
    fun rowWithTwoPlants() {
        val result = Day12(
            arrayOf(
                charArrayOf('A', 'B'),
            )
        ).calculateFences()

        assertEquals(8, result)
    }

    @Test
    fun oneSquare() {
        val result = Day12(
            arrayOf(
                charArrayOf('A', 'A'),
                charArrayOf('A', 'A'),
            )
        ).calculateFences()

        assertEquals(32, result)
    }

    @Test
    fun simpleMap() {
        val result = Day12(
            arrayOf(
                charArrayOf('A', 'A', 'A'),
                charArrayOf('A', 'A', 'B'),
                charArrayOf('A', 'B', 'B'),
            )
        ).calculateFences()
    }

}