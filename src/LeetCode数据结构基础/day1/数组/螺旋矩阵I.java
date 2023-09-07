package LeetCode数据结构基础.day1.数组;

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
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> list = spiralOrder(matrix);
        System.out.println(list);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        List<Integer> list = new ArrayList<>();
        int num = 0;
        //定义左右上下
        int left = 0,right = m - 1,top = 0,bottom = n - 1;
        //如果num == n * m 就跳出。
        while (true){
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
                num++;
            }
            if (num == n * m ) break;
            top++;
            for (int i = top; i <= bottom; i++) {
                list.add(matrix[i][right]);
                num++;
            }
            right--;
            if (num == n * m ) break;
            for (int i = right; i >= left; i--){
                list.add(matrix[bottom][i]);
                num++;
            }
            bottom--;
            if (num == n * m ) break;
            for (int i = bottom; i >= top; i--){
                list.add(matrix[i][left]);
                num++;
            }
            left++;
            if (num == n * m ) break;
        }
        return list;
    }
}
