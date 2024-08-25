package LeetCode数据结构与算法基础.DFS与BFS;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/24
 */
public class 岛屿数量_test {

    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    boolean[][] used;

    @Test
    public void test() {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '1'}
        };
        int numIslands = numIslands(grid);
        System.out.println(numIslands);
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        used = new boolean[n][m];

        int res = 0;
        for(int i = 0; i < n;i++){
            for(int j = 0;j < m;j++){
                //如果是没发现过的新大陆
                if(grid[i][j] == '1' && used[i][j] == false){
                    res++;
                    used[i][j] = true;
                    dfs(i,j,used,grid);
                }
            }
        }
        for (boolean[] booleans : used) {
            System.out.println(Arrays.toString(booleans));
        }
        return res;
    }

    public void dfs(int x,int y,boolean[][] used,char[][] grid){
        /**
         * 往四个边界去探索
         */
        int n = grid.length;
        int m = grid[0].length;
        for(int i = 0;i < 4;i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            //如果下一个探索点在界内，往下走
            if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < m){
                if(grid[nextX][nextY] == '1' && used[nextX][nextY] == false){
                    used[nextX][nextY] = true;
                    dfs(nextX,nextY,used,grid);
                }
            }
        }
    }
}
