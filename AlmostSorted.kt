package src.hackerrank

fun main(args: Array<String>) {

    val arr = arrayOf(1, 2, 3, 4, 12, 7, 8, 6)
    almostSorted(arr)
}

fun almostSorted(arr: Array<Int>): Unit {

    val modifiedArray = getArrayWithSentinels(arr)

    val segmentsList = createSegmentsList(modifiedArray)


    when(segmentsList.size) {

        0 -> print("yes")

        1 -> {
            if(validateSegmentIsOrderable(modifiedArray, segmentsList[0])) {
                println("yes")
                val secPrint = if(segmentsList[0].second - segmentsList[0].first ==  1) "swap" else "reverse"

                print(secPrint + " " + (segmentsList[0].first) + " " + (segmentsList[0].second))
            }
            else print("no")
        }

        2 -> {
            if(validateElementsSwappable(modifiedArray, segmentsList)) {
                println("yes")
                print("swap" + " " + (segmentsList[0].first) + " " + (segmentsList[1].second))
            }
            else print("no")
        }

        else -> print("no")
    }

}

fun getArrayWithSentinels(arr: Array<Int>): Array<Int> {

    var modifiedArr = arrayOf(-1)
    modifiedArr = modifiedArr.plus(arr)

    modifiedArr = modifiedArr.plus(1000001)

    return modifiedArr
}

fun validateElementsSwappable(arr: Array<Int>, segmentsList: MutableList<Pair<Int, Int>>): Boolean {

    val firstSegment = segmentsList[0]
    val secSegment = segmentsList[1]

    val validateLeftSegment = arr[firstSegment.first] > arr[secSegment.second - 1] && arr[firstSegment.first] < arr[secSegment.second + 1]

    val validateRightSegment = arr[secSegment.second] > arr[firstSegment.first - 1] && arr[secSegment.second] < arr[firstSegment.first + 1]

    return validateLeftSegment && validateRightSegment
}

fun validateSegmentIsOrderable(arr: Array<Int>, pair: Pair<Int, Int>): Boolean {

    val validateLeftSegment = arr[pair.second] > arr[pair.first - 1]

    val validateRightSegment = arr[pair.first] < arr[pair.second + 1]

    return validateLeftSegment && validateRightSegment
}

fun createSegmentsList(arr: Array<Int>): MutableList<Pair<Int, Int>> {

    val segmentsList = mutableListOf<Pair<Int, Int>>()
    var isKnownSegment = false

    var beginSegment = 0

    var engSegment = 0

    for(i in 1 until arr.size) {

        if(arr[i] < arr[i - 1]) {
            // If a new segment is discovered
            if(!isKnownSegment) {
                beginSegment = i - 1
            }

            engSegment = i

            isKnownSegment = true
        }

        else if (isKnownSegment) {

            isKnownSegment = false

            segmentsList.add(Pair(beginSegment, engSegment))
        }
    }

    return segmentsList
}
