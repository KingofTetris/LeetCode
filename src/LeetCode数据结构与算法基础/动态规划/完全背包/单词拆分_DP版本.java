package LeetCode数据结构与算法基础.动态规划.完全背包;

/**
 * @author by KingOfTetris
 * @date 2024/9/2
 */

import java.util.List;

/**
 * https://leetcode.cn/problems/word-break/
 *
 * 单词就是物品，字符串s就是背包，单词能否组成字符串s，就是问物品能不能把背包装满。
 *
 * 拆分时可以重复使用字典中的单词，说明就是一个完全背包！
 *
 * 而且单词是有顺序的，很明显是求一个排列。所以是先遍历背包再遍历物品
 */
public class 单词拆分_DP版本 {

    /**
     * 时间复杂度：O(n^3)，因为substr返回子串的副本是O(n)的复杂度（这里的n是substring的长度）
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        //1.确定DP含义,DP[i]代表以i结尾的字符串能否由字典组成
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        //2.确定DP递推公式
        //dp[i]能否由字典组成就看 i前面的 dp[j] 是否为true
        // 并且 sub(i,j)是否包含在dict中。都成立dp[i] = true 否则为false

        //3.确定初始值
        //dp[0] 应该是什么？ dp[0]一定要为true，不然递推公式就没用了。
        dp[0] = true;

        //4.确定遍历顺序
        //因为对单词顺序是有要求的，也就是求排列，先背包，再物品

        //先背包
        for (int i = 1; i <= n; i++) {
            //再物品
            for (int j = 0; j < i; j++) {
                //取子串
                String sub = s.substring(j,i);
                if (wordDict.contains(sub) && dp[j] == true){
                    dp[i] = true;
                }
            }
        }
        //然后dp[n]即可
        return dp[n];
    }
}
