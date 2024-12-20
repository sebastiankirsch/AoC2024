package io.github.sebastiankirsch.aoc2024

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class Day8Test {

    @Test
    fun example() {
        val result = Day8(
            arrayOf(
                "............".toCharArray(),
                "........0...".toCharArray(),
                ".....0......".toCharArray(),
                ".......0....".toCharArray(),
                "....0.......".toCharArray(),
                "......A.....".toCharArray(),
                "............".toCharArray(),
                "............".toCharArray(),
                "........A...".toCharArray(),
                ".........A..".toCharArray(),
                "............".toCharArray(),
                "............".toCharArray(),
            )
        ).calculatePathAndObstacles()

        assertEquals(14, result)
    }
    @Test
    fun harmonicExample() {
        val result = Day8(
            arrayOf(
                "............".toCharArray(),
                "........0...".toCharArray(),
                ".....0......".toCharArray(),
                ".......0....".toCharArray(),
                "....0.......".toCharArray(),
                "......A.....".toCharArray(),
                "............".toCharArray(),
                "............".toCharArray(),
                "........A...".toCharArray(),
                ".........A..".toCharArray(),
                "............".toCharArray(),
                "............".toCharArray(),
            )
        ).calculatePathAndObstaclesWithResonance()

        assertEquals(34, result)
    }

}