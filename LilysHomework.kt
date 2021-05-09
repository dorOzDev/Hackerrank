package hackerrank

import kotlin.math.min

fun main() {

    val arr = arrayOf(3, 4, 2, 5, 1)
    print(lilysHomework(arr))
}

fun lilysHomework(arr: Array<Int>): Int {
    // Write your code here
    if(arr.size <= 2) return 0

    val mapValueToIndex = getValueToIndexMap(arr)
    val sortedArr = arr.sortedArray()


    return min(minSwapsAscending(arr.clone(), sortedArr, HashMap(mapValueToIndex)), minSwapsDescending(arr, sortedArr, mapValueToIndex))
}

fun getValueToIndexMap(arr: Array<Int>): MutableMap<Int, Int> {

    val map = mutableMapOf<Int, Int>()
    for(i in arr.indices) {
        map[arr[i]] = i
    }

    return map
}

fun minSwapsAscending(arr: Array<Int>, sortedArray: Array<Int>, mapValueToIndex: MutableMap<Int, Int>): Int {

    var countDifferences = 0

    for (i in arr.indices) {

        if (arr[i] != sortedArray[i]) {
            val swapIndex = mapValueToIndex[sortedArray[i]] ?: 0
            val temp = arr[i]

            arr[i] = arr[swapIndex]
            arr[swapIndex] = temp

            mapValueToIndex[sortedArray[i]] = i
            mapValueToIndex[temp] = swapIndex

            ++countDifferences
        }

    }

    return countDifferences
}

fun minSwapsDescending(arr: Array<Int>, sortedArray: Array<Int>, mapValueToIndex: MutableMap<Int, Int>): Int {

    var countDifferences = 0

    for(i in arr.indices) {
        //println("arr[i] = ${arr[i]} , sortedarr[arr.size - i - 1] = ${sortedArray[arr.size - i - 1]}")
        if(arr[i] != sortedArray[arr.size - i - 1]) {

            val swapIndex = mapValueToIndex[sortedArray[arr.size - i - 1]] ?: 0
            val temp = arr[i]
            arr[i] = arr[swapIndex]
            arr[swapIndex] = temp

            mapValueToIndex[sortedArray[arr.size - i - 1]] = i
            mapValueToIndex[temp] = swapIndex
            ++countDifferences
        }
        //util.printArray(arr)
    }
    return countDifferences
}
