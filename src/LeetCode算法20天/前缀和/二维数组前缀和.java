package LeetCode算法20天.前缀和;

/**
 * @author by KingOfTetris
 * @date 2023/4/24
 */
public class 二维数组前缀和 {
    public static void main(String[] args) {
        int[][] matrix = {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(0,0,0,0));
    }
}
