package LeetCode数据结构与算法基础.动态规划.完全背包;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */
public class 组合总和IV {

    public int combinationSum4(int[] nums, int target) {
        //和兑换零钱一样，不过这里是求排列数
        int[] dp = new int[target + 1];
        dp[0] = 1; //保证dp[j - nums[i]]至少为1，也就是凑成j至少有nums[i]=j 一种方案。


        /**
         * 求排列，先背包，再物品
         */
        //遍历背包
        for (int j = 0; j <= target; j++) {
            //遍历物品
            for (int i = 0; i < nums.length; i++) {
                //这个顺序需要保证 j - nums[i] >= 0
                if (j - nums[i] >= 0)
                    dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
