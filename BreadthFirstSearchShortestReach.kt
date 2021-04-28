package hackerrank

import util.printArray
import java.util.*

fun main() {

    val edges = arrayOf(
        arrayOf(1, 2),
        arrayOf(1, 3),
        arrayOf(3, 4)
    )

    printArray<Int>(bfs(n = 5,edges = edges, s = 3))
}


fun bfs(n: Int, edges: Array<Array<Int>>, s: Int): Array<Int> {

    val graph = Graph(edges, n)

    return graph.bfsToAllNodes(s, 6)
}

class Graph(edges: Array<Array<Int>>, private val nodeCount: Int) {

    private val verticesList = Array<MutableList<Int>>(nodeCount + 1) { mutableListOf() }
    private val visited = mutableSetOf<Int>()

    init {
        for(edge in edges) {
            verticesList[edge[0]].add(edge[1])
            verticesList[edge[1]].add(edge[0])
        }
    }

    fun bfsToAllNodes(startPos: Int, weight: Int): Array<Int> {

        val distanceMap = mutableMapOf<Int, Int>()
        val queue = LinkedList<Int>()
        visited.clear()

        visited.add(startPos)
        queue.add(startPos)
        distanceMap[startPos] = 0

        while(!queue.isEmpty()) {
            val currNode = queue.pop()
            val adjacencyList = extractReachableNodes(currNode)

            for(elem in adjacencyList) {
                distanceMap[elem] = distanceMap[currNode]!! + 1
                visited.add(elem)
            }

            queue.addAll(adjacencyList)
        }

        return extractWeightDistanceList(distanceMap, startPos, weight)
    }

    private fun extractReachableNodes(pos: Int): List<Int> {

        val list = mutableListOf<Int>()

        for(elem in verticesList[pos]) {
            if(!visited.contains(elem)) list.add(elem)
        }

        return list
    }

    private fun extractWeightDistanceList(map: Map<Int, Int>, startPos: Int, weight: Int): Array<Int> {

        val array = Array<Int>(nodeCount - 1) { -1 }
        for((key, value) in map) {
            if(key == startPos) continue

            val pos = if(key < startPos) key - 1 else key - 2
            array[pos] = value * weight
        }

        return array
    }
}