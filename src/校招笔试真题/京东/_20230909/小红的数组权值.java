package 校招笔试真题.京东._20230909;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/9
 */

//20% MLE 不能开二维数组，要观察数学公式
public class 小红的数组权值 {
    static final int N = 100010;
    static final int MOD = 1000000007;
    public static void main(String[] args) {
       /* Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ai = new int[n];
        for (int i = 0; i < n; i++) {
            ai[i] = sc.nextInt();
        }
        long res = 0;
        //二维会超内存。
        //一半都是0，要怎么压缩矩阵
        //我怎么用一维数组 得到二维矩阵的上半部分？？
        long[][] dp = new long[n][n];//dp代表从i->j数组子数组权重之和
//        long[] dp1 = new long[n];//dp代表从0开始长度为i的数组的权重。
        //那么dp[0][0] = ai[0]
        //dp[i][j] = dp[i][j-1] + (j-i+1)*ai[j]
        //0,0 1,1, 2,2  n-1,n-1
        for (int i = 0; i < n; i++) {
            dp[i][i] = ai[i]; //初始值
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > i) {
                    dp[i][j] = dp[i][j - 1]  + ((long) (j - i + 1) * ai[j]) % MOD;
                }
            }
        }
        for (long[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res += (dp[i][j]) % MOD;
            }
        }
        System.out.println(res);*/
        /**
         * 对于 a[1, 4]的连续子数组可以写出如下：
         * a[1]
         * a[1] a[2]
         * a[1] a[2] a[3]
         * a[1] a[2] a[3] a[4]
         * a[2]
         * a[2] a[3]
         * a[2] a[3] a[4]
         * a[3]
         * a[3] a[4]
         * a[4]
         * 所以答案应该等于如下：
         * 1 * (4a[1] + 3a[2] + 2a[3] + a[4])
         * + 2 * (3a[2] + 2a[3] + a[4])
         * + 3 * (2a[3] + a[4])
         * + 4 * (a[4])
         */
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] w = new int[N];
        for (int i = 1; i <= n; i++) {
            w[i] = scanner.nextInt();
        }
        long t = 0;
        for (int i = 1, j = n; i <= n; i++, j--) {
            t = (t + (long) j * w[i]) % MOD;
        }
        long ans = 0;
        for (int i = 1, j = n; i <= n; i++, j--) {
            ans = (ans + i * t) % MOD;
            t = ((t - (long) j * w[i]) % MOD + MOD) % MOD;
        }
        System.out.println(ans);
    }
}
