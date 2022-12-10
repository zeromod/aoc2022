package aoc2022

fun main() {
    val day = 10
    var x = 1
    var cycle = 1
    var sum = 0
    var currentInst: List<String>? = null
    var alarm = 0 to 0
    val crt = mutableListOf<Char>()
    val input = loadFile("input$day")
        .lines()
        .map { it.split(" ") }
        .toMutableList()

    while (input.isNotEmpty()) {
        when (cycle) {
            in 1..40 -> cycle - 1
            in 41..80 -> cycle - 41
            in 81..120 -> cycle - 81
            in 121..160 -> cycle - 121
            in 161..200 -> cycle - 161
            in 201..240 -> cycle - 201
            else -> error("Invalid cycle")
        }.also { p ->
            crt.add(if (p in x - 1..x + 1) '#' else '.')
        }
        when (cycle) {
            20 -> sum += (20 * x)
            60 -> sum += (60 * x)
            100 -> sum += (100 * x)
            140 -> sum += (140 * x)
            180 -> sum += (180 * x)
            220 -> sum += (220 * x)
        }
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