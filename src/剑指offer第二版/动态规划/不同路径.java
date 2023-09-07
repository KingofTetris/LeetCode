package 剑指offer第二版.动态规划;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */

//一个n*m的矩阵，机器人位于左上角，现在
//机器人每次只能往下或者往右，要到达右下角，请问有多少种不同的路径?
public class 不同路径 {
    public static void main(String[] args) {
        System.out.println(solution(3, 3));
    }

    //可以深搜，因为每一步都有两种选择，就是一颗二叉树的遍历。
//    这棵树的深度其实就是m+n-1（深度按从1开始计算）。
//    那二叉树的节点个数就是 2^(m + n - 1) - 1。
//    可以理解深搜的算法就是遍历了整个满二叉树（其实没有遍历整个满二叉树，只是近似而已）
    //这是指数级别的时间复杂度，是非常大的

    //本题考虑DP
    public static int solution(int n, int m) {
        //dp应该代表什么呢?
        //dp[i][j] 代表到达i,j 的方案数量
        //那么dp[i][j] = ?
        //因为只有向下向右两种方案。那么
        // dp[i][j] = dp[i - 1][j]  + dp[i][j - 1]
        //dp[i - 1][j]从上边下来, dp[i][j - 1]从左边过来。
        // dp[i][j] 就是这两种方案之和
        // dp[i][j]的初始值应该设为多少?
        int[][] dp = new int[n + 1][m + 1];
        //初始值设为1，表示到1,1这个点只有一种方式
        //最上面和最左边的方案都是1
        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1;
        }
        for (int i = 1; i <= m; i++) {
            dp[1][i] = 1;
        }
        //然后从2,2开始计算
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }
        return dp[n][m];
    }
}
