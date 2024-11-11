package LeetCode数据结构与算法基础.DFS与BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class 走马 {

    //计算一匹马从起点(0,0)出发到达矩阵中每个点的最少步数
    public static void main(String[] args) {
        int n = 8; // 行数
        int m = 8; // 列数

        int[][] minSteps = calculateMinSteps(n, m);

        // 打印到达每个点的最少步数矩阵
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(minSteps[i]));
        }
    }


    //简单的BFS
    public static int[][] calculateMinSteps(int n, int m) {
        int[][] minSteps = new int[n][m];
        int[][] directions = {
                //还是八个方向
                {-2, -1}, {-2, 1}, {2, -1}, {2, 1},
                {-1, -2}, {1, -2}, {-1, 2}, {1, 2}};

        // 初始化minSteps矩阵为-1，表示未访问过
        for (int i = 0; i < n; i++) {
            Arrays.fill(minSteps[i], -1);
        }

        minSteps[0][0] = 0; //起点为0。
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            //取出当前位置
            int[] currPos = queue.poll();
            int x = currPos[0];
            int y = currPos[1];
            //遍历8个方向
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                //只要合法且没遍历过
                if (newX >= 0 && newX < n && newY >= 0 && newY < m && minSteps[newX][newY] == -1) {
                    //+1
                    minSteps[newX][newY] = minSteps[x][y] + 1;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }

        return minSteps;
    }
}
