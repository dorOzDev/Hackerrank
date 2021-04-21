package hackerrank

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*


fun main() {

/*    val number = ((10 + 5).toDouble()) / 2

    print(number)*/

    val arr = runningMedian(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

    printArray(arr)
}

fun printArray(a: Array<Double>) {

    for(elem in a) {

        print("$elem ")
    }
}


fun runningMedian(a: Array<Int>): Array<Double> {

    val medianArray = Array(a.size) { 0.0 }
    val medianDataStructure = MedianDataStructure()

    for((index, elem) in a.withIndex()) {

        medianDataStructure.insert(elem)
        medianArray[index] = medianDataStructure.getMedian()
    }

    return medianArray
}

class MedianDataStructure {

    private val rightHeap = PriorityQueue<Int>()
    private val leftHeap = PriorityQueue<Int>(Collections.reverseOrder())
    private var size = 0

    fun insert(element: Int) {

        if(size == 0 || element < leftHeap.peek()) leftHeap.add(element)

        else rightHeap.add(element)

        balanceHeaps()

        size++
    }

    fun getMedian(): Double {

        var median = leftHeap.peek().toDouble()

        if(size % 2 == 0) median = (median + rightHeap.peek()) / 2

        return median
    }

    private fun balanceHeaps() {

        if(leftHeap.size - rightHeap.size > 1) rightHeap.add(leftHeap.remove())

        else if(rightHeap.size > leftHeap.size) leftHeap.add(rightHeap.remove())
    }
}
