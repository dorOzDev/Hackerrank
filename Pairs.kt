package src.hackerrank



fun pairs(k: Int, arr: Array<Int>): Int {

    val seenMap = hashSetOf<Int>()
    var countPairs = 0
    for(num in arr) {

        val firstValue = num + k
        val secValue = num - k

        if(seenMap.contains(firstValue)) {
            ++countPairs
        }
        if(seenMap.contains(secValue)) {
            ++countPairs
        }

        seenMap.add(num)
    }
    return countPairs
}
