package src.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;

public class XorSequence {

    public static void main(String[] args) {

    }

    static long xorSequence(long left, long right) {

        if(left == 0) return generateXorSumToN(right);

        return generateXorSumToN(left - 1) ^ generateXorSumToN(right);
    }

    private static long generateXorSumToN(long n) {

        long remainder = n % 8;

        if(remainder == 2 || remainder == 3) return 2;

        else if(remainder == 0 || remainder == 1) return n;

        else if(remainder == 4 || remainder == 5) return n + 2;

        return 0;
    }

}
