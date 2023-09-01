package LeetCode数据结构入门.day1.数组;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author KingofTetris
 * @File 重塑矩阵
 * @Time 2021/9/30  20:04
 */

/*566. 重塑矩阵
        在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同
        （r x c）的新矩阵，但保留其原始数据。

        给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。

        重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。

        如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。*/
public class 重塑矩阵 {
    @Test
    public void test(){
        //4*3
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
        int[][] newMatrix = matrixReshape(matrix,3,4);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            if (i != matrix.length - 1)
                System.out.println();
        }

        System.out.println();
        System.out.println();

        for (int i = 0; i < newMatrix.length; i++) {
            for (int j = 0; j <newMatrix[0].length; j++) {
                System.out.print(newMatrix[i][j] + "\t");
            }
            if (i != newMatrix.length - 1)
                System.out.println();
        }
    }
    //相当于实现numpy里面的reshape方法 改变mat的行列为 r*c
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int n,m;
        n = mat.length;
        m = mat[0].length;
        if(r * c != n * m)
            return mat; //如果根本reshape不了，直接返回mat
        int[][] newMatrix = new int[r][c];
        int newRow = 0,newColumn = 0;
        //按行遍历原数组 进行填充
        //新数组的下标最大行是r-1 最大列是c-1
        //横着往每列里面填数 当newColumn = c - 1时 就可以换下一行了 记得重置newColumn为0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMatrix[newRow][newColumn]  = mat[i][j];
                if(newColumn < c - 1){
                    newColumn++;
                }
                else{
                    newRow++;
                    newColumn=0;
                }
            }
        }
        return newMatrix;
    }
}
