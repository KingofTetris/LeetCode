package 校招笔试真题.OPPO._2024后端C卷;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/25
 */

/**
 * 小欧有一个长度为n的等差数列，a是首相，d是公差
 * a,a+d,a+2d,⋯,a+(n−1)d。
 * 现在，小欧把这 n 个数看作一个集合，每次操作可以从集合中任意选两个数ai,aj
 * ，如果是 ai+aj 是偶数，那么可以将 (ai+aj) / 2加入到集合中。
 *小欧想知道，经过若干次操作后，集合中最多能有多少个数。
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 256M，其他语言512M
 * 输入描述：
 * 一行三个整数 n, a, d，表示等差数列的长度，首项和公差。
 * 1 <= n <= 10^5
 * 1 <= a, d <= 10^9
 * 输出描述：
 * 输出一个整数，表示集合中最多能有多少个数。
 * 示例1
 * 输入例子：
 * 5 1 2
 * 输出例子：
 * 9
 * 例子说明：
 * 一开始集合为 [1, 3, 5, 7, 9]，选择相邻两项，可以得到 [1, 2, 3, 4, 5, 6, 7, 8, 9]。
 */
public class 小欧的等差数列 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = sc.nextInt();
        int a = sc.nextInt();
        int d = sc.nextInt();
        //主要看d的奇偶性
        //如果d是奇数，就不会产生新数字
        if (d % 2 == 1) System.out.println(n);
            //如果是偶数
        else {
            //先算出最后一项
            long end = a + (n - 1) * (long)d;
            // 以下代码段的作用是将d除以2直到d变为奇数
            while (d % 2 == 0) {
                d = d / 2;
            }
            System.out.println((end - a) / d + 1);
        }
    }
}
