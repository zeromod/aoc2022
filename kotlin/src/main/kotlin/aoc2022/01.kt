package aoc2022

fun main() {
    loadFile("input1").split("\n").fold(mutableListOf(mutableListOf<Int>())) { acc, calories ->
        acc.apply {
            if (calories.isBlank()) add(mutableListOf()) else last().add(calories.toInt())
        }
    }.map { caloriesList -> caloriesList.sum() }.sortedDescending().also { caloriesOfElf ->
        val part1 = caloriesOfElf[0]
        checkEquals(67658, part1)

        val part2 = caloriesOfElf.take(3).sum()
        checkEquals(200158, part2)
    }
}
