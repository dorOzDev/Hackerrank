package hackerrank

fun hackerlandRadioTransmitters(arr: Array<Int>, k: Int): Int {
    // Write your code here
    var transmitterCount = 0
    arr.sort()
    var anchoredPosition = 0
    var runnerIndex = 1

    while(runnerIndex < arr.size) {

        if(arr[runnerIndex] - arr[anchoredPosition] <= k ) {
            runnerIndex++
        }
        else {

            transmitterCount++
            anchoredPosition= runnerIndex - 1
            while(runnerIndex < arr.size && arr[runnerIndex] - arr[anchoredPosition] <= k) {
                runnerIndex++
            }
            anchoredPosition = runnerIndex
            runnerIndex++
        }
    }
    if(anchoredPosition < arr.size) transmitterCount++

    return transmitterCount
}

fun main(args: Array<String>) {

    val arr = arrayOf(7, 2, 4, 6, 5, 9, 12, 11)
    val k = 2
    println(hackerlandRadioTransmitters(arr, k))
}
