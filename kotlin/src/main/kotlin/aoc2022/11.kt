package aoc2022

import kotlin.math.floor

fun main() {
    data class Monkey(
        val items: ArrayDeque<Long>,
        val operation: (Long) -> Long,
        val divider: Long,
        val ifTrue: Int,
        val ifFalse: Int,
        var inspections: Long = 0,
    )


    listOf(20, 10000).forEach { rounds ->
        val monkeys = mutableListOf<Monkey>()
        loadFile("input11")
            .split("\n\n")
            .map { it.lines() }
            .forEach { info ->
                monkeys += Monkey(
                    items = ArrayDeque(info[1].substringAfter("items: ").split(", ").map { it.toLong() }),
                    operation = { old ->
                        info[2].substringAfter("Operation: new = old ").split(" ").let { operation ->
                            val operand = if (operation[1] == "old") old else operation[1].toLong()
                            when (operation[0][0]) {
                                '*' -> old * operand
                                '+' -> old + operand
                                else -> error("")
                            }
                        }.let { item ->
                            if (rounds == 20) floor(item / 3f).toLong() else item
                        }
                    },
                    divider = info[3].substringAfter("Test: divisible by ").toLong(),
                    ifTrue = info[4].substringAfter("If true: throw to monkey ").toInt(),
                    ifFalse = info[5].substringAfter("If false: throw to monkey ").toInt(),
                )
            }
        val commonDivisor = monkeys.fold(1L) { acc, m -> m.divider * acc }
        repeat(rounds) {
            monkeys.forEach { monkey ->
                while (monkey.items.isNotEmpty()) {
                    (monkey.operation(monkey.items.removeFirst()) % commonDivisor)
                        .also { newItem ->
                            val index = if (newItem % monkey.divider == 0L) monkey.ifTrue else monkey.ifFalse
                            monkeys[index].items.add(newItem)
                        }
                    monkey.inspections++
                }
            }
        }
        monkeys.sortByDescending { it.inspections }
        val monkeyBusiness: Long = monkeys[0].inspections * monkeys[1].inspections
        checkEquals(if (rounds == 20) 121450 else 28244037010, monkeyBusiness)
    }
    printSparkles()
}