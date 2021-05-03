package hackerrank

import java.lang.Integer.max
import java.util.*

fun main() {

    val arr = arrayOf(1, 2, 3, 4, 5, 1, 2, 3, 32132)
    print(longestIncreasingSubsequence(arr))
}

fun longestIncreasingSubsequence(arr: Array<Int>): Int {
    // d Holds what element each sub sequence of length i (1 <= i <= arr.size) ends with.
    // If sub sequence of length i has multiple ending then the smallest one will be used.
    val d = Array<Int>(arr.size + 1) { Int.MAX_VALUE }

    d[0] = Int.MIN_VALUE

    for(i in arr.indices) {
        val position = d.upperBounds(arr[i])
        if(d[position - 1] < arr[i] && arr[i] < d[position]) {
            d[position] = arr[i]
        }
        util.printArray(d)
    }

    var answer = 1
    for(i in 1..arr.size) {

        if(d[i] == Int.MAX_VALUE) break

        answer = i
    }

    return answer
}

fun Array<Int>.upperBounds(elem: Int): Int {

    var start = 0
    var end = size - 1
    var mid = (start + end) / 2

    while(!(elem <= get(mid + 1) && get(mid - 1) <= elem)) {

        if(elem < get(mid) && elem < get(mid + 1)) end = mid

        else if(get(mid) < elem && get(mid + 1) < elem) start = mid

        mid = (start + end) / 2
    }

    if(get(mid) <= elem) return mid + 1

    return mid
}
