package LeetCode数据结构与算法基础.动态规划;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/22
 */
public class 判断子序列 {
    //其实这题直接双指针去判断s是不是t的子序列就行了
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
/*
        作者：力扣官方题解
        链接：https://leetcode.cn/problems/is-subsequence/solutions/346539/pan-duan-zi-xu-lie-by-leetcode-solution/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }

    //这里重点使用DP来解决
    public boolean isSubsequence2(String s, String t) {
        //其实这题可以换个思路就是求最长公共子序列
        //如果s和t的最长公共子序列就是s的长度
        //那么说明s就是t的一个子序列
        int n = s.length(),m = t.length();
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();
        int[][] dp = new int[n + 1][m + 1];

        //dp还是子序列老一套
        //遍历
        int max = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (char1[i - 1] == char2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

                //记录最大值
                max = Math.max(max,dp[i][j]);
            }
        }
        //最好判断max和n是否一致即可
        return max == n;
    }
}
