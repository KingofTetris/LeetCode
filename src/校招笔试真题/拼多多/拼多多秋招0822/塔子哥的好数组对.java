package 校招笔试真题.拼多多.拼多多秋招0822;

import java.util.Scanner;


//第二题01序列的加强版。有点难度。
//而且要注意他不是要你求形成好数组对后的最大和，而是要求最少交换次数。
public class 塔子哥的好数组对 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // 读取测试用例数量
        for (int t = 0; t < T; t++) {
            solve();
        }
    }
    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取数组的长度
        int[] a = new int[n];
        int[] b = new int[n];

        // 读取数组 a 的元素
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // 读取数组 b 的元素
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }

        long[][] cnt = new long[n][2]; // 用于记录操作次数
        long[][] val = new long[n][2]; // 用于记录价值

        //val[i][0]表示 前i个数ai和bi不交换的最大值
        //val[i][1]表示 前i个数ai和bi交换的最大值。
        /**
         * 状态转移就有点麻烦
         * dp[i][0] = dp[i-1][1] + abs(ai - bi-1) + abs(bi - ai-1)
         *         或者 = dp[i-1][0] + abs(ai - ai-1) + abs(bi - bi-1)
 *         取这两者的max
 *
 *         dp[i][1]也是一样
         */
        val[0][0] = val[0][1] = 0;
        cnt[0][0] = 0; cnt[0][1] = 1;

        for (int i = 1; i < n; i++) {
            int tmp_n = Math.abs(a[i] - a[i - 1]) + Math.abs(b[i] - b[i - 1]);
            int tmp_y = Math.abs(a[i] - b[i - 1]) + Math.abs(b[i] - a[i - 1]);

            if (val[i - 1][0] + tmp_n > val[i - 1][1] + tmp_y ||
                (val[i - 1][0] + tmp_n == val[i - 1][1] + tmp_y && cnt[i - 1][0] < cnt[i - 1][1])) {
                val[i][0] = val[i - 1][0] + tmp_n;
                cnt[i][0] = cnt[i - 1][0];
            } else {
                val[i][0] = val[i - 1][1] + tmp_y;
                cnt[i][0] = cnt[i - 1][1];
            }

            if (val[i - 1][0] + tmp_y > val[i - 1][1] + tmp_n ||
                (val[i - 1][0] + tmp_y == val[i - 1][1] + tmp_n && cnt[i - 1][0] < cnt[i - 1][1])) {
                val[i][1] = val[i - 1][0] + tmp_y;
                cnt[i][1] = cnt[i - 1][0] + 1;
            } else {
                val[i][1] = val[i - 1][1] + tmp_n;
                cnt[i][1] = cnt[i - 1][1] + 1;
            }
        }

        if (val[n - 1][0] < val[n - 1][1]) {
            System.out.println(cnt[n - 1][0]);
        } else if (val[n - 1][0] > val[n - 1][1]) {
            System.out.println(cnt[n - 1][1]);
        } else {
            System.out.println(Math.min(cnt[n - 1][0], cnt[n - 1][1]));
        }
    }
}
