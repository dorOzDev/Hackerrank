package hackerrank

import java.lang.Integer.min
import kotlin.math.max

fun main() {

    val input = arrayOf(
        arrayOf(1, 6),
        arrayOf(2, 7),
        arrayOf(3, 8),
        arrayOf(4, 9),
        arrayOf(2, 6)
    )

    util.printArray(componentsInGraph(input))
}


fun componentsInGraph(gb: Array<Array<Int>>): Array<Int> {
    // Write your code here
    val disjointSet = DisjointSet(gb.size)

    for(elem in gb) {

        disjointSet.union(elem[0], elem[1])
    }

    var minValue = Integer.MAX_VALUE
    var maxValue = Integer.MIN_VALUE

    for(set in disjointSet.sets) {
        maxValue = max(maxValue, set.size)
        if(set.size > 1) minValue = min(minValue, set.size)
    }

    return arrayOf(minValue, maxValue)
}

class DisjointSet(val n: Int) {

    val sets = Array<MutableSet<Int>>(2*n + 1) { mutableSetOf() }
    private val headRep = Array<Int>(2*n + 1) { -1 }
    init {
        makeSets()
    }

    private fun makeSets() {

        for(i in 0..(2*n)) {
            sets[i].add(i)
            headRep[i] = i
        }
    }

    private fun find(elem: Int): Int = headRep[elem]

    fun union(first: Int, sec: Int) {

        val firstHead = find(first)
        val secHead = find(sec)

        if(firstHead != secHead) {
            addLowerCountSetToHigher(firstHead, secHead)
        }
    }

    private fun addLowerCountSetToHigher(firstHead: Int, secHead: Int) {

        val higherCountSet = if(sets[firstHead].size > sets[secHead].size) firstHead else secHead
        val lowerCountSet = if(higherCountSet == firstHead) secHead else firstHead

        sets[higherCountSet].addAll(sets[lowerCountSet])
        markNewHead(lowerCountSet, higherCountSet)

        sets[lowerCountSet].clear()
    }

    private fun markNewHead(oldHead: Int, newHead: Int) {

        for(elem in sets[oldHead]) {
            headRep[elem] = newHead
        }
    }
}


