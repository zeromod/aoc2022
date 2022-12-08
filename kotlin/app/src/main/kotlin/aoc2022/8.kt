package aoc2022

fun main() {
    var edgeTrees = 0
    var visibleTrees = 0
    val scenicScores = mutableListOf<Int>()

    loadFile("input8")
        .lines()
        .map { trees -> trees.toCharArray().map { it.digitToInt() }.toList() }
        .also { treeMap ->
            treeMap.forEachIndexed { rowIndex, trees ->
                if (rowIndex == 0 || rowIndex == treeMap.lastIndex) edgeTrees += trees.size
                else {
                    trees.forEachIndexed trees@{ columnIndex, tree ->
                        var scenicScore = 1
                        if (columnIndex == 0 || columnIndex == trees.lastIndex) {
                            edgeTrees++
                            return@trees
                        }
                        var visible = false
                        fun checkSide(tree: Int, trees: List<Int>) {
                            var treesInSight = 0
                            trees.forEach { t ->
                                treesInSight++
                                if (t >= tree) {
                                    scenicScore *= treesInSight
                                    return
                                }
                            }
                            visible = true
                            if (treesInSight != 0) scenicScore *= treesInSight
                            return
                        }
                        checkSide(tree, treeMap.subList(0, rowIndex).reversed().map { it[columnIndex] })//up
                        checkSide(tree, trees.subList(0, columnIndex).reversed())//left
                        checkSide(tree, trees.subList(columnIndex + 1, trees.size))//right
                        checkSide(tree, treeMap.subList(rowIndex + 1, treeMap.size).map { it[columnIndex] })//down
                        if (visible) {
                            visibleTrees++
                            scenicScores.add(scenicScore)
                        }
                    }
                }
            }
        }
    checkEquals(1807, edgeTrees + visibleTrees)
    checkEquals(480000, scenicScores.max())
    printSparkles()
}

