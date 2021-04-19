package hackerrank

fun main(args: Array<String>) {

    val str = "TATATAGCTTGTCTCCCTAATGTTAGTTCATGCTCGTAAGAGAACTTAGCCTACTAGGACGAGAGAACCGCACGGCGTCGTGAGGTATTTTTCGTAGGACACGCCAGATAGACGGTGGCAATGCCCGTTCAATATGACGCGATGTACGGCTAATGGGAACACTGCCCGACGCGTCTTTAGGACTGTGAGTTGCGGGTTACAGCTATGGTCTTATTGGTATCCGGCCCCTTTCGAGTCGCGATGCGCCTGCCACCACGATATTCGCCCGAAACGCGATTTGTGGGCGAGGTAGTCGTGTTCAACCCTGTAAATTTCCCTAGGTATAATCGTTCTAAGGTTCGCACATACACATCCACACCTACCTTTACACAGTTCGAGGTTCTATACGTCCTCTGAGTGCGTGTTAACACGCCCGTAAATGGGCATTTGGAGTCAGACCAGTACTTTGCGATAAACTTTACTTCCGCGAGACCTGTCCCTGGAACCCTGTTGTAAGGGTTAGGGTTTAATAGCTCCATGTCGTGTGCCTATAAGAAAAGGACGAATGGTGACAGTCCGGCTTAGCCAGGACAATGCGTGGCTGACGACGTCCAGGGTAAATTGAGTTGAATTCGCCTAATTTTAGGGTGTCTTGGTTCAATGAGGTGTCGACTTAACAAAAGGCGACATCAGTTGTCATCTTGCCTTGATAAAGTAAAACACGTGAATAGCCTATCCGGTCTGACCCCCGGGCCATGTGCTTCACCCAGGGAGCATCGCCGCTCTAGAGACGGTGTTCGTAGTCTCGATAACATGTGGGGTAATATAGAATATCCAAGACCGGTAGGAGGGGCGGTTCCGCGTCATAAGAAGTCCCAACGTGGCCTGCCACGTTCAAACAGGATACGCTATAACAGCTTCGTGGGTAATTGATGGATACGCCCGCAGGCTACCCATGCTCTTGCGATTTTGCAACCCTCGGAACCGTCACTCGTACACCCAGACATCATCTCATACAATTGCCTCACCTTCATGCCGGTACATAGGTGCCATCTCCGCTTAAGAATCCTCGCAGCAATTAATGTGACAGCACGCTAGTCCACTAGCGTATGATTACGCCACCGGGCCACCATGGACAAAAACGTTGAATTCCGACTAATAGACGAGTGTCCGATCGGGTCAACCGATCTCGGATGTTGCGTACCAGGACTACTGGGCTCGGGCCGAATCAGACACACGTATGCAACAGATACCGATAGGCGTCTTCCTAAGTAACAGCCGTAATCAATGGTGCCACAGATCTACTAATTACGGTGAAGATCATGGCCCACGACGCTGTACGGGTTTATAGCTGCCACAAACTTTAGGAAGTTTCAGCAATCGACGCGTAGTATGTGTGCTCAGACGGGTCGAGCATGCACTTGTGTATTAAGTTACTTGGCTGAACAACCTGTTGATAGATCTTGAGAGGACCGAGAAATTGCCCTCCGGTTATGAAACAGGTCCTGCGTACCAATCCTT"
    print(steadyGene(str))
}

// Complete the steadyGene function below.
fun steadyGene(gene: String): Int {

    val frequenciesMap = getCountFrequenciesMap(gene)
    val unsteadyGeneMap = frequenciesMap
        .filter { (_, value) -> value > gene.length / 4 }
        .mapValues { (_, value) -> value - gene.length / 4 }

    return findShortestUnsteadyGenePart(unsteadyGeneMap, gene)
}

fun findShortestUnsteadyGenePart(unsteadyGeneMap: Map<Char, Int>, gene: String): Int {

    val minWindowLength = unsteadyGeneMap.values.sum()
    var (leftWindow, rightWindow) = arrayListOf(0, minWindowLength)
    val map = getCountFrequenciesMap(gene.substring(leftWindow, rightWindow))
    var min = Int.MAX_VALUE

    while(rightWindow < gene.length && rightWindow >= leftWindow) {

        if(windowsContainsOverFrequenciesChars(unsteadyGeneMap, map)) {

            min = min.coerceAtMost(rightWindow - leftWindow)
            val oldVal = map[gene[leftWindow]] ?: 1
            map[gene[leftWindow]] = oldVal - 1
            ++leftWindow
        }
        else {
            val oldVal = map[gene[rightWindow]] ?: 0
            map[gene[rightWindow]] = oldVal + 1
            ++rightWindow
        }
    }

    return min
}

fun windowsContainsOverFrequenciesChars(unsteadyGeneMap: Map<Char, Int>, partialGeneMap: Map<Char, Int>): Boolean {

    for((key, value) in unsteadyGeneMap) {
        val otherValue = partialGeneMap[key] ?: 0
        if(otherValue < value) return false
    }

    return true
}

fun getCountFrequenciesMap(gene: String): MutableMap<Char, Int> {
    
    val map = mutableMapOf<Char, Int>()

    for(ch in gene.toCharArray()) {

        val currCount = map[ch] ?: 0
        map[ch] = currCount + 1
    }
    
    return map
}


