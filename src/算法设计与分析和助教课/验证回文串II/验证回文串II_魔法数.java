package 算法设计与分析和助教课.验证回文串II;

import java.util.Scanner;

/**
 * @author KingofTetris
 * @File 验证回文串II_魔法数
 * @Time 2021/10/21  11:36
 */

public class 验证回文串II_魔法数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();
        System.out.println(s.length());
        System.out.print(validPalindrome(s));
    }

    public static boolean validPalindrome(String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            char c1 = s.charAt(low), c2 = s.charAt(high);
            if (c1 == c2) {
                ++low;
                --high;
            } else {
                return validPalindrome(s, low, high - 1) || validPalindrome(s, low + 1, high);
            }
        }
        return true;
    }

    public static boolean validPalindrome(String s, int low, int high) {
        for (int i = low, j = high; i < j; ++i, --j) {
            char c1 = s.charAt(i), c2 = s.charAt(j);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }
}
