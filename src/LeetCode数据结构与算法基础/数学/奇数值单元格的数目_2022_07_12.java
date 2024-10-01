package LeetCode数据结构与算法基础.数学;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/7/12 13:46
 * 给你一个 m x n 的矩阵，最开始的时候，每个单元格中的值都是 0。
 * <p>
 * 另有一个二维索引数组 indices，indices[i] = [r_low_i, c_low_i] 指向矩阵中的某个位置，
 * 其中 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
 * <p>
 * 对 indices[i] 所指向的每个位置，应同时执行下述增量操作：
 * <p>
 * r_low_i 行上的所有单元格，加 1 。
 * c_low_i 列上的所有单元格，加 1 。
 * 给你 m、n 和 indices 。请你在执行完所有 indices 指定的增量操作后，返回矩阵中 奇数值单元格 的数目。
 * <p>
 * 输入：m = 2, n = 3, indices = [[0,1],[1,1]] [0,1]表示第0行和第1列都+1，[1,1]表示第1行和第1列都+1，
 * 输出：6
 * 解释：最开始的矩阵是
 * 0 0 0
 * 0 0 0
 * 第一次增量操作后得到
 * 1 2 1
 * 0 1 0
 * 最后的矩阵是
 * 1 3 1
 * 1 3 1
 * ，里面有 6 个奇数。
 * <p>
 * 输入：m = 2, n = 2, indices = [[1,1],[0,0]]
 * 输出：0
 * 解释：最后的矩阵是 [[2,2],[2,2]]，里面没有奇数。
 * <p>
 * 提示：
 * <p>
 * 1 <= m, n <= 50
 * 1 <= indices.length <= 100
 * 0 <= ri < m
 * 0 <= ci < n
 *  
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(n + m + indices.length) 且仅用 O(n + m) 额外空间的算法来解决此问题吗？
 */
public class 奇数值单元格的数目_2022_07_12 {
    @Test
    public void test() {
        int[][] indeces = {{1, 1}, {0, 0}};
        System.out.println(oddCells(2, 2, indeces));
    }

    /**
     * 简单模拟遍历
     * 时间 O(q*(m+n)+m*n) q是操作数组的长度
     * 空间 O(m*n)
     *
     * @param m
     * @param n
     * @param indices
     * @return
     */
    public int oddCells(int m, int n, int[][] indices) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < indices.length; i++) {
            int[] operation = indices[i];

            /**
             * 对应行++
             */
            for (int j = 0; j < n; j++) {
                matrix[operation[0]][j]++;
            }

            /**
             * 对应列++
             */
            for (int j = 0; j < m; j++) {
                matrix[j][operation[1]]++;
            }
        }
        /**
         * 然后遍历矩阵算出奇数的个数
         */
        int count = 0;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if ((anInt & 1) == 1)
                    count++;
            }
        }
        return count;
    }

    /**
     * 或者用两个数组row和col来统计每行每列被操作的次数
     * 最后去遍历row[x]+col[y]被操作的次数，奇数就+1
     * <p>
     * 时间 O(q+m*n)
     * 空间O(m+n)
     *
     * @param m
     * @param n
     * @param indices
     * @return
     */
    public int oddCells2(int m, int n, int[][] indices) {
        int[] row = new int[m];
        int[] col = new int[n];
        for (int[] operation : indices) {
            row[operation[0]]++;//行++
            col[operation[1]]++;//列++
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (((row[i] + col[j]) & 1) == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 继续改进，考虑奇数的特性只能是 一奇一偶才能得到奇数。
     * 那么假设row有oddx个奇数，col有oddy个奇数
     * 对于row[x]是偶数的，那么第x行有 oddy 个是奇数。
     * 对于row[x]是奇数的,那么第x行有 n-oddy 个是奇数。
     * 所以最后总的奇数个数就是 oddx * (n-oddy) + (m-oddx)*oddy
     * <p>
     * 时间 O(q + m + n)
     * 空间 O(m + n)
     * <p>
     * 反过来求偶数也可以用，考虑偶数只能是两偶数相加才能成功即可
     *
     * 容斥原理
     * @param m
     * @param n
     * @param indices
     * @return
     */
    public int oddCells3(int m, int n, int[][] indices) {
        int[] row = new int[m];
        int[] col = new int[n];
        for (int[] operation : indices) {
            row[operation[0]]++;//行++
            col[operation[1]]++;//列++
        }

        int oddx = 0, oddy = 0;
        for (int i = 0; i < m; i++) {
            if ((row[i] & 1) == 1) {
                oddx++;
            }
        }
        for (int i = 0; i < n; i++) {
            if ((col[i] & 1) == 1) {
                oddy++;
            }
        }
        return oddx * (n - oddy) + (m - oddx) * oddy;
    }
}
