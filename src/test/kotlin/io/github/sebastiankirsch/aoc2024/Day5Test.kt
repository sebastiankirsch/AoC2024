package io.github.sebastiankirsch.aoc2024

import kotlin.test.Test

class Day5Test {

    @Test
    fun example() {
        Day5(listOf(
        47 to 53,
        97 to 13,
        97 to 61,
        97 to 47,
        75 to 29,
        61 to 13,
        75 to 53,
        29 to 13,
        97 to 29,
        53 to 29,
        61 to 53,
        97 to 53,
        61 to 29,
        47 to 13,
        75 to 47,
        97 to 75,
        47 to 61,
        75 to 61,
        47 to 29,
        75 to 13,
        53 to 13,
        )).doStuff(listOf(
            listOf(75,47,61,53,29),
            listOf(97,61,53,29,13),
            listOf(75,29,13),
            listOf(75,97,47,61,53),
            listOf(61,13,29),
            listOf(97,13,75,29,47),
        ))
    }

}