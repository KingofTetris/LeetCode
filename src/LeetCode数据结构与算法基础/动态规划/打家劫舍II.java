package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

import java.util.Arrays;

public class 打家劫舍II {

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 1};
        int rob = rob(nums);
        System.out.println(rob);
    }

    /**
     * 和I的差别在于 这是个循环数组
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        //递推公式有变化
        //dp[i]
        // eg.2 3 2
        // 取nums[i]  Math.max(nums[i - 2] + nums[i],nums[i + 2] + nums[i])???
        // 不取nums[i]
        //初始值有变化
        /**
         * 天真了，真的当环去处理 是推不出他这个递推公式的。
         * 你应该分3种情况
         * 1，不考虑头节点和尾节点，只考虑中间，就和I一样是线性
         * 2.不考虑头节点，也是线性
         * 3.不考虑尾节点，也是线下
         * 其实情况1包含在2，3内。
         * 所以只要把2，3的dp求出来，取更大的那个即可
         */
        int n = nums.length;
        //只有一家直接返回
        if (n == 1) return nums[0];
        //不考虑头节点
        //copyOfRange(source,from,to)
        //[from,to) 经典左闭右开。
        int[] exHeadNums = Arrays.copyOfRange(nums, 1, n);
        int[] exTailNums = Arrays.copyOfRange(nums, 0, n - 1);
        int res1 = rob_ver1(exHeadNums);
        int res2 = rob_ver1(exTailNums);
        return Math.max(res1, res2);
    }


    public int rob_ver1(int[] nums) {
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

        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[n - 1];
    }
}
