package LeetCode数据结构与算法基础.动态规划;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/22
 */
public class 最长连续递增子序列 {


    /**
     * 因为要求连续
     * 可以直接用一个max去记录一下就行了
     * @param nums
     * @return
     */

    public int findLengthOfLCIS1(int[] nums) {
        int max = 1;
        int current = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i-1]){
                current++;
                if(current > max)
                    max = current;
            }
            else{
                current = 1;
            }
        }
        return max;
    }

    /**
     * 为了训练，用DP方法
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;;
        if (nums.length <= 1) return n;

        int[] dp = new int[n];
        Arrays.fill(dp,1);

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]){
                dp[i] = dp[i-1] + 1;
            }
        }

        int max = 0;
        for(int i : dp){
            max = Math.max(i,max);
        }
        return max;
    }
}
