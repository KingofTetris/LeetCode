package 校招笔试真题.用友._20241016;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/16
 */
public class 拿零食游戏 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        //A和B每次可以从nums的头尾取走一个数
        //A先拿，B后拿
        //A和B都足够聪明，都会使得自己最后拿走的数字之和尽量大
        //那么当nums被拿空的时候，A的数字之和能否大于等于B的数字之和?
        //可以的话，返回true。否则返回false。
        boolean b = canGetLargerSum(nums);
        System.out.println(b);
    }

    public static boolean canGetLargerSum(int[] nums) {
        int n = nums.length;
        // dp[i][j][0]
        //表示A能从子数组[i..j]能获得的最大值
        // dp[i][j][1]
        //表示B能从子数组[i..j]能获得的最大值
        int[][][] dp = new int[n][n][2];
        // 初始化
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = nums[i];
            dp[i][i][1] = 0;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // 拿左边
                int leftSum = nums[i] + dp[i + 1][j][1];
                // 拿右边
                int rightSum = nums[j] + dp[i][j - 1][1];
                if (leftSum > rightSum) {
                    dp[i][j][0] = leftSum;
                    dp[i][j][1] = dp[i + 1][j][0];
                } else {
                    dp[i][j][0] = rightSum;
                    dp[i][j][1] = dp[i][j - 1][0];
                }
            }
        }

        // A 大于等于 B的条件 : dp[0][n - 1][0] >= dp[0][n - 1][1]
        return dp[0][n - 1][0] >= dp[0][n - 1][1];
    }

}
