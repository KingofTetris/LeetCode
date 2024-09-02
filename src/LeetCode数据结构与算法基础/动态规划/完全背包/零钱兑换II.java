package LeetCode数据结构与算法基础.动态规划.完全背包;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */
public class 零钱兑换II {

    @Test
    public void test(){
        int amount = 5;
        int[] coins = {5,2,1};
        int change = change(amount,coins);
        System.out.println(change);
    }

    public int change(int amount, int[] coins) {
        //背包求方案数
        //最常用的dp公式一定要记住就是
        //dp[j] += dp[j - nums[i]]
        //相当于dp[j] = dp[j-1] + dp[j-2] + dp[j-3] +.... + dp[1]
        /**
         * 定义DP的含义
         * DP[i] 表示 额定为i的面额 有几种兑换方式
         * dp[j] += dp[ j - nums[i]]
         */
        int[] dp1 = new int[amount + 1];
        int[] dp2 = new int[amount + 1];
        //这题需要定义dp[0] = 1，不然后面累加全是0
        dp1[0] = 1;
        dp2[0] = 1;

        //遍历顺序
        /**
        这题是求组合数 要先遍历物品再遍历背包。这样才不会重复使用。
        */
         //物品
        for (int i = 0; i < coins.length; i++) {
            //背包
            for (int j = coins[i]; j <= amount; j++) {
                dp1[j] += dp1[j - coins[i]];
            }
        }

        //反过来先遍历背包再遍历物品，是求排列数
        //要注意反过来，你最好把i,j也反过来写。尽量统一
        //背包
        for (int j = 0; j <= amount; j++) {
            //物品
            for (int i = 0; i < coins.length; i++) {
                if (j - coins[i] >= 0)
                    dp2[j] += dp2[j - coins[i]];
            }
        }

        System.out.println(Arrays.toString(dp1));
        System.out.println(Arrays.toString(dp2));

        return dp1[amount];
    }

}
