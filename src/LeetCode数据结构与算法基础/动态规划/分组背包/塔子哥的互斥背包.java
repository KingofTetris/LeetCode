package LeetCode数据结构与算法基础.动态规划.分组背包;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/8/24
 */
public class 塔子哥的互斥背包 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //第一行输入三个整数
        //n,m,k，分别表示商品数量、背包容量和互斥关系数量。
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        //接下来n 行，每行两个整数 wi,vi
        //接下来K行 每行a ,b 表示a,b互斥


        //从1开始好对于物品下标
        int[] weight = new int[n + 1];
        int[] value = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        int[] dp = new int[m + 1];

        //遍历背包
        for (int i = m; i >= 0; i--) {
            //遍历物品
            for (int j = 1; j <= 1; j++) {
                if (i - j >= 0){
                    dp[i] = Math.max(dp[i],dp[i - weight[j]] + value[j]);
                }
            }
        }
    }


}
