package aoc2022

fun main() {
    val sandSource = 500 to 0
    var sandUnit = 500 to 0
    val input = loadFile("input14")
        .lines()
        .map { line ->
            line.split(" -> ")
                .map {
                    it.split(",")
                        .let { p ->
                            p[0].toInt() to p[1].toInt()
                        }
                }.fold(mutableListOf<Pair<Int, Int>>()) { acc, (x, y) ->
                    if (acc.isEmpty()) acc.add(x to y)
                    else {
                        val (px, py) = acc.last()
                        for (i in if (px < x) px..x else px downTo x) {
                            for (j in if (py < y) py..y else py downTo y) {
                                acc.add(i to j)
                            }
                        }
                    }
                    acc
                }
        }
        .flatten()
        .map { '#' to it }
        .toMutableList()
        .also { it.add('+' to sandSource) }
    var xAxis = input.minOf { it.second.first }..input.maxOf { it.second.first }
    var yAxis = input.minOf { it.second.second }..input.maxOf { it.second.second }

    var xa = 0
    repeat(yAxis.last + 2) {
        xa += 2
    }

    for (i in xAxis.first - xa..xAxis.last + xa) {
        input.add('#' to (i to yAxis.last + 2))
    }

    xAxis = input.minOf { it.second.first }..input.maxOf { it.second.first }
    yAxis = input.minOf { it.second.second }..input.maxOf { it.second.second }

    println("($xAxis, $yAxis)")

    while (!input.contains('o' to (501 to 1))) {
        println(sandUnit)
        val down = sandUnit.first to sandUnit.second + 1
        val leftDown = sandUnit.first - 1 to sandUnit.second + 1
        val rightDown = sandUnit.first + 1 to sandUnit.second + 1
        val scans = input.map { it.second }
        sandUnit = if (scans.contains(down)) {
            if (scans.contains(leftDown)) {
                if (scans.contains(rightDown)) {
                    input.add('o' to sandUnit)
                    sandSource
                } else rightDown
            } else leftDown
        } else down
    }

    for (y in yAxis) {
        for (x in xAxis) {
            input.find { it.second == x to y }?.first?.let { print(it) } ?: print(".")
        }
        println("")
    }
    println(input.filter { it.first == 'o' }.size + 1)
}
