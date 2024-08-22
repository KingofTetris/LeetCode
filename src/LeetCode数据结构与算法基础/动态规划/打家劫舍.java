package LeetCode数据结构与算法基础.动态规划;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */
public class 打家劫舍 {

    public int rob(int[] nums) {
        //dp含义
        //dp[i] 表示 i这个下标为止 能够偷取的最大金额
        //递推公式
        /**
         * 偷i ，因为不能相邻，则金额等于 dp[i-2] + nums[i]
         * 不偷i 那么 等于 dp[i - 1]
         *
         * 那么dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i])
         * 从递推公式来看得从2开始遍历
         */
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        //得保证有nums[1],才能求DP[1]
        if (n == 1) return dp[0];

        dp[1] = Math.max(nums[0],nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2] + nums[i]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[n - 1];
    }
}
