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
 *
 *
 * 写了半天0% 很幽默，下面这个就不对，应该是 8 + 4.5 = 12.5 为什么是12.5?
 * 4 0.5
 * 8 6 1 9
 * 13.25怎么来的??
 */
public class 工作任务 {


    static double[] res;
    static double resN;
    static int n;
    static int[] w;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入任务数量和衰变常数
        n = sc.nextInt();
        double c = sc.nextDouble();

        // 输入任务的重要度分数
        w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        res = new double[n];
        for (int i = 0; i < res.length; i++) {
            res[i] = w[i];
        }

        // 调用函数计算最大质量分数
        for (int i = 0; i < n; i++) {
            int[] visited = new int[n];
            backTracking(i,i,c,visited);
        }
        System.out.print(resN);  // 输出结果，保留6位小数
    }

    private static void backTracking(int startIndex, int i, double c, int[] visited) {
        if (i >= n){
            return;
        }

        for (int j = i + 1; j < n; j++) {
            //如果访问过
            if (visited[j] == 1){
              continue;
            }
            //没访问过才尝试访问
            visited[j] = 1;
            double t = c * w[j];
            res[startIndex] += t;
            resN = Math.max(resN,res[startIndex]);
            double temp = c;
            c = c * c;
            backTracking(startIndex,i + 1,c,visited);
            //回溯
            visited[j] = 0;
            c = c / temp;
            res[startIndex] -= t;
        }
    }
}
