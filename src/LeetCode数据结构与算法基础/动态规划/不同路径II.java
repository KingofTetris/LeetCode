package LeetCode数据结构与算法基础.动态规划;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */

//还是寻路，但是网格中现在存在障碍物。
//请问现在有多少种？
public class 不同路径II {
    //障碍物用1表示
    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {1, 0, 0},
                {0, 0, 0}
        };
        System.out.println(solution(obstacleGrid));
    }


    public static int solution1(int[][] obstacleGrid) {
        /**
         * 简洁的代码，看起来舒服多了
         */
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int j = 1; j < n; ++j)
            dp[0][j] = obstacleGrid[0][j] == 1 ? 0 : dp[0][j - 1];
        for (int i = 1; i < m; ++i)
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1][0];
        for (int i = 1; i < m; ++i)
            for (int j = 1; j < n; ++j)
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
        return dp[m - 1][n - 1];
    }

    public static int solution(int[][] obstacleGrid) {
        //还是老一套，不过现在多了个条件
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        //如果起点或者终点直接就是阻碍，那根本就没必要继续走了。永远到不了
        if (obstacleGrid[n - 1][m - 1] == 1 || obstacleGrid[0][0] == 1) return 0;
        //dp[i][j] 还是代表到[i][j]有多少种方案
        int[][] dp = new int[n + 1][m + 1];
        //公式还是 dp[i][j] = dp[i-1][j] + dp[i][j-1]
        //不过现在没这么单纯
        //需要考虑矩阵自身的情况
        // dp[i][j] = (obstacleGrid[i - 1][j - 1] == 0) ? dp[i - 1][j] + dp[i][j - 1] : 0;
        //初始值就有点麻烦了。
        //如果向左或者向下的路被堵住了，那么后面的路都是0，前面是1
        for (int i = 0; i < m && obstacleGrid[0][i] == 0; i++) {
            dp[1][i + 1] = 1;
        }
        for (int i = 0; i < n && obstacleGrid[i][0] == 0; i++) {
            dp[i + 1][1] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                //如果[i][j]不是路障，那么dp[i-1][j] + dp[i][j-1]
                //如果是就为0
                dp[i][j] = (obstacleGrid[i - 1][j - 1] == 0) ? dp[i - 1][j] + dp[i][j - 1] : 0;
            }
        }

        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }

        return dp[n][m];
    }

}
