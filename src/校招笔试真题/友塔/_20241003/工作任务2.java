package 校招笔试真题.友塔._20241003;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/3
 */


//应该是回溯啊！

/**
 * 今天小ⅹ可以处理n个任务，每个任务有一个重要度分数w，如果小ⅹ此时体力值为p，他会为自己评估一个质量分，w * p，
 * 小x一早体力为1，他有一个衰变常数c，每处理完一个任务，p = p * c，小ⅹ是强迫症，他必须按顺序处理，
 * 只能先处理任务号小的再处理任务号大的，
 * 但他可以选择跳过一些任务，那么小ⅹ今天最多能取得多少质量分？第一行输入整数n和实数c，第二行n个整数表示任务分值w。
 * <p>
 * <p>
 * 写了半天0% 很幽默，下面这个就不对，应该是 8 + 4.5 = 12.5 为什么是12.5?
 * 4 0.5
 * 8 6 1 9
 * 13.25怎么来的??
 */
public class 工作任务2 {

    //感觉就是DP，但是你的DP总之是有错的
    //DP的定义，递推公式，初始值，遍历方式，打印DP！

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入任务数量和衰变常数
        int n = scanner.nextInt();
        double c = scanner.nextDouble();

        // 输入任务的重要度分数
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }

        // 调用函数计算最大质量分数
        double result = maxQualityScore(n, c, w);
        System.out.printf("%.6f\n", result);  // 输出结果，保留6位小数
    }

    private static double maxQualityScore(int n, double c, int[] w) {
        // dp数组，dp[i]表示前i个任务能获得的最大质量分数
        double[] dp = new double[n + 1];
        // 体力值
        double p = 1.0;
        // 遍历每个任务
        for (int i = 1; i <= n; i++) {
            // 不选择第i个任务时，dp[i] = dp[i - 1]
            dp[i] = dp[i - 1];
            // 选择第i个任务时，更新dp[i]
            dp[i] = Math.max(dp[i], dp[i - 1] + p * w[i - 1]);
            // 更新体力值，体力衰减
            p *= c;
        }

        return dp[n];  // 返回处理完所有任务后的最大质量分数
    }
}
