package aoc2022

fun main() {
    val rackSacks = loadFile("input3").split("\n").map { it.toCharArray().toList() }

    fun intersectAndSum(window: List<List<Char>>): Int {
        return window.reduce { prevCompartment, currentCompartment ->
            currentCompartment.intersect(prevCompartment.toSet()).toList()
        }
            .sumOf { asciiToAocPriority(it.code) }
    }

    rackSacks.sumOf { rackSack ->
        val compartments = rackSack.chunked((rackSack.size) / 2)
        intersectAndSum(compartments)
    }.also { sumOfItemPriority ->
        checkEquals(8394, sumOfItemPriority)
    }

    rackSacks.chunked(3).sumOf { group ->
        intersectAndSum(group)
    }.also { sumOfBadgePriority ->
        checkEquals(2413, sumOfBadgePriority)
    }
}

fun asciiToAocPriority(value: Int) = when (value) {
    in 65..90 -> value - 38
    in 97..122 -> value - 96
    else -> error("Unsupported char")
}
