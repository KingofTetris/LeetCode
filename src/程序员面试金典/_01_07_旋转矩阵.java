package 程序员面试金典;

/**
 * @Author KingofTetris
 * @Date 2022/9/27 16:14
 */
public class _01_07_旋转矩阵 {


    /**
     * 和矩阵的转置还不太一样
     *
     * 第 1 行 变成 n 列
     * 第 2 行 变成 n - 1列
     * .
     * .
     * .
     * .
     * 第 n 行 变成 1 列
     * 对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在倒数第 i 列的第 j 个位置。
     *
     * 我们将其翻译成代码。
     * 由于矩阵中的行列从 0 开始计数，因此对于矩阵中的元素 matrix[row][col]，
     * 在旋转后，它的新位置为 matrix[col][n - row - 1]
     * @param matrix
     */

    //直接用辅助空间来交换
    public void rotate(int[][] matrix) {

        int n = matrix.length;
        int[][] matrix_new = new int[n][n]; //辅助矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix_new[j][n - i - 1] = matrix[i][j]; //找到这个关系 旋转后 row_new = col ,col_new = n - row - 1;
            }
        }

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }

    //先水平翻转再沿着主对角线翻转
    //能节省O(n^2)的空间
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

       /* 作者：LeetCode-Solution
        链接：https://leetcode.cn/problems/rotate-matrix-lcci/solution/xuan-zhuan-ju-zhen-by-leetcode-solution/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
