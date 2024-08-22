package LeetCode数据结构与算法基础.动态规划.完全背包;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */
public class 完全平方数 {


    @Test
    public void test(){
        int i = numSquares(1);
        System.out.println(i);
    }
    /**
     * 这题和零钱兑换几乎是一模一样，只是元素变成了完全平方数
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        int max = Integer.MAX_VALUE;
        Arrays.fill(dp, max);

        //这里的dp[0] = 0
        dp[0] = 0;

        //物品
        for (int i = 1; i * i <= n; i++) {
            //背包
            for (int j = i * i; j <= n; j++) {
                ////不需要這個if statement，
                // 因爲在完全平方數這一題不會有"湊不成"的狀況發生（ 一定可以用"1"來組成任何一個n），
                // 故comment掉這個if statement。
                if (dp[j - i * i] != max) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }

        //因为有1，dp[n]不可能等于max 一定有解的
        return dp[n];
    }
}
