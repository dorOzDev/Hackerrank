package hackerrank

const val ADD = "add"
const val FIND = "find"
// 27 So i have one index to indicate this is an end word
const val ALPHA_BET_SIZE = 27
const val END_WORD_INDEX = 26
const val END_WORD_MARK = '$'

data class TrieNode(val c: Char, var count: Int = 1) {

    val adjacencyArr = Array<TrieNode?>(ALPHA_BET_SIZE) { null }

    fun markEndWord() {
        if(adjacencyArr[END_WORD_INDEX] == null) adjacencyArr[END_WORD_INDEX] = TrieNode(END_WORD_MARK)
    }
}

class Trie {

    private val root = TrieNode(' ', 0)

    fun insert(str: String) {

        var itr: TrieNode = root
        var nextNode: TrieNode?

        for(i in str.indices) {
            nextNode = itr.adjacencyArr[str[i] - 'a']
            if(nextNode != null) {
                nextNode.count++
            }
            else {
                nextNode = TrieNode(str[i])
                itr.adjacencyArr[str[i] - 'a'] = nextNode
            }
            itr = nextNode
        }
        itr.markEndWord()
    }

    fun findPartialCount(partial: String): Int {
        var itr = root
        for(c in partial) {
            val next = itr.adjacencyArr[c - 'a'] ?: return 0
            itr = next
        }

        return itr.count
    }
}

class Contacts {
    private val trie = Trie()

    fun queryContacts(queries: Array<Array<String>>): Array<Int> {
        val listAnswers = mutableListOf<Int>()

        for (query in queries) {
            val (queryType, name) = query
            if (queryType == ADD) {
                trie.insert(name)
            } else {
                listAnswers.add(trie.findPartialCount(name))
            }
        }

        return listAnswers.toTypedArray()
    }
}

fun main() {

    val contacts = Contacts()
    val queries: Array<Array<String>> = arrayOf(
        arrayOf("add", "ed"),
        arrayOf("add", "eddie"),
        arrayOf("add", "edward"),
        arrayOf("find", "ed"),
        arrayOf("add", "edwina"),
        arrayOf("find", "hak"),
        arrayOf("find", "hac")
    )

    val arr = contacts.queryContacts(queries)
}