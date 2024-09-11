package LeetCode数据结构与算法基础.DFS与BFS;

/**
 * @author by KingOfTetris
 * @date 2024/8/18
 */

import java.util.Scanner;

public class 岛屿数量DFS {
    //左右上下顺序随便无所谓。
//    static int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    //分成两个一维数组也可以。无所谓。
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = in.nextInt();
            }
        }
        visited = new boolean[n][m];

        int result = 0;

        //
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //没走过，且是陆地
                if (!visited[i][j] && grid[i][j] == 1) {
//                    visited[i][j] = true; 这句话其实无所谓，反正DFS的逻辑都会把要走的路径标记为true
                    result++; // 遇到没访问过的陆地，+1
                    dfs(grid, i, j); // 然后从i,j出发把这一块岛屿的所有visited标记为true
                }
                //然后循环去找新大陆。
            }
        }
        System.out.println(result);
    }


    public static void dfs(int[][] grid, int x, int y) {
        //从(x,y)开始向外深搜。
        for (int i = 0; i < 4; i++) {
            //四个方向向外探索
            int nextx = x + dx[i];
            int nexty = y + dy[i];
            if (nextx < 0
                    || nextx >= grid.length
                    || nexty < 0
                    || nexty >= grid[0].length) continue;  // 越界了，直接跳过

            // 没有访问过的 同时 陆地的
            // 说明是同一块陆地
            if (!visited[nextx][nexty] && grid[nextx][nexty] == 1) {
                visited[nextx][nexty] = true;
                //继续
                dfs(grid, nextx, nexty);
            }
        }
    }


}
