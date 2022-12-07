package aoc2022

fun main() {
    var sumOfFullOverlap = 0
    var sumOfPartialOverlap = 0

    loadFile("input4").split("\n").forEach { pair ->
        pair.split(",").map { elf ->
            elf.split("-").let { sectionRange ->
                IntRange(sectionRange[0].toInt(), sectionRange[1].toInt())
            }
        }.also { (elf1Section, elf2Section) ->
            if (elf1Section.minus(elf2Section).isEmpty()) sumOfFullOverlap++
            else if (elf2Section.minus(elf1Section).isEmpty()) sumOfFullOverlap++

            if (elf1Section.any { it in elf2Section }) sumOfPartialOverlap++
        }
    }
    checkEquals(550, sumOfFullOverlap)
    checkEquals(931, sumOfPartialOverlap)
}
