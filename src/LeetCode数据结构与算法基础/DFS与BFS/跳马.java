package LeetCode数据结构与算法基础.DFS与BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * 马是象棋(包括 中国象棋Q和国际象棋)中的棋子，
 * 走法是每步直一格再斜一格，即先横着或者直者走一格，然后再斜着走一个对角线，
 * 可进可退，可越过河界，俗称"马走日"字。
 * 给定 m行n列的棋盘(网格图)，棋盘上只有棋子象棋中的棋子“马”，
 * 并且每个棋子有等级之分，等级为k的马可以跳 1-k步(走的方式与象棋中“马”的规则一样，不可以超出棋盘位置)，
 * 问是否能将所有马跳到同一位置，如果存在，输出最少需要的总步数(每匹马的步数相加)，不存在则输出-1。
 * 注:
 * 允许不同的马在跳的过程中跳到同一位置，
 * 坐标为(x,y)的马跳一次可以跳到的坐标为:
 * (x+1,y+2)，(x+1,y-2)，(x+2,y+1)，(x+2,y-1)，
 * (x-1,y+2)，(x-1,y-2)，(x-2,y+1)，(x-2,y-1)，
 * 的格点上，但是不可以超出棋盘范围。
 *
 */
public class 跳马 {
    // 棋盘行数
    static int m;
    // 棋盘列数
    static int n;
    // 棋盘矩阵
    static char[][] map;
    // 最小步数和矩阵，stepMap[i][j]记录各个马走到棋盘(i,j)位置的最小步数之和
    static int[][] stepMap;
    // 记录所有马都可达的公共位置坐标
    static HashSet<Integer> reach;
    // 马走日的偏移量
    static int[][] offsets = {
            {1, 2}, {1, -2}, {2, 1}, {2, -1},
            {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        map = new char[m][n];
        stepMap = new int[m][n];
        reach = new HashSet<>();
        for (int i = 0; i < m; i++) {
            map[i] = sc.next().toCharArray();
            // 初始时假设所有位置都是各个马可达的
            for (int j = 0; j < n; j++) {
                reach.add(i * n + j);
            }
        }
        System.out.println(getResult());
    }

    public static int getResult() {
        // 遍历棋盘
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果棋盘(i,j)位置是马
                if (map[i][j] != '.') {
                    // 马的等级
                    int k = map[i][j] - '0';
                    // 对该马进行BFS走日
                    bfs(i, j, k);
                }
            }
        }

        // 如果所有马走完，发现没有公共可达位置
        if (reach.size() == 0) {
            return -1;
        }

        // 记录所有马都可达位置的最小步数和
        int minStep = Integer.MAX_VALUE;

        for (int pos : reach) {
            int x = pos / n;
            int y = pos % n;
            // (x,y)是所有马都可达的位置，stepMap[x][y]记录所有马到达此位置的步数和
            minStep = Math.min(minStep, stepMap[x][y]);
        }

        return minStep;
    }

    // 广搜
    public static void bfs(int sx, int sy, int k) {
        // 广搜队列
        LinkedList<int[]> queue = new LinkedList<>();
        // (sx,sy)为马所在初始位置，马到达初始位置需要0步
        queue.add(new int[]{sx, sy, 0});

        // 记录该马可以访问(sx,sy)位置
        HashSet<Integer> vis = new HashSet<>();
        vis.add(sx * n + sy); // 二维坐标一维化

        // k记录该马剩余可走步数
        while (queue.size() > 0 && k > 0) {
            // newQueue记录该马花费相同步数的可达的位置（即BFS按层遍历的层）
            LinkedList<int[]> newQueue = new LinkedList<>();

            // 按层BFS
            for (int[] tmp : queue) {
                // 当前马所在位置(x,y)，以及马到达该位置的步数step
                int x = tmp[0];
                int y = tmp[1];
                int step = tmp[2];

                for (int[] offset : offsets) {
                    // 马走日到达的新位置
                    int newX = x + offset[0];
                    int newY = y + offset[1];

                    int pos = newX * n + newY;

                    // 如果新位置越界或者已访问过，则不能访问
                    if (newX < 0 || newX >= m || newY < 0 || newY >= n || vis.contains(pos)) continue;

                    // 将新位置加入新层
                    newQueue.add(new int[]{newX, newY, step + 1});
                    // 该马到达(newX, newY)位置最小步数为step+1, 由于该马首次到达(newX, newY)位置，因此step+1就是最小步数
                    stepMap[newX][newY] += step + 1;
                    // 记录该马访问过该位置，后续如果该马再次访问该位置，则不是最小步数
                    vis.add(pos);
                }
            }

            queue = newQueue;
            k--; // 剩余步数减1
        }

        // BFS完后，将公共可达位置reach和当前马可达位置取交集，交集部分就是新的公共可达位置
        reach.retainAll(vis);
    }
}
