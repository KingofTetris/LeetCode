package 校招笔试真题.美团._20230923;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/23
 */
public class 小美的好序列 {
    // 如果有一个序列，其中每个b_i == b_i-2 那么就称为好序列
    //请你求出最长好序列。
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        if (n < 2){
            System.out.println(0);
            return;
        }
        System.out.println("findLongestGoodSequence(nums) = " + findLongestGoodSequence(nums));
    }

    public static int findLongestGoodSequence(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            if (i >= 2 && arr[i] == arr[i - 2]) {
                dp[i] = dp[i - 2] + 1;
            } else {
                dp[i] = 0;
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }
}
