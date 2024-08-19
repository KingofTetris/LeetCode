package LeetCode数据结构与算法基础.DFS与BFS;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author KingofTetris
 * @File 岛屿的最大面积
 * @Time 2021/9/25  14:43
 */
/*695. 岛屿的最大面积
        给你一个大小为 m x n 的二进制矩阵 grid 。
        岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在
         水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
        岛屿的面积是岛上值为 1 的单元格的数目。
        计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
        示例 1：
        输入：grid=[ [0,0,1,0,0,0,0,1,0,0,0,0,0],
                    [0,0,0,0,0,0,0,1,1,1,0,0,0],
                    [0,1,1,0,1,0,0,0,0,0,0,0,0],
                    [0,1,0,0,1,1,0,0,1,0,1,0,0],
                    [0,1,0,0,1,1,0,0,1,1,1,0,0],
                    [0,0,0,0,0,0,0,0,0,0,1,0,0],
                    [0,0,0,0,0,0,0,1,1,1,0,0,0],
                    [0,0,0,0,0,0,0,1,1,0,0,0,0]]
        输出：6
        解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
        示例 2：

        输入：grid = [[0,0,0,0,0,0,0,0]]
        输出：0


        提示：

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 50
        grid[i][j] 为 0 或 1*/

//最方便的方法是把访问过的土地直接置为0，为0就不进行DFS
//但是对原数组进行了修改不是很好。
//可以加个visited[][]数组，把访问过的1直接添加进去 赋值为1表示已经访问过
//这个visited数组只能按题目 限制直接给出，不然没法赋值；1 <= m, n <= 50
//不过这种方法在不改变原数组的情况下，时空效率都很低
public class 岛屿的最大面积 {

    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int count = 0;
    public static void dfs(int[][] grid, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) {
                continue;//越界了，换个方向
            }
            //没有越界就判断
            if (!visited[nextX][nextY] && grid[nextX][nextY] == 1) {
                visited[nextX][nextY] = true;
                count++;
                dfs(grid, nextX, nextY);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = in.nextInt();
            }
        }
        visited = new boolean[n][m];

        //记录岛屿的最大面积
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    count = 1; //新大陆count重新置1
                    visited[i][j] = true;//标记访问
                    dfs(grid, i, j);
                    maxArea = Math.max(maxArea,count);
                }
            }
        }

        System.out.println(maxArea);
    }
}
