package com.javarush.task.task34.task3403;

/*
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recurse(int n) {
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                if (n != 1) {
                    System.out.print(i + " ");
                    recurse(n / i);
                } else {
                    System.out.print(i + " ");
                }
                break;
            }
        }

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 132;
        System.out.println(n);
        solution.recurse(n);
    }
}
