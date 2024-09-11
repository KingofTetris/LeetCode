package LeetCode数据结构与算法基础.DFS与BFS;

/**
 * @author by KingOfTetris
 * @date 2024/8/18
 */

import java.util.*;

public class 岛屿数量BFS1 {

    //BFS版本，一样都是定义好四个方向
    //但是BFS需要一个队列

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static boolean[][] visited;

    //BFS就不是递归了，而是用队列去取代递归。
    //只要还能向外BFS，那么queue就不为空，如果queue为空了，那么说明已经找到一块单独的大陆了。
    public static void bfs(int[][] grid, int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        //将x,y入队
        queue.offer(new int[]{x, y});
        visited[x][y] = true; // 只要加入队列，立刻标记
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length)
                    continue;  // 越界了，直接跳过
                if (!visited[nextX][nextY] && grid[nextX][nextY] == 1) {
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true; // 只要加入队列立刻标记
                }
            }
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
        //只要是DFS,BFS就离不开这个visited数组
        //其实也不是离不开，最大人工岛不就没有吗，其实只要你能标记哪些走过了就行了。
        visited = new boolean[n][m];

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //BFS也是一样，有没访问的新大陆再+1
                if (!visited[i][j] && grid[i][j] == 1) {
                    result++; // 遇到没访问过的陆地，+1
                    bfs(grid, i, j); // 将与其链接的陆地都标记上 true
                }
            }
        }

        System.out.println(result);
    }

}
