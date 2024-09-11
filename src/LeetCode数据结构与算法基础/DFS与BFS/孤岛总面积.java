package LeetCode数据结构与算法基础.DFS与BFS;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/11
 */


/**
 * 孤岛是指的是那些四面环水的岛屿。
 *
 * 半岛不算。
 */
public class 孤岛总面积 {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void dfs(int[][] grid, int x, int y) {
        grid[x][y] = 0;
        for (int i = 0; i < 4; i++) { // 向四个方向遍历
            int nextx = x + dx[i];
            int nexty = y + dy[i];
            // 超过边界
            if (nextx < 0 || nextx >= grid.length || nexty < 0 || nexty >= grid[0].length)
                continue;
            // 遇到海水
            if (grid[nextx][nexty] == 0) continue;
            dfs(grid, nextx, nexty);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        /**
         * 从大陆开始往里面比搜索半岛
         * 把这些半岛都标记为ocean。
         * 剩下的1就是孤岛面积了。
         */
        // 从左侧边，和右侧边 向中间遍历
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][m - 1] == 1) dfs(grid, i, m - 1);
        }
        // 从上边和下边 向中间遍历
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) dfs(grid, 0, j);
            if (grid[n - 1][j] == 1) dfs(grid, n - 1, j);
        }

        //半岛全部删掉了，剩下的1就全是孤岛。
        //这种做法不好的是会改变原来地图的信息。
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1){
                    res++;
                }
            }
        }

        System.out.println(res);
    }
}
