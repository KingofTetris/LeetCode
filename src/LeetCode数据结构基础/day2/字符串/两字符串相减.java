package LeetCode数据结构基础.day2.字符串;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/4
 */
public class 两字符串相减 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String x = scanner.next();
        String y = scanner.next();
        String result = subtractStrings(x, y);
        System.out.println(result);
    }

    public static String subtractStrings(String x, String y) {
        boolean isNeg = false;
        // 小数字减去大数字，相当于大数字减去小数字，然后加上负号。a-b=-(b-a)
        if (x.length() < y.length()) {
            y = x + y;
            isNeg = true;
        }
        //如果 x.len = y.len 但是x字典序比y小，那么x-y 还是等于-(y-x)
        else if (x.length() == y.length() && x.compareTo(y) < 0) {
            y = x + y;
            isNeg = true;
        }

        int i = x.length() - 1, j = y.length() - 1;
        int carry = 0, diff = 0;
        StringBuilder ans = new StringBuilder();
        while (i >= 0 || j >= 0) {
            if (i >= 0) {
                diff = Character.getNumericValue(x.charAt(i)) - carry;
                if (j >= 0) {
                    diff -= Character.getNumericValue(y.charAt(j));
                }
                if (diff < 0) {
                    carry = 1;
                    diff += 10;
                } else {
                    carry = 0;
                }
            } else {
                diff = 0;
            }
            ans.insert(0, diff);
            i--;
            j--;
        }
        ans = new StringBuilder(ans.toString().replaceAll("^0+|0+$", ""));
        if (ans.length() == 0) {
            ans = new StringBuilder("0");
        }
        if (isNeg) {
            ans = new StringBuilder("-" + ans);
        }
        return ans.toString();
    }
}
