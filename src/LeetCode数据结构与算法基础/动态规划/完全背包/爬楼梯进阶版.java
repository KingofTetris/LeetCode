package LeetCode数据结构与算法基础.动态规划.完全背包;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */

import java.util.Scanner;

class 爬楼梯进阶版 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //简单爬楼梯是要么1步要么2步
        //这里改成n个楼梯，每次可以爬1-m步那么有多少种爬法？
        /**
         * 这是求排列问题，即：1、2 步 和 2、1 步都是上三个台阶，但是这两种方法不一样！
         */
        int m, n;
        while (sc.hasNextInt()) {
            // 从键盘输入参数，中间用空格隔开
            n = sc.nextInt();
            m = sc.nextInt();

            // 求排列问题，先遍历背包再遍历物品
            int[] dp = new int[n + 1];
            dp[0] = 1;

            //完全背包一定要想清楚是求组合还是求排列
            //这两者的遍历顺序是不一样的。
            /**
             * 完全背包
             *      求组合：先物品再背包
             *      求排列：先背包再物品
             */


            //遍历背包
            for (int j = 1; j <= n; j++) {
                //遍历物品
                for (int i = 1; i <= m; i++) {
                    //其实这里应该是dp[j] += dp[j - nums[i]]
                    //只是刚好就是1到m nums[i] = i 而已。
                    if (j - i >= 0) dp[j] += dp[j - i];
                }
            }
            System.out.println(dp[n]);
        }
    }
}
