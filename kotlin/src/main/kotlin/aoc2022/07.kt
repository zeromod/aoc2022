package aoc2022

import java.util.Stack

fun main() {
    val pwd = Stack<String>()
    val dirSizes = mutableMapOf<String, Long>()

    loadFile("input7")
        .lines()
        .forEach { line ->
            val words = line.split(" ")
            when {
                words.containsAll(listOf("$", "cd", "/")) -> pwd.push("")
                words.containsAll(listOf("$", "cd", "..")) -> pwd.pop()
                words.containsAll(listOf("$", "cd")) -> pwd.push(words.last())
                words.contains("ls") -> Unit
                words.contains("dir") -> Unit
                else -> pwd.runningReduce { acc, s -> "$acc/$s" }.forEach { key ->
                    dirSizes[key] = dirSizes.getOrDefault(key, 0) + words.first().toLong()
                }
            }
        }
    dirSizes.values.let { sizes ->
        checkEquals(1491614, sizes.filter { it <= 100000L }.sum())

        val requiredSpace = 30000000 - (70000000 - dirSizes.values.first())
        checkEquals(6400111, sizes.filter { it >= requiredSpace }.min())
    }
}
