package LeetCode数据结构与算法基础.DFS与BFS;

/**
 * @author by KingOfTetris
 * @date 2024/9/12
 */

//求出所有岛屿的周长之和

import java.util.Scanner;

/**
 * 岛屿问题最容易让人想到BFS或者DFS，但本题确实还用不上。
 * <p>
 * 为了避免大家惯性思维，所以给大家安排了这道题目。
 */
public class 岛屿的周长 {

    /**
     * 计算出总的岛屿数量，总的变数为：岛屿数量 * 4
     * <p>
     * 因为有一对相邻两个陆地，边的总数就要减2，如图红线部分，有两个陆地相邻，总边数就要减2
     */
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


        int sum = 0;    // 陆地数量
        int cover = 0;  // 陆地相邻数量
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    sum++; // 统计总的陆地数量
                    // 统计上边相邻陆地
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) cover++;
                    // 统计左边相邻陆地
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) cover++;
                    // 为什么没统计下边和右边？ 因为避免重复计算
                    // 因为遍历顺序是从左往右从上到下。
                    //只需要计算上边和左边有没有相邻块就行了。
                }
            }
        }

        System.out.println(sum * 4 - 2 * cover);
    }
}
