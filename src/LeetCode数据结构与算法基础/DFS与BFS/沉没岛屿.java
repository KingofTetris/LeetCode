package LeetCode数据结构与算法基础.DFS与BFS;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/11
 */

//和孤岛总面积反过来，现在你需要把所有的孤岛沉默掉换成0。
public class 沉没岛屿 {


    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        //先把非孤岛的部分给他标成2
        //然后把剩下的1全部改0，再把2改成1就可以了。

        //从左右两侧去dfs
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) dfs(grid, i, 0);
            if (grid[i][m - 1] == 1) dfs(grid, i, m - 1);
        }
        //从上下两侧去dfs
        for (int i = 0; i < m; i++) {
            if (grid[0][i] == 1) dfs(grid, 0, i);
            if (grid[n - 1][i] == 1) dfs(grid, n - 1, i);
        }

        //现在开始把1去掉,把2改回1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1){
                    grid[i][j] = 0;
                }
                if (grid[i][j] == 2){
                    grid[i][j] = 1;
                }
            }
        }

        for (int[] row : grid) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void dfs(int[][] grid, int i, int j) {
        grid[i][j] = 2;//改成2
        int n = grid.length;
        int m = grid[0].length;
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x < 0 || x >= n || y < 0 || y >= m){
                continue;
            }
            //如果还是陆地，就继续扩展
            if (grid[x][y] == 1){
                dfs(grid,x,y);
            }
        }
    }


}
