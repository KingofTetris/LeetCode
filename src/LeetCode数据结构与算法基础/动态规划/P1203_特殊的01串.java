package LeetCode数据结构与算法基础.动态规划;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/5/22
 */

//TODO 有时间再啃吧
public class P1203_特殊的01串 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(),a = in.nextInt(),b = in.nextInt();
        String s = in.nextLine();
        //先把不能的情况排除
        if (!canBeSum(n, a, b)) System.out.println(-1); //没法修改
        //如果可以修改，最少修改次数是多少？
        //dp[i,0]表示字符串前i个位置全部修改为0需要的最小修改次数
        //dp[i,1]同理
        //转移方程
        /**
         * dp[i][0] = min(j<i && j+1 = i mod a) { min(dp[j][0],dp[j][1]) + Count[j+1][i][1]}
         * Count_l,r,1 表示区间[l,r]中1的个数。
         */
        else {
            int[][] cnt = new int[n + 1][2];
            cnt[0][0] = 0;
            cnt[0][1] = 0;

            for (int i = 1; i <= n; i++) {
                cnt[i][0] = cnt[i - 1][0];
                cnt[i][1] = cnt[i - 1][1];
                int v = s.charAt(i - 1) - '0';
                cnt[i][v]++;
            }
            int[][] dp = new int[n + 1][2];
            int[] mp_a = new int[a];
            int[] mp_b = new int[b];
            for (int i = 0; i <= n; i++) {
                dp[i][0] = Integer.MAX_VALUE;
                dp[i][1] = Integer.MAX_VALUE;
            }
            mp_a[0] = 0;
            mp_b[0] = 0;
            dp[0][0] = 0;
            dp[0][1] = 0;

            for (int i = 1; i <= n; i++) {
                if (mp_a[i % a] != Integer.MAX_VALUE) {
                    dp[i][0] = cnt[i][1] + mp_a[i % a];
                }
                if (mp_b[i % b] != Integer.MAX_VALUE) {
                    dp[i][1] = cnt[i][0] + mp_b[i % b];
                }
                if (Math.min(dp[i][0], dp[i][1]) != Integer.MAX_VALUE) {
                    mp_a[i % a] = Math.min(mp_a[i % a], Math.min(dp[i][0], dp[i][1]) - cnt[i][1]);
                    mp_b[i % b] = Math.min(mp_b[i % b], Math.min(dp[i][0], dp[i][1]) - cnt[i][0]);
                }
            }

            if (Math.min(dp[n][0], dp[n][1]) == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(Math.min(dp[n][0], dp[n][1]));
            }
        }
    }

    public static boolean canBeSum(int n,int a,int b){
        int gcd = gcd(a,b);
        return n % gcd == 0;
    }

    //辗转相除求gcd
    public static int gcd(int a,int b){
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        //如果remainder等于0了，说明b就是最大公因子
        return b;
    }
}
