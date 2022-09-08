package LeetCode数据结构入门.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 矩阵置零
 * @Time 2021/10/1  21:24
 */

/*73. 矩阵置零
        给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

        进阶：

        一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
        一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
        你能想出一个仅使用常量空间的解决方案吗？*/

public class 矩阵置零 {

    @Test
    public void test(){
        int[][] matrix = {{1,2,3},
                      {2,3,0},
                      {3,1,0}};
        setZeroes(matrix);

        int n = matrix.length;
        int m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }

    }



    //用两个标记数组来标记每一行每一列是否有零出现
    //然后把出现0的行列置为0即可
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] hang = new int[n];
        int[] lie = new int[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] == 0) {
                    hang[i] = 1;
                    lie[j] = 1;
                }
            }
        }



        //开始置零
        for (int i = 0; i < n; i++) {
            if(hang[i] == 1){
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < m; j++) {
            if(lie[j] == 1){
                for (int i = 0; i < n; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

    }
}
