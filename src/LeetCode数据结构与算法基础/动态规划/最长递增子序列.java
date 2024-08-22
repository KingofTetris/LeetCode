package LeetCode数据结构与算法基础.动态规划;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/22
 */
public class 最长递增子序列 {

    public int lengthOfLIS(int[] nums) {
        //先确定DP的下标和含义
        /**
         * dp[i]代表 以为nums[i]为结尾的最长递增子序列的长度
         *
         */
        //确定递推公式
        /**
         * dp[i] 的递推公式是什么?
         * 状态转移方程
         * 位置i的最长升序子序列等于j从0到i-1各个位置的最长升序子序列 + 1 的最大值。
         * 所以：if (nums[i] > nums[j]) dp[i] = max(dp[i], dp[j] + 1);
         * dp[i] = Math.max(dp[i],dp[j] + 1);
         */
        if (nums.length <= 1) return nums.length;
        int n = nums.length;
        int[] dp = new int[n];
        //初始值,每个位置至少自己是一个递增子序列吧，所以都是1
        Arrays.fill(dp, 1);

        //确定遍历顺序 0没有遍历必要，从1开始。
        for (int i = 1; i < n; i++) {
            //这里是遍历[0,i]之间每个元素,因为不要求连续
            //只要nums[j] 小于nums[i]就行。
            for (int j = 0; j < i; j++) {
                //如果nums[i] > nums[j] 说明有了新的递增子序列
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i : dp) {
            max = Math.max(max, i);
        }
        return max;
    }
}
