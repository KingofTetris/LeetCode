package LeetCode数据结构与算法基础.day2.字符串;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/12/8
 *
 * 给你一个字符串 s ，考虑其所有 重复子串 ：即 s 的（连续）子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
 *
 * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "banana"
 * 输出："ana"
 * 示例 2：
 *
 * 输入：s = "abcd"
 * 输出：""
 *
 *
 * 提示：
 *
 * 2 <= s.length <= 3 * 104
 * s 由小写英文字母组成
 *
 *
 */
public class 最长重复子串 {


    @Test
    public void test(){
        String banana = "banana";
        String abcd = "abcd";
        System.out.println(longestDupSubstring(banana));
        System.out.println(abcd);
    }
    public String longestDupSubstring(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        int len = 0; // len为能匹配上的最高长度
        int loc = -1; // loc为需要计算的起始位置，如果是-1那就是没有。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == j) {
                    continue; // i==j时位置就重复了，需要跳过
                }
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1; // 如果i和j匹配上了，那么这里最长匹配距离就可以+1
                }
                if (dp[i][j] > len) {
                    len = dp[i][j];
                    loc = i; // 同时更新当前长度和下标
                }
            }
        }
        if (loc == -1 || len == 0) { // 两个条件都可以来判断是否空串，任选一个都行
            return "";
        }
        return s.substring(loc, loc + len);
    }
}
