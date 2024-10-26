package LeetCode数据结构与算法基础.day1.数组;

/**
 * @author by KingOfTetris
 * @date 2023/9/1
 */


import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */

public class 螺旋矩阵I {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> list = spiralOrder(matrix);
        System.out.println(list);
    }
    //每遍历完一圈就去掉一层。
    //比如遍历完left->right 就把top++;
    //遍历完top->bottom 就把right--;
    //遍历完right->left 就把bottom--;
    //遍历完bottom->top 就把left++;
    //如果是逆时针的话也是一样，只不过++ --的顺序要调整一下而已。
    public static List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        List<Integer> list = new ArrayList<>();
        int num = 0;
        //定义上下左右
        int top = 0, bottom = n - 1,left = 0, right = m - 1;
        //如果num == n * m 就结束了，可以跳出了。
        while (true) {
            //注意每次i的开头赋值。
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
                num++;
            }
            if (num == n * m) break;
            top++;
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);
                num++;
            }
            right--;
            if (num == n * m) break;
            for (int i = right; i >= left; i--) {
                list.add(matrix[bottom][i]);
                num++;
            }
            bottom--;
            if (num == n * m) break;
            for (int i = bottom; i >= top; i--) {
                list.add(matrix[i][left]);
                num++;
            }
            left++;
            if (num == n * m) break;
        }
        return list;
    }
}
