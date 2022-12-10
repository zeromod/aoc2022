package aoc2022

fun main() {
    var x = 1
    var cycle = 1
    var sum = 0
    var currentInst: List<String>? = null
    var alarm = 0 to 0
    val crt = mutableListOf<Char>()
    val input = loadFile("input10")
        .lines()
        .map { it.split(" ") }
        .toMutableList()

    while (input.isNotEmpty()) {
        crt.add(if ((cycle - 1) % 40 in x - 1..x + 1) '#' else '.')
        if (cycle % 40 == 20) sum += cycle * x

        if (currentInst == null) {
            currentInst = input.first()
            when (currentInst[0]) {
                "addx" -> alarm = cycle + 1 to currentInst[1].toInt()
                "noop" -> alarm = cycle to 0
            }
        }
        if (cycle == alarm.first) {
            x += alarm.second
            input.removeFirst()
            currentInst = null
        }
        cycle++
    }

    val crtScreen = crt.chunked(40).joinToString("\n") { it.joinToString("") }
    checkEquals(15260, sum)
    checkEquals(
        """
        ###...##..#..#.####..##..#....#..#..##..
        #..#.#..#.#..#.#....#..#.#....#..#.#..#.
        #..#.#....####.###..#....#....#..#.#....
        ###..#.##.#..#.#....#.##.#....#..#.#.##.
        #....#..#.#..#.#....#..#.#....#..#.#..#.
        #.....###.#..#.#.....###.####..##...###.
    """.trimIndent(), crtScreen
    )
    printSparkles()
}