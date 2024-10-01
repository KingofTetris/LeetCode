package LeetCode数据结构与算法基础.动态规划;

/**
 * @author by KingOfTetris
 * @date 2023/7/13
 */

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 从方阵的第一层到最后一层，最小路径和是多少？
 * 每个数字下降的选择有三个方向，正下方，左下方，右下方。
 */
public class 下降路径最小和_20230713 {
    /**
     * 简单DP
     * 我们定义f[i][j] 表示从第一行开始下降，到达第i 行第j 列的最小路径和。
     * 那么我们可以得到这样的动态规划转移方程：
     *                               f[i-1][j-1], j-1 >= 0
     * f[i][j] = matrix[i][j] + min{ f[i-1][j]  0 < j < n
     *                               f[i-1][j+1]  j+1 <= n-1
     * 那么要下降到最后一行的最小下降路径和
     * 答案就是min(0<=j<=n) f[n-1][j]
     *
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] f = new int[n][n];
        //给f赋初始值
        for (int i = 0; i < f[0].length; i++) {
            f[0][i] = matrix[0][i];
        }
        //计算DP数组
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //按条件计算
                //如果是最左边的数字，那么他的上层就不可能有j-1
                if (j == 0) {
                    int min = Math.min(f[i - 1][j], f[i - 1][j + 1]);
                    f[i][j] = matrix[i][j] + min;
                }
                //如果是最右边的数字，那么他的上层就不可能有j+1
                else if (j == n - 1) {
                    int min = Math.min(f[i - 1][j], f[i - 1][j - 1]);
                    f[i][j] = matrix[i][j] + min;
                } else {
                    //正常情况上层都有三个选择
                    int min = Math.min(f[i - 1][j], Math.min(f[i - 1][j - 1], f[i - 1][j + 1]));
                    f[i][j] = matrix[i][j] + min;
                }
            }
        }
        //返回DP数组最后一行的最小值
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < f.length; i++) {
            min = Math.min(min, f[n - 1][i]);
        }
        return min;
    }

}
