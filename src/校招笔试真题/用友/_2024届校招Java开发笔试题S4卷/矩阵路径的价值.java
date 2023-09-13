package 校招笔试真题.用友._2024届校招Java开发笔试题S4卷;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/9/4
 */

//从矩阵的左上走到右下
//只能向下或者向右走
//请求返回路径的最大和
public class 矩阵路径的价值 {
    //其实就是动态规划
    //MD 那个时候没看。真JB傻逼。
    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3,9},
                {4,5,6,5},
                {7,8,9,4}
        };
        solution(matrix);
    }

    //dp[i][j]表示从0,0出发能获取的最大价值
    //那么dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]) + matrix[i][j];
    private static void solution(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = 0;
        //初始化边界
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i-1] + matrix[0][i] ;
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] + matrix[i][0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        System.out.println(dp[n - 1][m - 1]);
    }
}
