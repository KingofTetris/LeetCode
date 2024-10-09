package 校招笔试真题.友塔._20241003;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/3
 */


//应该是回溯啊！

/**
 今天小ⅹ可以处理n个任务，每个任务有一个重要度分数w，如果小ⅹ此时体力值为p，
 他会为自己评估这个任务的质量分为w * p，
 小x一早体力为1，他有一个衰变常数c，每处理完一个任务，p = p * c，
 并且小ⅹ是强迫症，他必须按顺序处理，也就是只能先处理任务号小的任务再处理任务号大的任务。
 但在保持任务相对顺序的前提下，他可以选择跳过中间的一些任务，
 那么小ⅹ今天最多能取得多少质量分？第一行输入整数n和实数c，第二行n个整数表示任务分值w。
 用例1：
 4 0.5
 8 6 1 9
 小x会选择任务1和任务4，则他能取得的最大质量分为8*1 + 9 * 0.5 = 12.5
 */
public class 工作任务2 {

    //感觉就是DP，但是你的DP总之是有错的
    //DP的定义，递推公式，初始值，遍历方式，打印DP！
    //TODO 这个DP到底怎么写。唉。

    public static double maxQualityScore(int n, double c, int[] w) {
        double[][] dp = new double[101][n + 1];
        double p = 1.0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 100; j++) {
                if (j >= w[i - 1]) {
                    dp[j][i] = Math.max(dp[j][i - 1], dp[j - w[i - 1]][i - 1] + w[i - 1] * p);
                } else {
                    dp[j][i] = dp[j][i - 1];
                }
            }
            p *= c;
        }

        double maxScore = 0;
        for (int j = 1; j <= 100; j++) {
            maxScore = Math.max(maxScore, dp[j][n]);
        }
        return maxScore;
    }

    public static void main(String[] args) {
        int n = 4;
        double c = 0.5;
        int[] w = {8, 6, 1, 9};
        System.out.println(maxQualityScore(n, c, w)); // 输出: 12.5
    }

}
