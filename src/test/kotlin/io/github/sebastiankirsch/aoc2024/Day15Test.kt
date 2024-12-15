package io.github.sebastiankirsch.aoc2024

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day15Test {
    @Test
    fun justTheBot() {
        val result = Day15(
            arrayOf(
                "###".toCharArray(),
                "#.#".toCharArray(),
                "#@#".toCharArray(),
                "###".toCharArray(),
            ), "^^>vv<".toList()
        ).calculateGps()

        assertEquals(0, result)
    }

    @Test
    fun simplest() {
        val result = Day15(
            arrayOf(
                "###".toCharArray(),
                "#.#".toCharArray(),
                "#O#".toCharArray(),
                "#@#".toCharArray(),
                "###".toCharArray(),
            ), "^^>vv<".toList()
        ).calculateGps()

        assertEquals(101, result)
    }

    @Test
    fun example() {
        val result = Day15(
            arrayOf(
                "########".toCharArray(),
                "#..O.O.#".toCharArray(),
                "##@.O..#".toCharArray(),
                "#...O..#".toCharArray(),
                "#.#.O..#".toCharArray(),
                "#...O..#".toCharArray(),
                "#......#".toCharArray(),
                "########".toCharArray(),
            ), "<^^>>>vv<v>>v<<".toList()
        ).calculateGps()

        assertEquals(2028, result)
    }

    @Test
    fun wideSimple() {
        val result = Day15(
            arrayOf(
                "######".toCharArray(),
                "##..##".toCharArray(),
                "##[]##".toCharArray(),
                "##@.##".toCharArray(),
                "######".toCharArray(),
            ), "^^>vv<".toList()
        ).calculateGps()

        assertEquals(102, result)
    }

    @Test
    fun wideSmallExample() {
        val result = Day15(
            widenWarehouse(
                arrayOf(
                    "#######".toCharArray(),
                    "#...#.#".toCharArray(),
                    "#.....#".toCharArray(),
                    "#..OO@#".toCharArray(),
                    "#..O..#".toCharArray(),
                    "#.....#".toCharArray(),
                    "#######".toCharArray(),
                )
            ), "<vv<<^^<<^^".toList()
        ).calculateGps()

        assertEquals(105 + 207 + 306, result)
    }

    @Test
    fun wideLargeExample() {
        val result = Day15(
            widenWarehouse(
                arrayOf(
                    "##########".toCharArray(),
                    "#..O..O.O#".toCharArray(),
                    "#......O.#".toCharArray(),
                    "#.OO..O.O#".toCharArray(),
                    "#..O@..O.#".toCharArray(),
                    "#O#..O...#".toCharArray(),
                    "#O..O..O.#".toCharArray(),
                    "#.OO.O.OO#".toCharArray(),
                    "#....O...#".toCharArray(),
                    "##########".toCharArray(),
                )
            ),
            "<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^>^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^".toList()
        ).calculateGps()

        assertEquals(9021, result)
    }

    @Test
    fun wideBug() {
        val result = Day15(
            arrayOf(
                "######".toCharArray(),
                "#....#".toCharArray(),
                "#.[].#".toCharArray(),
                "###[].#".toCharArray(),
                "#.[].#".toCharArray(),
                "#[]@.#".toCharArray(),
                "######".toCharArray(),
            ),
            "^".toList()
        ).calculateGps()

        assertEquals(202 + 303 + 402 + 501, result)
    }

    @Test
    fun nextWideBug() {
        val result = Day15(
            arrayOf(
                "#######".toCharArray(),
                "#.....#".toCharArray(),
                "#[]..##".toCharArray(),
                "#.[][]#".toCharArray(),
                "#..[]##".toCharArray(),
                "#..@.##".toCharArray(),
                "#######".toCharArray(),
            ),
            "^".toList()
        ).calculateGps()

        assertEquals(201 + 302 + 304 + 403, result)
    }


}
