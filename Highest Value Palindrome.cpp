#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
#include <string>
#include <ctype.h>
#include <map> 
#include "test.h"
#include <bitset>
#include <set>

using namespace std;

struct Pair {
    string palidrome;
    int remainigChanges;

public:
    Pair(string pal, int remainChanges) {
        palidrome = pal;
        remainigChanges = remainChanges;
    }
};

int getMatchingIndex(int strLength, int index) {
    return strLength - index - 1;
}

Pair createPalindromeString(string str, int k, vector<bool> &hasPositionChanged) {

    int remaningChanges = k;
    string temp = str;

    for (int i = 0; i < str.size() / 2; ++i) {
        if (str[i] != str[getMatchingIndex(str.length(), i)]) {
            char higherChar = max(str[i], str[getMatchingIndex(str.length(), i)]);
            remaningChanges--;
            hasPositionChanged[i] = true;
            temp[i] = higherChar;
            temp[getMatchingIndex(str.length(), i)] = higherChar;
        }
    }

    return Pair(temp, remaningChanges);
}

string maximizePalindrome(string palindrome, int remainingChanges, vector<bool> &hasPositionChanged) {

    int index = 0;
    string maximizedPal = palindrome;
    while (index < palindrome.size() / 2 && remainingChanges > 0) {

        if (palindrome[index] != '9') {
            if (hasPositionChanged[index]) {
                remainingChanges++;
            }
            if (remainingChanges >= 2) {
                maximizedPal[index] = '9';
                maximizedPal[getMatchingIndex(palindrome.size(), index)] = '9';
                remainingChanges -= 2;
            }
        }
        index++;
    }

    if (palindrome.size() % 2 != 0 && remainingChanges > 0) maximizedPal[palindrome.size() / 2] = '9';

    return maximizedPal;
}

string highestValuePalindrome(string s, int n, int k) {

    int remainingChanges = k;
    vector<bool> hasPositionChanged(s.size(), false);
    
    Pair pairPalChangesRemaining = createPalindromeString(s, k, hasPositionChanged);

    if (pairPalChangesRemaining.remainigChanges < 0) return "-1";

    return maximizePalindrome(pairPalChangesRemaining.palidrome, pairPalChangesRemaining.remainigChanges, hasPositionChanged);
}

int main(int argc, const char* argv[]) {
    
    string str = "932239";
    int k = 2;
    int n = 2;
    cout << highestValuePalindrome(str, n, k);
}
