package hackerrank

fun main() {

}

// Complete the minimumLoss function below.
fun minimumLoss(price: Array<Long>): Long {

    val list = mutableListOf<ValueIndex>()

    for(i in price.indices) {

        list.add(ValueIndex(i, price[i]))
    }

    list.sortBy { it.value }

    var min = Long.MAX_VALUE

    for(i in 1 until list.size) {

        if(list[i].value - list[i - 1].value < min) {

            if(list[i].index < list[i - 1].index) min = (list[i].value - list[i - 1].value)
        }
    }

    return min
}

data class ValueIndex(val index: Int, val value: Long)