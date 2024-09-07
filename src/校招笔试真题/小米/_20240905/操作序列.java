package 校招笔试真题.小米._20240905;

import java.util.*;

public class 操作序列 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            a[i] = a[i] % x;
        }

        // 只考虑删元素，统计最小删除次数，得到余数r
        // 第一维前n个元素，第二维枚举模x的余数
        int[][] dp = new int[n + 1][x];

        // 初始都不可达
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 10000);
        }

        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            // 当前元素
            int curA = a[i - 1];
            for (int r = 0; r <= x - 1; r++) {
                // 不删除当前元素，要达到r，需要前面的元素和余数值
                int needFront = (r - curA + x) % x;
                // 要么删，要么不删，只依赖前一个元素状态
                dp[i][r] = Math.min(dp[i - 1][r] + 1, dp[i - 1][needFront]);
            }
        }

        int res = Integer.MAX_VALUE;

        // 对n个元素，所有余数值减去达到0，还需要的操作步数，取最小的一个
        for (int i = 0; i <= x - 1; i++) {
            if (i == 0) {
                res = dp[n][0];
                continue;
            }
            res = Math.min(res, dp[n][i] + x - i);
        }
        System.out.println(res);
    }
}
