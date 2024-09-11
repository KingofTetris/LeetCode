package LeetCode数据结构与算法基础.DFS与BFS;

import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/9/11
 */
public class 太平洋大西洋水流问题_优化DFS {

    /**
     * 两个边界分别是太平洋和大西洋，请问从地图中哪个点出发能确保
     * 既能流到太平洋又能流到大西洋。
     * 请返回这些点的集合
     * @param heights
     * @return
     */
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    //从每个点出发看他能否到达太平洋和大西洋
    //这样的复杂度是O(N*M)^2
    //leetcode上改过了，过是可以过，就是比较慢。
    public List<List<Integer>> pacificAtlantic(int[][] grid) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (canArriveTwo(grid,i,j)) {
                    LinkedList<Integer> pos = new LinkedList<>();
                    pos.add(i);
                    pos.add(j);
                    res.add(pos);
                }
            }
        }
        return res;
    }


    //判断从x,y出发能否到达两个大洋
    //也就是能否到达两个边界，到达边界，他就能下海。
    public boolean canArriveTwo(int[][] grid,int x,int y){
        int n = grid.length;
        int m = grid[0].length;

        //每次传入一个新的visited
        boolean[][] visited = new boolean[n][m];
        // 深搜，将x，y出发能到的节点都标记上。
        dfs(grid, visited, x, y);

        boolean toPacific = false;
        boolean toAtlantic = false;

        // 以下就是判断x，y出发，是否到达第一组边界和第二组边界
        // 第一边界的上边
        for (int j = 0; j < m; j++) {
            if (visited[0][j]) {
                toPacific = true;
                break;
            }
        }
        // 第一边界的左边
        for (int i = 0; i < n; i++) {
            if (visited[i][0]) {
                toPacific = true;
                break;
            }
        }
        // 第二边界下边
        for (int j = 0; j < m; j++) {
            if (visited[n - 1][j]) {
                toAtlantic = true;
                break;
            }
        }
        // 第二边界右边
        for (int i = 0; i < n; i++) {
            if (visited[i][m - 1]) {
                toAtlantic = true;
                break;
            }
        }
        //查看能否同时到达两边
        return toPacific && toAtlantic;
    }

    // 从 x，y 出发 把可以走的地方都标记上
    public void dfs(int[][] grid, boolean[][] visited, int x, int y) {
        int n = grid.length;
        int m = grid[0].length;
        if (visited[x][y]) return;
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nextx = x + dx[i];
            int nexty = y + dy[i];
            if (nextx < 0 || nextx >= n || nexty < 0 || nexty >= m) continue;
            if (grid[x][y] < grid[nextx][nexty]) continue; // 高度不合适
            dfs(grid, visited, nextx, nexty);
        }
    }
}
