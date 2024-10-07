package LeetCode数据结构与算法基础.模拟;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/19
 *
 *
 * 题目内容：
 * 一个 n×m 的网格图 a，左上角为 (0,0)，右下角为 (n−1,m−1)，格子 (i,j) 价值为 i×m+j。
 *
 * 小塔从左上角 (0,0) 为起点，每一步可以走到上下左右四个方向的相邻格子。
 *
 * 每到达一个格子，就能获取相应格子的奖励。
 *
 * 需要注意的是，在到达某个格子获取宝物后，这个格子的宝物会在小塔离开格子后再次刷新。
 *
 * 现在给出一个整数 k，表示游游最多走 k 步。
 *
 * 问：游游最多能获得多少价值的宝物？
 *
 * 输入描述：
 * 第一行输入 q (1 <= q <= 10^5)，表示询问个数。
 *
 * 接下来 q 行，每行输入 n，m，k (1 <= n, m, k <= 10^4, n+m > 2)，表示矩阵大小和限制步数。
 *
 * 输出描述：
 * 输出 q 行分别表示每组数据答案。
 *
 * 样例1：
 * 输入
 *
 * 1
 * 2 2 5
 * 输出
 * 12
 */
public class 游游寻宝 {
    //其实没必要四个方向
    //只有往下，往右才会变大。
    //但是每次往下能变得更大。首先往下，然后往右。
    /**
     * 1
     * 2 2 5
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        while (q-- > 0) {
            long n, m, k, res;
            n = sc.nextLong();
            m = sc.nextLong();
            k = sc.nextLong();
            //如果小于n-1 就一直往下走
            if (k <= n - 1) {
                //往下走之和 (1 + k) * k * m / 2;
                res = (1 + k) * k * m / 2;
            }
            //如果小于n-1 + m-1 那就一直往下走再一直往右走。
            else if (k <= n + m - 2) {
                long a = n * (n - 1) * m / 2;
                k -= (n - 1);
                //往右走之和 k * (n - 1) * m + (k + 1) * k / 2;
                res = a + k * (n - 1) * m + (k + 1) * k / 2;
            }

            //如果大于，那么就在最后两个数来回循环即可
            else {
                res = n * (n - 1) * m / 2 + (m - 1) * (n - 1) * m + m * (m - 1) / 2;
                k -= (n + m - 2);
                res += ((k / 2) * (2 * (n - 1) * m + 2 * m - 3));
                if (k % 2 == 1) {
                    res += ((n - 1) * m + m - 2);
                }
            }
            System.out.println(res);
        }
        sc.close();
    }


}
