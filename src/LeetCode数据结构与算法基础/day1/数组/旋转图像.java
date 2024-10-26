package LeetCode数据结构与算法基础.day1.数组;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author KingofTetris
 * @File 旋转图像
 * @Time 2021/10/13  22:44
 */

/*  注意一定是方阵。
    给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
        每个圈都要转90度
        你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
*/

public class 旋转图像 {
    @Test
    public void test(){
//        int[][] matrix  = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix  = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //观察到其实90度旋转就是 第一行变最后一列，第二行变倒数第二列，第三行变倒数第三列，以此类推。
    //规律就是对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在倒数第 i 列的第 j 个位置。
    //利用额外空间
    public int[][] rotate1(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] rotate = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
            //这个公式自己拿个3*3的例子，推一下就出来了。
                rotate[j][n - 1 - i] = matrix[i][j];
            }
        }
        return rotate;
    }


    //原地
    //实际上顺时针90度的效果
    //和水平翻转（上下互换）完，沿着主对角线互换的效果是一样的 注意这个顺序不能颠倒。
    //具体的证明可以去看数学公式
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 上下翻转
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 -i][j];
                matrix[n - 1 -i][j] = tmp;
            }
        }
        // 左斜对角线(\)翻转
        for(int i = 0; i < n; i++) {
            // 第二层遍历终止条件为 j < i
            for(int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

    }
}
