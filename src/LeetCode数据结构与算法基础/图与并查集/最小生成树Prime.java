package LeetCode数据结构与算法基础.图与并查集;

import java.util.*;

public class 最小生成树Prime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int e = scanner.nextInt();

        // 初始化邻接矩阵，所有值初始化为一个大值，表示无穷大
        int[][] grid = new int[v + 1][v + 1];
        for (int i = 0; i <= v; i++) {
            Arrays.fill(grid[i], 10001);
        }

        // 读取边的信息并填充邻接矩阵
        for (int i = 0; i < e; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int k = scanner.nextInt();
            grid[x][y] = k;
            grid[y][x] = k;
        }

        // 所有节点到最小生成树的最小距离
        int[] minDist = new int[v + 1];
        Arrays.fill(minDist, 10001);

        // 记录节点是否在树里
        boolean[] isInTree = new boolean[v + 1];

        // Prim算法主循环
        for (int i = 1; i < v; i++) {
            int cur = -1;
            int minVal = Integer.MAX_VALUE;

            // 选择距离生成树最近的节点
            for (int j = 1; j <= v; j++) {
                if (!isInTree[j] && minDist[j] < minVal) {
                    minVal = minDist[j];
                    cur = j;
                }
            }

            // 将最近的节点加入生成树
            isInTree[cur] = true;

            // 更新非生成树节点到生成树的距离
            for (int j = 1; j <= v; j++) {
                if (!isInTree[j] && grid[cur][j] < minDist[j]) {
                    minDist[j] = grid[cur][j];
                }
            }
        }

        // 统计结果
        int result = 0;
        for (int i = 2; i <= v; i++) {
            result += minDist[i];
        }
        System.out.println(result);
        scanner.close();
    }
}
