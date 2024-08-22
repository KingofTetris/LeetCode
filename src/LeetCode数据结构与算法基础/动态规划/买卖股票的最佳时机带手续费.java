package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Date 2024/08/22
 */

public class 买卖股票的最佳时机带手续费 {

    @Test
    public void test() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        int maxProfit = maxProfit(prices, fee);
        System.out.println(maxProfit);
    }

    //和II其实一样，区别就是价格手续费
    //那么其实需要比较的价格就要加上手续费
    public int maxProfit(int[] prices, int fee) {
        //算了本来想贪心，贪不动，直接套DP模板吧
        int n = prices.length;
        // 0 : 持股（买入）
        // 1 : 不持股（售出）
        // dp 定义第i天持股/不持股 所得最多现金
        // 因为可以买卖无限次，就没有后面的第K次买卖这个限制了
        // 要么持股，要么不持股
        int[][] dp = new int[n][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            //都保持上一天的状态，或者买入，卖出
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);

            dp[i][1] = Math.max(dp[i - 1][1],dp[i - 1][0] + prices[i] - fee);
        }
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }

    /**
     * 买入时支付手续费
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit2(int[] prices, int fee) {
        int len = prices.length;
        // 0 : 持股（买入）
        // 1 : 不持股（售出）
        // dp 定义第i天持股/不持股 所得最多现金
        int[][] dp = new int[len][2];
        // 考虑买入的时候就支付手续费
        dp[0][0] = -prices[0] - fee;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

}
