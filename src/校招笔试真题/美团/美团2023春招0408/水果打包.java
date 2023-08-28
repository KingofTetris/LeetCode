package 校招笔试真题.美团.美团2023春招0408;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/11
 *
小美不干外卖配送了，转行开了一家水果店。
一天她接到了一个大单，客户订购了 n 个水果，并且要求打包成多个果篮，一个果篮最多装 m 个水果。
为了包装方便，水果按从 1 到 n 编号，同一个果篮里装的水果编号必须是连续的。(编号连续的意思是下标连续)
果篮的成本与容积成线性关系。
为了估计容积，小美简单地用样本中点估计了一下。
具体来说，假设一个果篮中装的最大的水果体积是 u，最小的是 v，
那么这个果篮的成本就是 k × floor((u+v)/2) + s，其中 k 是果篮中装入水果的个数，s 是一个常数，
floor(x) 是下取整函数，比如 floor(3.8)=3, floor(2)=2。
客户并没有规定果篮的数量，但是希望果篮的成本越小越好，毕竟买水果就很贵了。
请求出小美打包这 n 个水果所用的最小花费。

输入描述
第一行三个正整数 n, m, s。意义如题面所示。

第二行 n 个正整数 a1, a2, ..., an，表示每个水果的体积。

对于全部数据，1 ≤ n ≤ 1e4,   1 ≤ m ≤ 1e3,   m ≤ n,   1 ≤ ai, s ≤ 1e4。

输出描述
输出一个整数，表示打包这 n 个水果果篮的最小成本。


样例输入
6 4 3
1 4 5 1 4 1
样例输出
21

 */
public class 水果打包 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt();
        int[] volume = new int[n];
        for (int i = 0; i < n; i++) {
            volume[i] = sc.nextInt();
        }
        int solution = solution(n, m, s, volume);
        System.out.println(solution);
    }

    //一般最大最小再看一眼数据规模，就能判断动态优化了。
    private static int solution(int n, int m, int s, int[] volume) {
        int[] dp = new int[n + 1];//dp表示存储前i个水果的最小成本
        for (int i = 0; i < n; i++) {
            dp[i + 1] = Integer.MAX_VALUE;
        }
        /**
         * 然后，对于每个 i，通过反向循环求出其前 j 个水果的体积最大值和最小值，
         * 并计算出成本 cost，最后根据状态转移方程
         * 假定最后一个果篮装的水果数是1,2,...,m。那么dp[i]就等于下面的公式
         * dp[i]=min(dp[i-1]+cost[1],dp[i-2]+cost[2],...,dp[i-m]+cost[m])
         * 更新 dp[i] 的值，其中 cost[] 的最大值和最小值通过反向循环得到。
         * 最终，程序输出 dp[n]，即前 n 个水果的最小成本。
         */
        for (int i = 1; i <= n; i++) {
            //1 4 5 1 4 1
            int max = -1, min = Integer.MAX_VALUE; //每轮算dp都去找一个max,min来算cost
            for (int j = 1; j <= m && j <= i; j++) { // 从这里开始算cost1,cost2,...costm
                //去找[0,i]这个区间的max,min，另外是倒着找的，因为是确定了dp,要算cost.所以才和volume[i - j]比较
                max = Math.max(max, volume[i - j]);
                min = Math.min(min, volume[i - j]);
                int cost = j * ((max + min) / 2) + s;//每个果篮的成本
                dp[i] = Math.min(dp[i], dp[i - j] + cost);//每次dp[i]选最小值
            }
        }
        return dp[n];
    }
}
