package LeetCode数据结构与算法基础.动态规划.多重背包;

import java.util.Scanner;
class multi_pack{
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);

        /**
         * bagWeight:背包容量
         * n:物品种类
         */
        int bagWeight, n;

        //获取用户输入数据，中间用空格隔开，回车键换行
        bagWeight = sc.nextInt();
        n = sc.nextInt();
        int[] weight = new int[n];
        int[] value = new int[n];
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) weight[i] = sc.nextInt();
        for (int i = 0; i < n; i++) value[i] = sc.nextInt();
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();

        int[] dp = new int[bagWeight + 1];

        //当成01背包，先遍历物品
        for (int i = 0; i < n; i++) {
            //再遍历背包，防止物品重复拿去。
            for (int j = bagWeight; j >= weight[i]; j--) {
                /** 下面就是多重背包和01背包的区别，多了一层循环，去拆分物品
                //遍历每种物品的个数 每次拿一个。
                // 注意这里的状态公式就变成了
                // max(dp[j],dp[j - k * w] + k * v)
                //并且要保证 j >= k*w 不然会越界。
                 **/
                for (int k = 1; k <= nums[i]; k++) {
                    if ((j - k * weight[i]) >= 0){
                        dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * value[i]);
                    }
                }
            }
        }
        System.out.println(dp[bagWeight]);
    }
}
