package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author KingofTetris
 * @File 买卖股票的最佳时机II
 * @Time 2021/9/29  11:29
 */


public class 买卖股票的最佳时机IV {

    @Test
    public void test() {
        int k = 3;
        int[] prices = {3, 2, 6, 5, 0, 3, 8, 9, 10};
        int maxProfit = maxProfit(k, prices);
        System.out.println(maxProfit);
    }

    //这题就是III的扩展，从至多买卖2次，变成K次
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // 边界判断, 题目中 length >= 1, 所以可省去
        if (prices.length == 0) return 0;
        /*
         * 定义 2k + 1 种状态:
         * 0: 没有操作, 1: 第一次买入, 2: 第一次卖出, 3: 第二次买入, 4: 第二次卖出
         * ....
         * 2k - 1 : 第k次买入
         * 2k : 第k次卖出
         *
         */
        //n就是有多少天
        int[][] dp = new int[n][2 * k + 1];

        /**
         * 初始化
         */
        // 初始化第二次买入的状态是确保 最后结果是最多两次买卖的最大利润
        //当天买入当天卖出K次，都是-price[0]
        for (int i = 1; i < 2 * k; i += 2) {
            dp[0][i] = -prices[0];
        }
        //卖出则都是0,默认就是0，就没必要赋值了

        for (int i = 1; i < n; i++) {
            //用j来控制赋值
            //因为每次要买入1次卖出1次
            //每次跳过2
            for (int j = 0; j < 2 * k - 1; j += 2) {
                //第K次买入 2k - 1
                //延续上一次的持有状态，或者买入
                dp[i][j + 1] = Math.max(dp[i - 1][j + 1], dp[i - 1][j] - prices[i]);
                //第k次卖出 2k
                //延续上一次的未持有状态，或者卖入
                dp[i][j + 2] = Math.max(dp[i - 1][j + 2], dp[i - 1][j + 1] + prices[i]);
            }
        }
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return dp[n - 1][2 * k];
    }
}
