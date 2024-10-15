package LeetCode数据结构与算法基础.动态规划.概率DP;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/15
 *
 * 塔子哥正在玩一款魂类游戏。
 *
 * 这款游戏中有n个BOSS。塔子哥按照BOSS编号从小到大的顺序挑战BOSS。
 * 当挑战第i个BOSS时，有pi的概率失败，1−pi的概率成功。
 * 此外，当塔子哥连续挑战一个BOSS失败三次时，下一次一定挑战成功。
 *
 * 每当塔子哥失败三次，就会爆发一次小宇宙。
 *
 * 塔子哥想知道，在挑战完所有的BOSS后，他期望爆发多少次小宇宙。
 *
 * 输入描述
 * 第一行一个整数n，代表BOSS的数量。
 *
 * 第二行n个数字pi，表示塔子哥挑战第i个BOSS的成功概率为pi，pi精确到小数点后5位。
 *
 * 1 ≤ n ≤ 500
 * 0 < pi ≤ 1
 *
 * 输出描述
 * 一个数字，代表期望爆发小宇宙的次数，要求精度误差不超过10^(-5)。
 *
 * 样例
 * 输入
 * 2
 * 0.50000 0.50000
 * 输出:
 * 0.328125
 */


/**
 * 思路：概率动态规划
 * 首先，我们需要计算攻击每个怪物失败的概率。对于每个怪物，
 * 我们有四种可能的结果：成功，失败一次，失败两次，失败三次。
 *
 * 然后，我们使用动态规划计算攻击所有怪物失败的概率。我们定义 f[i][j] 为攻击前 i 个怪物，失败次数为 j 的概率。
 * 对于第 i 个怪物，我们有四种可能的失败次数（0，1，2，3），我们需要将这四种情况的概率累加到 f[i][j] 中。
 *
 * i / 3是爆发小宇宙的次数，f[n][i]是失败i次的概率。也就相当于爆发i/3次小宇宙的概率。
 * 那么爆发小宇宙的期望就是 f[n][i] * (i/3)
 *
 * 最后把所有的期望值加起来就是最终小宇宙爆发的期望次数。
 *
 */
public class 黑暗之魂 {

    static final int N = 510;
    static double[] w = new double[N];
    static double[][] f = new double[N][N * 3];
    static double[][] p = new double[N][4];
    static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            w[i] = scanner.nextDouble();
        }
        for (int i = 1; i <= n; i++) {
            p[i][0] = (1 - w[i]);
            p[i][1] = w[i] * p[i][0];
            p[i][2] = w[i] * p[i][1];
            p[i][3] = 1 - p[i][0] - p[i][1] - p[i][2];
        }
        f[0][0] = 1.0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i * 3; j++) {
                for (int k = 0; k <= 3; k++) {
                    f[i][j + k] += f[i - 1][j] * p[i][k];
                }
            }
        }
        double res = 0;
        for (int i = 0; i <= n * 3; i++) {
            res += f[n][i] * (i / 3);
        }
        System.out.println(res);
    }
}
