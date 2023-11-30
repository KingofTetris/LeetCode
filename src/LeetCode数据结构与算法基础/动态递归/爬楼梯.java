package LeetCode数据结构与算法基础.动态递归;

/**
 * @author by KingOfTetris
 * @date 2023/11/30
 */

import org.junit.Test;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 */
public class 爬楼梯 {
    @Test
    public void test(){
        int res = climbStairs(3);
        System.out.println(res);
    }

    //非常经典的dp
    //dp[n]代表爬到第n个台阶有多少种方法
    //则dp[i] = dp[i - 1] + dp[i - 2]
    //也就是到i-1台阶和i-2台阶方法之和
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        //初始值
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        //从3开始
        for(int i = 3;i <= n;i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
