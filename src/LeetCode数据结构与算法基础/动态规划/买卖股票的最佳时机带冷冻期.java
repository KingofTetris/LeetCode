package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

/**
 * @Date 2024/08/22
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 */

public class 买卖股票的最佳时机带冷冻期 {
    //带冷冻期的其实模板也是一样，只不过你没办法连续买入卖出。也就是冷冻期为1

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[][] dp = new int[prices.length][2];

        // 怎么这个代码是反过来 不持有，持有。
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][1], -prices[1]);//同时只能有一支股票，要么保持第一次持有，要么持有当日股票。

        for (int i = 2; i < prices.length; i++) {
            // dp公式
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //注意这个i-2 因为有冷冻期，所以只能是i-2那天不买，然后今天买入。
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }

        //最后返回最后一天，全部卖光的状态即可。
        return dp[prices.length - 1][0];
    }

}
