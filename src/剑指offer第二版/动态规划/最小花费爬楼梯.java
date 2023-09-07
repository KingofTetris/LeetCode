package 剑指offer第二版.动态规划;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */

//你初始可以选择站在第一个或者第二个台阶上，台阶上有往上跳1步或者2步需要花费的体力值。
//请给出跳到顶楼需要花费的最少体力值
public class 最小花费爬楼梯 {
    public static void main(String[] args) {
//        int[] cost = {1,100,1,1,1,100,1,1,100,1};
        int[] cost = {10,15,20};
        System.out.println(solution(cost));
    }

    public static int solution(int[] cost) {
        //假设达到dp[i] 的位置所需要消耗的最小花费
        int ceil = cost.length + 1;
        int[] dp = new int[ceil];
        //递推公式是什么呢？
        // 就是前一步跳1步的花费 和跳两步的花费。
        //dp[i] = Math.min( dp[i-1] + cost[i-1],dp[i-2]+cost[i-2])
        dp[0] = dp[1] = 0;//前两个台阶不需要花费体力
        for (int i = 2; i < ceil; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[ceil - 1];
    }
}
