package 校招笔试真题.米哈游._20240803;

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

        //建立互斥关系
        List<Integer>[] graph = new ArrayList[k + 1];
        for (int i = 0; i <= k; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < k; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            graph[s].add(t);
            graph[t].add(s);
        }

        //模拟01 互斥背包
        /**
         * dp[j]表示j容量的背包的最高价值
         *
         * dp[j] = Math.max(dp[j],dp[ j - weight[i] ] + value[i]);
         *
         * dp[0] = 0
         */
        int[] dp = new int[m + 1];
        dp[0] = 0;

        //记录包里面有什么东西
        HashSet<Integer> products = new HashSet<>();

        //遍历物品
        for (int i = 1; i <= n; i++) {
            /**
             * 现在的问题是有互斥关系，就不能随意拿
             * 需要判断物品是否互斥
             */
            //遍历背包，而且是倒着遍历，防止重复拿
            //只要背包还能放得下weight[i]
            for (int j = m; j >= weight[i]; j--) {
                //下面是多重背包的扩展，每种物品的数量不同，就需要把这些物品都分成一个看出01背包
                /**
                 * 本来应该这样写，放到weight和value里面去，但是这样会超时
                 * for (int i = 0; i < n; i++) {
                 *     while (nums[i] > 1) { // 物品数量不是一的，都展开
                 *         weight.push_back(weight[i]);
                 *         value.push_back(value[i]);
                 *         nums[i]--;
                 *     }
                 * }
                 *
                 *  //在遍历背包的同时，拆开物品。
                 *  for (int k = 1; k <= nums[i] && (j - k * weight[i]) >= 0; k++) {
                 *      dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * value[i]);
                 *   }
                 */

                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        //输出
        System.out.println(dp[m]);
    }


}
