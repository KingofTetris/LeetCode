package 校招笔试真题.用友._20230918;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/18
 */
public class 有多少种走法 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        int m = sc.nextInt();
        //从左下走到右上 只能往右或者往左。
        //那其实跟左上到右下 只能往右，往下不是一样吗。
        solution(n,m);
    }

    private static void solution(int n, int m) {
        int NN = n + 1;
        int MM = m + 1;
        int[][] dp = new int[NN][MM];
        //行初始化
        for (int i = 0; i < MM; i++) {
            dp[0][i] = 1;
        }
        //列初始化
        for (int i = 0; i < NN; i++) {
            dp[i][0]  = 1;
        }
        //从1，1开始
        //走到右下角
        for (int i = 1; i < NN; i++) {
            for (int j = 1; j < MM; j++) {
                //等于从上面下来，和从右边过来之和
                dp[i][j] = dp[i - 1][j] + dp[i][j -1];
            }
        }
        System.out.println(dp[n][m]);
    }
}
