package 校招笔试真题.携程._20241010;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/10/11
 */
public class gcd区间 {


    @Test
    public void test(){
        int[] nums = {5,3,1,8};
        int maxGcdSum = maxGcdSum(nums, 2);
        System.out.println(maxGcdSum);
    }


    public static int maxGcdSum(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= n; j++) {
                if (i == 1) {
                    dp[i][j] = dp[i][j-1] + nums[j-1];
                } else {
                    dp[i][j] = Integer.MIN_VALUE;
                    for (int k = i-2; k < j-1; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][k+1] + gcd(nums, k+1, j));
                    }
                }
            }
        }
        return dp[m][n];
    }

    private static int gcd(int[] nums, int start, int end) {
        int res = nums[start-1];
        for (int i = start; i < end; i++) {
            res = gcd(res, nums[i]);
        }
        return res;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
