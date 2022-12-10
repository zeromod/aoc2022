package aoc2022

import kotlin.math.abs
import kotlin.math.sign

fun main() {
    val input = loadFile("input9")
        .lines()
        .map { it.split(" ").let { (d, l) -> d to l.toInt() } }

    listOf(2, 10).forEach { knots ->
        val x = IntArray(knots)
        val y = IntArray(knots)
        val tailMovements = mutableSetOf(0 to 0)
        input.forEach { (direction, length) ->
            repeat(length) {
                when (direction[0]) {
                    'U' -> y[0]++
                    'D' -> y[0]--
                    'R' -> x[0]++
                    'L' -> x[0]--
                }
                for (k in 1 until knots) {
                    val dx = x[k - 1] - x[k]
                    val dy = y[k - 1] - y[k]
                    if (abs(dx) == 2 || abs(dy) == 2) {
                        x[k] += dx.sign
                        y[k] += dy.sign
                    }
                }
                tailMovements += x[knots - 1] to y[knots - 1]
            }
        }
        checkEquals(if (knots == 2) 6498 else 2531, tailMovements.size)
    }
    printSparkles()
}