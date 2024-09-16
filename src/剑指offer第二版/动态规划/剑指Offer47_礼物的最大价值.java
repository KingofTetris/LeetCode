package 剑指offer第二版.动态规划;

import java.util.Arrays;

/**
 * @Author KingofTetris
 * @Date 2022/9/2 10:47
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *  
 *
 * 提示：
 *
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

//用友SP 第二题! 哎。做了等于白做。！早点复习算法啊！ 20230911?
    //这么简单当初都不会做吗?有点搞笑。 20240916，今天再看，根本是送分。。
public class 剑指Offer47_礼物的最大价值 {

    public static void main(String[] args) {
        int[][] nums = {
                {1,3,1},
                {4,3,1},
                {4,2,1}
        };
        System.out.println(maxValue(nums));
    }
    /**
     * 从左上角到右下角，每次向右或向下，找到一条最大的路径
     * 设f(i,j) 是达到单元(i,j)的最大值
     * 那么由于只能向右向下走就有
     * f(i,j) = max[f(i,j-1) //左边来的 + f(i - 1,j)//上面来的] + grid[i][j]
     * 反过来从右下角到左上角求最大值也是一样。因为是完全对称的，都不用改代码结果也一样。
     * @param grid
     * @return
     */
    public static int maxValue(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        //dp[i][j]表示从grid[0][0]到grid[i - 1][j - 1]时的最大价值
        //dp[i]初始值都是0
        int[][] dp = new int[row + 1][column + 1];//把dp矩阵扩大多一行，多一列 那么左边界和上边界都是0，加上也不影响。
        // 就不用费力就关心越界问题了
        /**
         * 从1出发。可以解决从0出发的边界问题
         * 注意这个从1开始遍历，如果你是从0开始的话，那么i-1,j-1就会越界。
         * 这也是上面row+1,col +1 的好处！
         */
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                //比较两个方向哪个更大，加上自身的值就行了。
                dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1])
                        + grid[i - 1][j - 1];//加grid[i - 1][j - 1]的值
            }
        }
        for (int[] rowT : dp) {
            System.out.println(Arrays.toString(rowT));
        }
        return dp[row][column];//直接返回dp[n][m]即可
    }

}
