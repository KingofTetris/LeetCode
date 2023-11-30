package LeetCode数据结构与算法基础.day2.字符串;

/**
 * @author by KingOfTetris
 * @date 2023/9/9
 */

//等于leetcode分割回文串
public class 回文子串的数量 {
    //给你一个字符串判断，这个字符串包含多少个回文子串
    public static void main(String[] args) {
        String s = "asdasd";
        int i = countSubstrings(s);
        System.out.println(i);
    }

    public static int countSubstrings(String s) {
        int n = s.length();
        char sChar[] = s.toCharArray();
        int res = 0;
        boolean dp[][] = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if (sChar[i] == sChar[j] && (j - i < 3 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    res++;
                }
            }
        }
        return res;
    }
}
