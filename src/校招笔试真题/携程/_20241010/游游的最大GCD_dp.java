package 校招笔试真题.携程._20241010;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/10
 */

//这个DP居然有21.43 亏贼

/**
 * 正确做法是这个什么ST表，又多一个数据结构，算法的学习成本太高的。
 */
public class 游游的最大GCD_dp {
    // 辗转相除法求 GCD
    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 使用稀疏表快速获取区间 [l, r] 的 GCD
    //ST表的用处就是求区间最值问题。
    static int getGCD(int l, int r, int[][] st, int[] Log) {
        int k = Log[r - l + 1];
        return gcd(st[l][k], st[r - (1 << k) + 1][k]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入数组大小 n 和组数 m
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n + 1]; // 1-based indexing
        int[][] dp = new int[n + 1][m + 1]; // dp[i][j] 表示前 i 个元素分成 j 个组的最大 GCD 之和
        int[][] st = new int[n + 1][20]; // 稀疏表，st[i][j] 表示从 i 开始长度为 2^j 的区间的 GCD 值
        int[] Log = new int[n + 1]; // 预处理 log 值，Log[i] 表示 i 的 log2 值

        // 初始化dp数组为负无穷大，以便求最大值
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        // 读取数组 a
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            st[i][0] = a[i]; // 初始化稀疏表的第一列
        }
        sc.close();

        // 预处理 log 值
        Log[1] = 0;
        for (int i = 2; i <= n; i++) {
            Log[i] = Log[i / 2] + 1;
        }

        // 构建稀疏表，用于快速计算区间 GCD
        for (int j = 1; j <= Log[n]; j++) {
            for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                st[i][j] = gcd(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }

        // 动态规划初始化，表示不选任何元素的情况
        dp[0][0] = 0;

        // 进行动态规划求解
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = j; k <= i; k++) {
                    int w = getGCD(k, i, st, Log); // 获取区间 [k, i] 的 GCD 值
                    if (k == 1 && j == 1) {
                        // 特殊情况：当 k 和 j 都为 1 时，表示第一组从第一个元素开始
                        dp[i][j] = Math.max(dp[i][j], w);
                    } else if (j > 1) {
                        // 更新 dp 值，取最大值
                        dp[i][j] = Math.max(dp[i][j], dp[k - 1][j - 1] + w);
                    }
                }
            }
        }

        // 输出最终答案，即前 n 个元素分成 m 个组的最大 GCD 之和
        System.out.println(dp[n][m]);
    }
}
