package LeetCode数据结构与算法基础.手撕算法;

/**
 * @author by KingOfTetris
 * @date 2024/10/24
 */
public class 岛屿数量 {

    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    boolean[][] used;
    int count = 0;

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        used = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && used[i][j] == false) {
                    count++;//岛屿数量+1
                    dfs(grid, n, m, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int n, int m, int i, int j) {
        used[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            //如果非法跳过，下一个
            if (x < 0 || x >= n || y < 0 || y >= m) {
                continue;
            }
            //合法则继续尝试dfs
            if (grid[x][y] == '1' && used[x][y] == false)  dfs(grid,n,m,x,y);
        }
    }


}
