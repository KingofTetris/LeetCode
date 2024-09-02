package LeetCode数据结构与算法基础.动态规划._01背包;

import java.util.Scanner;

/**
 * 小红很喜欢前往小红书分享她的日常生活。已知她生活中有
 * i
 * i个事件，分享第
 * i
 * i个事件需要她花费
 * t
 * i
 * t
 * i
 * ​
 * 的时间和
 * h
 * i
 * h
 * i
 * ​
 * 的精力来编辑文章，并能获得
 * a
 * i
 * a
 * i
 * ​
 * 的快乐值。
 * 小红想知道，在总花费时间不超过
 * T
 * T且总花费精力不超过
 * H
 * H的前提下，小红最多可以获得多少快乐值？
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 256M，其他语言512M
 * 输入描述：
 * 第一行输入一个正整数n，代表事件的数量。
 * 第二行输入两个正整数T和H，代表时间限制和精力限制。
 * 接下来的n行，每行输入三个正整数t_i,h_i,a_i，
 * 代表分享第i个事件需要花费t_i的时间、h_i的精力，收获a_i的快乐值。
 * 1 <= n <= 50
 * 1 <= T,H<= 500
 * 1<= t_i,h_i <= 30
 * 1<= a_i <=10^9
 * 输出描述：
 * 一个整数，代表小红最多的快乐值。
 * 示例1
 * 输入例子：
 * 3
 * 5 4
 * 1 2 2
 * 2 1 3
 * 4 1 5
 * 输出例子：
 * 7
 * 例子说明：
 * 分享第一个和第三个事件即可。
 * 示例2
 * 输入例子：
 * 2
 * 2 2
 * 1 3 3
 * 3 1 4
 * 输出例子：
 * 0
 * 例子说明：
 * 显然，小红无法分享任何事件。
 */
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class 小红的分享日常 {

    /**
     * 这种多个背包互相影响的题怎么套??
     // 20240902 二刷才看到，其实这题就是 零和一 。一个背包装0，一个背包装1。
     // 如果还有多个背包就再多嵌套一层就行了。

     * 就是再加一层for就行了，就这么简单。
     * 不过这题要注意一下dp要开long，不然会溢出。
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int T = sc.nextInt(), H = sc.nextInt();
        int[] t = new int[n];
        int[] h = new int[n];
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
            h[i] = sc.nextInt();
            a[i] = sc.nextInt();
        }
        //背包大小分别是T和H
        //物品重量分别是t[i]和h[i];
        //问最大a[i]?

        //首先肯定是01背包，只能选一次
        //如果是一个背包就容易了
        //dp[j] = max(dp[j],dp[j - weight[i]] + value[i])
        //但问题是现在是两个背包
        //dp[j][k] ? 背包1容量为j背包2容量为k时，最大的快乐值
        //dp[j][k] = max(dp[j][k],dp[j - weight1[i]][k - weight2[i]] + value[i])

        //初始值都是0
        //遍历顺序01背包 先物品再背包，而且背包要倒序
        long[][] dp = new long[T + 1][H + 1];

        //物品
        for (int i = 0; i < n; i++) {
            //背包从最大容量出发
            for (int j = T; j >= t[i]; j--)
                for (int k = H; k >= h[i]; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - t[i]][k - h[i]] + a[i]);
                }
        }

        //最后返回dp[T][H]
        System.out.println(dp[T][H]);
    }
}








