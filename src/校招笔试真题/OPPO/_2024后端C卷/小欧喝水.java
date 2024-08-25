package 校招笔试真题.OPPO._2024后端C卷;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/25
 */

/**
 * 小欧拿了n个杯子排成了一排，其中有k个杯子装满了水，剩余的n−k个杯子为空的。小欧每回合的操作如下：
 * 1. 随机选择一个杯子。
 * 2. 杯子是空的。回合直接结束。
 * 3. 杯子是满的。如果小欧上一回合喝过了水，则回合结束；否则将喝完这杯水，回合结束。
 *
 * 小欧想知道，她喝完所有水的回合数期望是多少？
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 256M，其他语言512M
 * 输入描述：
 * 两个正整数n,k，用空格隔开。
 * 1<= k <= n <= 10^6
 * 输出描述：
 * 一个浮点数，代表期望的回合数。如果你的答案和正确答案的误差不超过10^{-6}，则认为答案正确。
 * 示例1
 * 输入例子：
 * 1 1
 * 输出例子：
 * 1.000000000
 * 例子说明：
 * 只有一杯水，第一回合就可以喝完。
 *
 * 示例2
 * 输入例子：
 * 2 1
 * 输出例子：
 * 2.000000000
 * 例子说明：
 * 有50%的概率1回合喝完，有25%的概率需要2回合 ，有12.5%的概率需要3回合……
 * 总期望为0.5*2+0.25*3+0.125*4+……=2
 *
 * 示例3
 * 输入例子：
 * 2 2
 * 输出例子：
 * 4.000000000
 * 例子说明：
 * 第一回合有100%的概率喝一杯水。
 * 第二回合无论是否选到有水的杯子都不会喝水。
 * 0.5*3+0.25*4+0.125*5+...=4
 */
public class 小欧喝水 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt(), k = in.nextInt();
        double dp = find(1, n);
        for (int i = 2; i <= k; i++) {
            dp += find(i, n) + 1;
        }
        System.out.print(dp);
    }

    public static double find(int i, int n) { //n个杯子i杯水，喝到第一杯的期望轮次
        if (i == n) {
            return 1;

        }
        int round = 1;
        double select = (double) i / n;
        double noselect = (double) (n - i) / n;
        double pre = 1;
        double res = 0;

        //多个0可以用_分开好看
        while (round * select * pre >= 0.000_000_1) { // 界限越小精度越高
            res += round * pre * select;
            pre *= noselect;
            round++;
        }
        return res;
    }
}
