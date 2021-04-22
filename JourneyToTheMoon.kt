package hackerrank

fun main() {


}

fun journeyToMoon(n: Int, astronaut: Array<Array<Int>>): Long {

    val unionSets = UnionSets(n)
    for(arr in astronaut) {
        unionSets.union(arr)
    }
    return unionSets.getNumberOfValidPairs()
}

class UnionSets(n: Int) {

    private var setsCountries = ArrayList<MutableSet<Int>>()
    private var mapAstronautToCountries: Array<Int> = Array(n){0}

    init {
        for(elem in 0 until n) {
            setsCountries.add(createNewSet(elem))
            mapAstronautToCountries[elem] = elem
        }
    }

    fun getNumberOfValidPairs(): Long {

        var sum = 0L
        var result = 0L
        for(set in setsCountries) {
            result += sum * set.size
            sum += set.size
        }

        return result
    }

    fun union(arr: Array<Int>) {

        val (firstAstronaut, secAstronaut) = arr
        // If these value are on the same group, there is no need to do anything
        if(mapAstronautToCountries[firstAstronaut] != mapAstronautToCountries[secAstronaut]) {
            unionTwoSets(firstAstronaut, secAstronaut)
            clearOldSet(secAstronaut)
            updateHeadIndex(firstAstronaut)
        }
    }

    private fun clearOldSet(header: Int) {

        setsCountries[mapAstronautToCountries[header]].clear()
    }

    private fun unionTwoSets(first: Int, sec: Int) {

        val firstCountry = mapAstronautToCountries[first]
        val secCountry = mapAstronautToCountries[sec]

        setsCountries[firstCountry].addAll(setsCountries[secCountry])

    }

    private fun updateHeadIndex(head: Int) {

        for(value in setsCountries[mapAstronautToCountries[head]]) {
            mapAstronautToCountries[value] = mapAstronautToCountries[head]
        }
    }

    private fun createNewSet(elem: Int) = mutableSetOf(elem)
}