package 剑指offer第二版.数组_矩阵;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author KingofTetris
 * @Date 2022/7/15 16:08
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，
 * 因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 * <p>
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer13_机器人的运动范围 {


    @Test
    public void test() {
        int m = 3, n = 3, k = 3;
        System.out.println(movingCount(m, n, k));
    }

    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        //参数：visited数组、m、n、k、当前所处格子，只往右边或者下边走就够了
        return dfs(visited, m, n, k, 0, 0);
    }

    public int dfs(boolean[][] visited, int m, int n, int k, int i, int j) {
        //visited为true就走过了，终止条件
        if (i >= m || j >= n || sum(i, j) > k || visited[i][j]) return 0;
        visited[i][j] = true; //可以走就标记为true 表示走过了
        //每成功递归一次就+1个位置。如果一次都不成功就是1，因为至少有0，0 是可以走的
        //先往下再往右
        return 1 + dfs(visited, m, n, k, i + 1, j) + dfs(visited, m, n, k, i, j + 1);
    }

    /**
     * 因为限制了范围，可以直接用除法算
     * 如果是很大的数，可能就需要转化为字符串
     *
     * @param i
     * @param j
     * @return
     */
    public int sum(int i, int j) {
        /**
         * 思路很简单就是每次取出个位后，除以10
         */
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j != 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum;
    }
}
