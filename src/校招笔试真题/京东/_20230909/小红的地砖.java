package 校招笔试真题.京东._20230909;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/9
 */

//AC
public class 小红的地砖 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        //假设达到dp[i] 的位置所需要消耗的最小花费
        int[] dp = new int[n];
        //递推公式是什么呢？
        // 就是前一步跳1步的花费 和跳两步的花费。
        //dp[i] = Math.min(dp[i-1] + cost[i-1],dp[i-2]+cost[i-2])
        dp[0] = 0;//前一个台阶不需要花费体力
        dp[1] = nums[1];//跳到第一个台阶的体力花费是nums[1]
        for (int i = 2; i < n; i++) {
//            dp[i] = Math.min(dp[i - 1] + nums[i - 1], dp[i - 2] + nums[i - 2]);
            dp[i] = Math.min(dp[i - 1] + nums[i], dp[i - 2] + nums[i]);
        }
        System.out.println(dp[n - 1]);//到达第n块的最小耗费体力
    }
}
