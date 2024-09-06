package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/22
 */
public class 最长重复子数组 {


    @Test
    public void test(){
        int[] nums1 = {1,2,3,2,1,5};
        int[] nums2 = {3,2,1,5,4,7};
        int length = findLength(nums1, nums2);
        System.out.println(length);
    }

    /**
     * 如果暴力就需要O(n^3) 必定超时
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        //dp的下标和含义
        //dp[i][j] 代表以nums[i-1]为结尾的的子数组A 和nums[j-1]为结尾的子数组B
        //他们的最长重复子数组为dp[i][j]

        //递推公式
        //if(A[i-1] == B[j-1]) then dp[i][j] = dp[i - 1][j - 1] + 1;

        /**
         * 初始化
         * 根据dp[i][j]的定义，dp[i][0] 和dp[0][j]其实都是没有意义的！
         * 因为如果i=0,那么i-1 = -1 难道子串还有-1?所以
         * 对于DP数组的第一行，第一列是无意义的。
         *
         * 但dp[i][0] 和dp[0][j]要初始值，因为 为了方便递归公式dp[i][j] = dp[i - 1][j - 1] + 1;
         *
         * 所以dp[i][0] 和dp[0][j]初始化为0。
         *
         */
        int result = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        /**
         * 从1 1开始递推
         */
        for (int i = 1; i < nums1.length + 1; i++) {
            for (int j = 1; j < nums2.length + 1; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    //因为result其实藏在dp数组里面，为了省去再遍历一次
                    //直接在递推公式的时候，直接赋值
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return result;
    }
}
