package com.javarush.task.task20.task2025;

import java.util.ArrayList;
import java.util.List;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        long[] result = null;

//        List<Long> longList = new ArrayList<>();
//
//        for (long l = 0; l <= N; l++) {
//
//            List<Integer> listM = getMassiveOfDigits(l);
//            int M = listM.size();
//            long summa = 0;
//
//            for (Integer i : listM
//                    ) {
//                summa = summa + (long) Math.pow(i, M);
//            }
//
//            if (l == summa) {
//                longList.add(l);
//            }
//        }

        return null;
    }

    public static long[] convertListToMassive(List<Long> list) {
        long[] result = new long[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }


    public static List<Integer> getMassiveOfDigits(long N) {
        List<Integer> massiveOfDigits = new ArrayList<>();

        while (N > 0) {
            massiveOfDigits.add(0, (int) (N%10));
            N = N / 10;
        }

        return massiveOfDigits;
    }

    public static void main(String[] args) {
//        long[] numbers = getNumbers(1000000);
//        for (Long i:numbers
//             ) {
//            System.out.println(i);
//        }
    }
}
