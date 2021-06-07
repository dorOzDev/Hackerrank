package hackerrank;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class BiggerIsGreater {

    public static void main(String[] args) {

        String str = biggerIsGreater("dkhc");

        System.out.println(str);
    }

    // Complete the biggerIsGreater function below.
    // The idea is to find a suffix which is a decreasing order(for example 'dca') and the left char to the begin of the same suffix
    // is a lower char to the begin of the suffix. For example the suffix from before will be like 'adca'.
    // Once the same sequence is found the idea is to replace the first char with the next greater char from the suffix(i.e the first a with c - cdaa).
    // And then sort the same suffix excluding the replaced bigger char, i.e sort daa. The result will be caad.
    static String biggerIsGreater(String w) {

        int beginSuffix = getStartSuffix(w);

        if(beginSuffix < 0) return "no answer";

        int replaceIndex = getReplaceableCharIndex(w, beginSuffix);

        String replacedString = replaceByIndex(w, beginSuffix, replaceIndex);

        char[] tempSuffix = replacedString.substring(beginSuffix + 1).toCharArray();
        Arrays.sort(tempSuffix);

        int endPrefix = Math.max(beginSuffix + 1, 1);
        char[] tempPrefix = replacedString.substring(0, endPrefix).toCharArray();

        return new String(tempPrefix).concat(new String(tempSuffix));
    }

    private static int getReplaceableCharIndex(String w, int beginSuffix) {

        char firstChar = w.charAt(beginSuffix);
        int replaceAbleIndex = beginSuffix + 1;

        for(int i = beginSuffix + 1; i < w.length(); ++i) {
            if(w.charAt(i) > firstChar && w.charAt(i) < w.charAt(replaceAbleIndex)) replaceAbleIndex = i;
        }

        return replaceAbleIndex;
    }

    static String replaceByIndex(String str, int firstIndex, int secIndex) {

        char[] charSequence = str.toCharArray();

        char temp = charSequence[firstIndex];

        charSequence[firstIndex] = charSequence[secIndex];
        charSequence[secIndex] = temp;

        return new String(charSequence);
    }

    static int getStartSuffix(String str) {

        int index = str.length() - 2;

        while(index >= 0 && str.charAt(index) >= str.charAt(index + 1)) --index;

        return index;
     }
}
