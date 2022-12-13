package aoc2022

fun main() {
    fun String.parsePacket(): Any {
        val stack = mutableListOf(mutableListOf<Any>())
        replace("]", ",]")
            .replace("[", "[,")
            .replace(",,", ",")
            .split(",")
            .forEach {
                when (it) {
                    "[" -> {
                        val m = mutableListOf<Any>()
                        stack.last().add(m)
                        stack.add(m)
                    }

                    "]" -> stack.removeLast()
                    else -> stack.last().add(it.toInt())
                }
            }
        return stack[0][0]
    }

    fun compare(a: Any, b: Any): Int {
        if (a is Int && b is Int) return when {
            a < b -> -1
            a > b -> 1
            else -> 0
        }
        val listA = if (a is MutableList<*>) a else mutableListOf(a)
        val listB = if (b is MutableList<*>) b else mutableListOf(b)

        listA.zip(listB).forEach { (i, j) ->
            compare(i!!, j!!).let {
                if (it != 0) return it
            }
        }
        return compare(listA.size, listB.size)
    }

    val input = loadFile("input13")
        .split("\n\n")
        .asSequence()
        .map { it.lines() }
        .map { pairs -> pairs.map { line -> line.parsePacket() } }
        .toMutableList()

    input.withIndex().filter { (_, pair) -> compare(pair[0], pair[1]) <= 0 }
        .sumOf { it.index + 1 }
        .also { checkEquals(5675, it) }

    val distress = listOf(listOf(listOf(2)), listOf(listOf(6)))

    input.flatten().toMutableList()
        .also { it.addAll(distress) }
        .asSequence()
        .sortedWith(::compare)
        .withIndex()
        .filter { it.value in distress }
        .map { it.index }
        .fold(1) { acc, i -> (i + 1) * acc }
        .also { checkEquals(20383, it) }
    printSparkles()
}
