package LeetCode数据结构与算法基础.动态递归;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/11/30
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
 * 一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 */
public class 使用最小花费爬楼梯 {

    @Test
    public void test(){
        int[] cost = {1,100,1,1,1,100,1,1,100,1};
        int i = minCostClimbingStairs(cost);
        System.out.println(i);
    }
    public int minCostClimbingStairs(int[] cost) {
        //假设达到dp[i] 的位置所需要消耗的最小花费
        int n = cost.length;
//        System.out.println(n);
        int[] dp = new int[n + 1];
        //递推公式是什么呢？
        // 就是前一步跳1步的花费 和跳两步的花费。
        //dp[i] = Math.min( dp[i-1] + cost[i-1],dp[i-2]+cost[i-2])
        dp[0] = dp[1] = 0;//前两个台阶不需要花费体力
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}
