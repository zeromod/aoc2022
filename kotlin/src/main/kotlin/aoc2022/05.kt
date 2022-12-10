package aoc2022

fun main() {
    loadFile("input5").also {
        listOf(9000, 9001).forEach { craneModel ->
            val crateStack = mutableMapOf<Int, MutableList<Char>>()
            it.split("\n\n").map { it.split("\n") }.also { (drawing, instruction) ->
                drawing.forEach { line ->
                    line.forEachIndexed { index, c ->
                        if (c.isDigit()) {
                            val stack = crateStack[index]!!
                            crateStack.remove(index)
                            crateStack[c.digitToInt()] = stack
                        } else if (c.isLetter()) crateStack.getOrPut(index) { mutableListOf() }.add(c)
                    }
                }
                instruction.forEach { line ->
                    val count = line.substringAfter("move ").takeWhile { it != ' ' }.toInt()
                    val from = line.substringAfter(" from ").takeWhile { it != ' ' }.toInt()
                    val to = line.substringAfter(" to ").takeWhile { it != ' ' }.toInt()
                    val transfer = crateStack[from]!!.take(count).let { if (craneModel == 9000) it.reversed() else it }
                    crateStack[to]!!.addAll(0, transfer)
                    repeat(count) { crateStack[from]!!.removeFirst() }
                }
            }
            val topCrates = crateStack.values.map { it.first() }.joinToString("")
            if (craneModel == 9000) checkEquals("TLFGBZHCN", topCrates)
            if (craneModel == 9001) checkEquals("QRQFHFWCL", topCrates)
        }
    }
}
