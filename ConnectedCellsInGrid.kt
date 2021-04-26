package hackerrank

import java.lang.Integer.max
import java.util.*

fun main(args: Array<String>) {

    val n = readLine()!!.trim().toInt()

    val m = readLine()!!.trim().toInt()

    val matrix = Array(n + 2) { Array(m + 2) { 0 } }

    for (i in 1 until n + 1) {
        val inputLine = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
        for(j in inputLine.indices){
            matrix[i][j + 1] = inputLine[j]
        }
    }

    println(connectedCell(matrix))
}

fun connectedCell(matrix: Array<Array<Int>>): Int {

    var tempMax = 0
    val dataStructure = MyDataStructure(matrix)

    for(i in 1 until matrix.size - 1) {
        for(j in 1 until matrix[i].size - 1) {
            if(matrix[i][j] == 1) tempMax = max(tempMax, dataStructure.bfs(i, j))
        }
    }

    return tempMax
}

class MyDataStructure(val grid: Array<Array<Int>>) {

    val visited = mutableSetOf<Pair<Int, Int>>()

    fun bfs(x: Int, y: Int): Int {

        var currPair = Pair(x, y)
        if (visited.contains(currPair)) return 0

        var count = 0
        val queue = LinkedList<Pair<Int, Int>>()
        queue.add(currPair)
        visited.add(currPair)

        while (!queue.isEmpty()) {
            currPair = queue.pop()
            val adjList = getAllAdjacency(currPair.first, currPair.second)
            queue.addAll(adjList)
            visited.addAll(adjList)
            ++count
        }

        return count
    }

    private fun getAllAdjacency(x: Int, y: Int): Collection<Pair<Int, Int>> {

        val adjacencyList = mutableListOf<Pair<Int, Int>>()
        val additiveList = listOf(-1, 0, 1)
        for(i in 0 until 3) {
            for(j in 0 until 3) {
                if(additiveList[i] == 0 && additiveList[j] == 0) continue

                val possibleAdjacency = Pair(x + additiveList[i], y + additiveList[j])

                if(isValidAdjacency(possibleAdjacency)) {
                    adjacencyList.add(possibleAdjacency)
                }
            }
        }
        return adjacencyList
    }

    private fun isValidAdjacency(pair: Pair<Int, Int>) =
        grid[pair.first][pair.second] == 1 && !visited.contains(pair)
}