package io.github.sebastiankirsch.aoc2024

fun main() {
    val chars = charsArrayFromInputOf(object {}.javaClass)

    println("XMASes: ${Day4(chars).findXmases()}")
    println("X_MASes: ${Day4(chars).findXXmases()}")
}

class Day4(val chars: Array<CharArray>) {

    fun findXmases(): Int {
        var count = 0
        (0..chars.size - 1).forEach { y ->
            (0..chars[y].size - 1).forEach { x ->
                val char = chars[y][x]
                if ('X' == char) {
                    count += findXmasesAt(x, y)
                }
            }
        }
        return count
    }

    fun findXXmases(): Int {
        var count = 0
        (0..chars.size - 1).forEach { y ->
            (0..chars[y].size - 1).forEach { x ->
                val char = chars[y][x]
                if ('A' == char) {
                    count += findXXmasesAt(x, y)
                }
            }
        }
        return count
    }

    private fun findXXmasesAt(x: Int, y: Int): Int {
        return if (firstMas(x, y) && secondMas(x, y)) 1 else 0
    }

    private fun firstMas(x: Int, y: Int): Boolean {
        val upperLeft = charAt(x - 1, y - 1)
        val lowerRight = charAt(x + 1, y + 1)
        return (upperLeft == 'M' && lowerRight == 'S') || (upperLeft == 'S' && lowerRight == 'M')
    }

    private fun secondMas(x: Int, y: Int): Boolean {
        val lowerLeft = charAt(x - 1, y + 1)
        val upperRight = charAt(x + 1, y - 1)
        return (lowerLeft == 'M' && upperRight == 'S') || (lowerLeft == 'S' && upperRight == 'M')
    }

    private fun findXmasesAt(x: Int, y: Int): Int {
        var count = 0
        if (backwardsDiagonalLeftXmas(x, y)) count++
        if (backwardsDiagonalRightXmas(x, y)) count++
        if (backwardsHorizontalXmas(x, y)) count++
        if (backwardsVerticalXmas(x, y)) count++
        if (diagonalLeftXmas(x, y)) count++
        if (diagonalRightXmas(x, y)) count++
        if (horizontalXmas(x, y)) count++
        if (verticalXmas(x, y)) count++
        return count
    }

    private fun backwardsDiagonalLeftXmas(x: Int, y: Int): Boolean {
        return charAt(x - 1, y + 1) == 'M' && charAt(x - 2, y + 2) == 'A' && charAt(x - 3, y + 3) == 'S'
    }

    private fun backwardsDiagonalRightXmas(x: Int, y: Int): Boolean {
        return charAt(x - 1, y - 1) == 'M' && charAt(x - 2, y - 2) == 'A' && charAt(x - 3, y - 3) == 'S'
    }

    private fun backwardsHorizontalXmas(x: Int, y: Int): Boolean {
        return charAt(x - 1, y) == 'M' && charAt(x - 2, y) == 'A' && charAt(x - 3, y) == 'S'
    }

    private fun backwardsVerticalXmas(x: Int, y: Int): Boolean {
        return charAt(x, y - 1) == 'M' && charAt(x, y - 2) == 'A' && charAt(x, y - 3) == 'S'
    }

    private fun diagonalLeftXmas(x: Int, y: Int): Boolean {
        return charAt(x + 1, y - 1) == 'M' && charAt(x + 2, y - 2) == 'A' && charAt(x + 3, y - 3) == 'S'
    }

    private fun diagonalRightXmas(x: Int, y: Int): Boolean {
        return charAt(x + 1, y + 1) == 'M' && charAt(x + 2, y + 2) == 'A' && charAt(x + 3, y + 3) == 'S'
    }

    private fun horizontalXmas(x: Int, y: Int): Boolean {
        return charAt(x + 1, y) == 'M' && charAt(x + 2, y) == 'A' && charAt(x + 3, y) == 'S'
    }

    private fun verticalXmas(x: Int, y: Int): Boolean {
        return charAt(x, y + 1) == 'M' && charAt(x, y + 2) == 'A' && charAt(x, y + 3) == 'S'
    }

    private fun charAt(x: Int, y: Int): Char? {
        return charAt(chars, x, y)
    }

}