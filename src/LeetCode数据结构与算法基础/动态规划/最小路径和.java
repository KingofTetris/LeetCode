package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/10/30
 */
public class 最小路径和 {


    @Test
    public void test(){
        int[][] grid = {
                {1,2,3},
                {4,5,6}
        };
        int minPathSum = minPathSum(grid);
        System.out.println(minPathSum);
    }
    //从左上到右下找出 路径之和最短的路径
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        //那么初始状态是什么?
        //第一行的值等于从左到右累加
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += grid[0][i];
            dp[0][i] = sum;
        }
        //第一列也是一样，从上到下累加
        sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[i][0];
            dp[i][0] = sum;
        }

        //然后递推DP
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                //要么是从上面来 就是dp[i-1][j] + 当前值
                //要么是从左右过来，就是dp[i][j-1]
                dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j],
                        dp[i][j - 1] + grid[i][j]);
            }
        }

        return dp[n - 1][m - 1];
    }

}
