package LeetCode数据结构与算法基础.动态规划.完全背包;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */
public class 零钱兑换 {


    @Test
    public void test() {
        /*int[] coins = {1, 2, 5};
        int amount = 11;*/
        int[] coins = {2};
        int amount = 3;
        int change = coinChange(coins, amount);
        System.out.println(change);
    }

    //求方案中的最小值
    public int coinChange(int[] coins, int amount) {
        //首先确定dp的含义
        //dp[j] 代表凑齐j 需要的最少硬币数
        //dp[j] = min(dp[j],dp[ j - coins[i] ] + 1);
        //dp[ j - coins[i]]就代表凑齐j - coins[i]所需最少的硬币数
        //那么再加个coins[i]就凑齐j了，也就是再加一个硬币
        int max = Integer.MAX_VALUE;
        int[] dp = new int[amount + 1];
        //初始值 因为是求Min 那么初始值都赋为最大
        Arrays.fill(dp, max);
        dp[0] = 0;

        /**
         * 这题本质不是求组合或者排列，是求使用硬币的最少个数，
         * 那么其实是组合还是排列都无所谓。
         */
        //物品
        for (int i = 0; i < coins.length; i++) {
            //背包
            for (int j = coins[i]; j <= amount; j++) {
                //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                //非则他+1 就溢出变成MIN_VALUE了
                if (dp[j - coins[i]] != max) {
                    //选择硬币数目最小的情况
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount] == max ? -1 : dp[amount];
    }
}
