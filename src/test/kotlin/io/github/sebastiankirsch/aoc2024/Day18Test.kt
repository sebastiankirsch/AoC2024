package io.github.sebastiankirsch.aoc2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day18Test {

    @Test
    fun example() {
        val result = Day18(
            arrayOf(
                "...#...".toCharArray(),
                "..#..#.".toCharArray(),
                "....#..".toCharArray(),
                "...#..#".toCharArray(),
                "..#..#.".toCharArray(),
                ".#..#..".toCharArray(),
                "#.#....".toCharArray(),
            )
        ).doStuff()

        assertEquals(22, result)
    }

    @Test
    fun simple() {
        val result = Day18(
            arrayOf(
                "..".toCharArray(),
                "..".toCharArray(),
            )
        ).doStuff()

        assertEquals(2, result)
    }

}