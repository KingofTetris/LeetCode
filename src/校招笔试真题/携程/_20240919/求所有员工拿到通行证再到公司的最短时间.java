package 校招笔试真题.携程._20240919;

import java.util.Arrays;
import java.util.Scanner;

public class 求所有员工拿到通行证再到公司的最短时间 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int p = scanner.nextInt();
        int[] a = new int[2010];
        int[] b = new int[2010];
        int[][] dp = new int[2010][2010];

        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }

        for (int i = 1; i <= k; i++) {
            b[i] = scanner.nextInt();
        }

        Arrays.sort(a, 1, n + 1);
        Arrays.sort(b, 1, k + 1);

        for (int i = 1; i <= n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j < i) {
                    dp[i][j] = Integer.MAX_VALUE;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.abs(a[i] - b[j]) + Math.abs(p - b[j]));
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = n; i <= k; i++) {
            res = Math.min(res, dp[n][i]);
        }

        System.out.println(res);
        scanner.close();
    }
}
