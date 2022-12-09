package aoc2022

fun main() {
    var score = 0
    var score2 = 0
    loadFile("input2").split("\n").forEach {
        it.split(" ").also { (opponent, response) ->
            when (opponent) {
                "A" -> when (response) {
                    "X" -> {
                        score += 4
                        score2 += 0 + 3
                    }

                    "Y" -> {
                        score += 8
                        score2 += 3 + 1
                    }

                    "Z" -> {
                        score += 3
                        score2 += 6 + 2
                    }

                }

                "B" -> when (response) {
                    "X" -> {
                        score += 1
                        score2 += 0 + 1
                    }

                    "Y" -> {
                        score += 5
                        score2 += 3 + 2
                    }

                    "Z" -> {
                        score += 9
                        score2 += 6 + 3
                    }

                }

                "C" ->
                    when (response) {
                        "X" -> {
                            score += 7
                            score2 += 0 + 2
                        }

                        "Y" -> {
                            score += 2
                            score2 += 3 + 3
                        }

                        "Z" -> {
                            score += 6
                            score2 += 6 + 1
                        }

                    }
            }
        }
    }
    checkEquals(14531, score)
    checkEquals(11258, score)
}
