package src.hackerrank

fun main(args: Array<String>) {

    val ints = arrayOf(1, 6, 5, 2, 4, 3)
    larrysArray(ints)
}

fun larrysArray(A: Array<Int>): String {

    var swaps = 0
    for(i in A.indices) {

        for(j in i until A.size) {

            if(A[i] > A[j]) swaps++
        }
    }
    return if ((swaps % 2) == 0) "YES" else "NO"
}

