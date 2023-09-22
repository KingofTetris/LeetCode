package 校招笔试真题.BOSS直聘._20230919;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/19
 */
public class 买卖股票的最佳时机III {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int res = solution(nums);
        System.out.println(res);
    }

    private static int solution(int[] nums) {
        //对一个股票可以有两次操作，买入卖出。
        //同一天，你只能买一只股票，或者卖一只股票。
        //也就是说，比如 1 2 8 这种股票
        //你不能第一天买入1，第二天买入2，然后第三天一起卖掉赚13
        //只能第一天买，第三天卖。赚7
        //或者你第2天卖，第三天卖，赚6
        int maxProfit = 0;
        int n  = nums.length;
        int minVal = nums[0],maxVal = nums[n - 1];
        //dp2次，从低到高 和 从高到低
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = -nums[0];
        for (int i = 1; i < n; i++) {
            dp1[i] = Math.max(dp1[i - 1],nums[i] - minVal);
            minVal = Math.min(nums[i],minVal);
        }
        for (int i = n - 2; i >= 0; i--) {
            dp2[i] = Math.max(dp2[i + 1],maxVal - nums[i]);
            maxVal = Math.max(nums[i],maxVal);
        }
        for (int i = 1; i <= n - 1; i++) {
            maxProfit = Math.max(dp1[i-1] + dp2[i],maxProfit);
        }
        int res = Math.max(maxProfit,dp1[n - 1]);
        return res;
    }
}
