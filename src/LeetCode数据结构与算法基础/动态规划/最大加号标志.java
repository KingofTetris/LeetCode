package LeetCode数据结构与算法基础.动态规划;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 最大加号标志 {


    //给你一个n*n的矩阵，其元素全是1，但是现在再给你一个mines,mines指定的坐标为0
    //现在求这个矩阵中最大的 + 号是多大?

    //暴力法就是遍历每个可能组成加号的1，遍历他的四个方向去找最长连续的1的个数
    //然后求这四个方向的最小值，就是这个1能组成的最大加号的阶。

    //那么可能组成+号的1，边界上就不可能嘛，直接从1，1开始到(n-2,n-2)

    //只会暴力法。手撕这道题就真有点恶心了。
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int ans = 0, grid[][] = new int[n][n];
        for (int i = 0; i < mines.length; i++) {
            grid[mines[i][0]][mines[i][1]] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, find(grid, i, j, n));
            }
        }
        return ans;
    }

    int find(int grid[][], int x, int y, int n) {
        if (grid[x][y] == 1) {
            return 0;
        }
        int k = 1;
        while (true) {
            if (x - k < 0 || grid[x - k][y] == 1 || x + k == n || grid[x + k][y] == 1
                    || y - k < 0 || grid[x][y - k] == 1 || y + k == n || grid[x][y + k] == 1) {
                break;
            }
            k++;
        }
        return k;
    }
}
