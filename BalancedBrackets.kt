package src.hackerrank

import java.util.*

fun main() {

    val test = "{{(([]))}}}"
    print(isBalanced(test))
}

fun isBalanced(s: String): String {

    val mapCloseToOpenBrackets = mapOf(
        ']' to '[',
        '}' to '{',
        ')' to '('
    )
    val stack: Stack<Char> = Stack()

    s.forEach { ch ->
        // Check if the current char is a close brackets
        if(mapCloseToOpenBrackets.containsKey(ch)){
            if(stack.isEmpty() || stack.pop() != mapCloseToOpenBrackets[ch])
                return "NO"
        }
        else stack.push(ch)
    }

    return if(stack.isEmpty()) "YES" else "NO"
}