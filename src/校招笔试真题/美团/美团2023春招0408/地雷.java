package 校招笔试真题.美团.美团2023春招0408;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/4/11
 * 题目描述：
 * 有一片 n × m 大小的网格，共 n 行 m 列，
 * 其中行和列都用从 1 开始的整数编号，网格中有 k 个格子中埋有地雷。
 * 我们记第 a 行第 b 列的格子为 (a, b)。小美现在位于 (x1, y1)，她想要移动到 (x2, y2) 处。
 * 小美每次移动可以移动到与她所处格子的相邻的一格中，形式化地说，如果小美位于 (x, y)，
 * 则小美可以移动到 (x-1, y), (x+1, y), (x, y-1), (x, y+1) 的格子之一，但小美不能移动到网格之外。

 * 小美想要在移动过程中，离这些地雷越远越好，而不是走最短路径。这里定义两个格子之间的距离为曼哈顿距离，
 * 即格子 (a, b) 和 (c, d) 之间的距离是 |a-c|+|b-d|。小美想知道，
 * 移动中与地雷之间距离的最小值中最大的可能是多少。请注意，如果无论小美如何移动，
 * 都会进入一个有地雷的格子的话，这个最大可能值为 0。

 * 输入描述
 * 第一行三个整数 n, m, k，分别表示网格的行数，列数和地雷个数。
 * 接下来 k 行，每行两个整数 p, q，表示一个地雷放置在格子 (p, q) 中。任意两地雷的放置位置不同。

 * 接下来一行四个整数 x1, y1, x2, y2，表示小美的出发位置和目的位置。保证小美的出发位置和目的位置上没有地雷。

 * 对于全部数据，1 ≤ n, m ≤ 500, n × m ≥ 3, 1 ≤ k ≤ min{n × m-2, 400},
 * 1 ≤ p, x1, x2 ≤ n, 1 ≤ q, y1, y2 ≤ m, (x1, y1) ≠ (x2, y2)，
 * 保证 (x1, y1) 和 (x2, y2) 中没有地雷，并且一个格子中最多放置一个地雷。

 * 输出描述
 * 输出一行一个整数，表示移动过程中与地雷之间距离的最小值的可能最大值。
 * <p>
 * 样例输入
 * 5 6 2
 * 2 1
 * 2 3
 * 1 1 5 1
 * 样例输出
 * 1
 */

public class 地雷 {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        int[] ints = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = ints[0], m = ints[1], k = ints[2];
        List<int[]> potatos = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            potatos.add(a);
        }
        int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] begin = new int[2];
        begin[0] = a[0];
        begin[1] = a[1];
        int[] end = new int[2];
        end[0] = a[2];
        end[1] = a[3];


        int max = Integer.MAX_VALUE;

        //找到那个距离起点或者终点最近的地雷
        for (int[] potato : potatos) {
            int x = potato[0];
            int y = potato[1];
            max = Math.min(Math.abs(x - begin[0]) + Math.abs(y - begin[1]), max);
            max = Math.min(Math.abs(x - end[0]) + Math.abs(y - end[1]), max);
        }



        //为什么要二分?
        int l = 0;
        int r = max; //把max当上界

        int ans = 0;

        //二分循环，它的目的是找到移动过程中当前位置与地雷之间距离的最小值的可能最大值。
        //代码会调用 check 函数，检查移动过程中与这些地雷中的任意一个之间距离的最小值是否能够达到 mid。
        // 如果能达到，就将 ans 更新为 mid，然后向上调整下界；
        // 如果不能达到，就向下调整上界。
        while (l <= r) {
            int mid = (l + r) >> 1;
            boolean[][] vis = new boolean[m][n];
            if (check(mid, begin, end, vis, potatos)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(ans);

    }


    //这道题BFS一个用例过不了，其他都过得了。用 dijkstra可以过
    private static boolean check(int mid, int[] begin, int[] end, boolean[][] vis, List<int[]> potatos) {
        //四个方向
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int m = vis.length;
        int n = vis[0].length;

        //BFS一般需要一个队列来辅助
        //这里队列里放的是当前节点的坐标以及当前节点到终点的曼哈顿距离。
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{begin[0], begin[1], Math.abs(begin[0] - end[0]) + Math.abs(begin[1] - end[1])});
        vis[begin[0]][begin[1]] = true;//标记为访问
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            //如果达到终点 返回true
            if (x == end[0] && y == end[1]) {
                return true;
            }
            //接下来去找四个邻居坐标，哪个离这些地雷最近
            //如果最近距离大于等于mid
            //那么就从(x1,y1)这个坐标继续找下去，直到返回true或者false
            for (int i = 0; i < 4; i++) {
                int x1 = x + dx[i];
                int y1 = y + dy[i];

                //坐标合法且未访问
                if (x1 >= 0 && y1 >= 0 && x1 < m && y1 < n && !vis[x1][y1]) {
                    int max = Integer.MAX_VALUE;
                    for (int[] potato : potatos) {
                        //计算当前位置和这些地雷之间的距离
                        //找到当前位置与最近的那个地雷之间有多远
                        max = Math.min(Math.abs(x1 - potato[0]) + Math.abs(y1 - potato[1]), max);
                    }

                    //如果这个最小距离大于等于mid。
                    //那么沿着这个方向走，就会离土豆们越来越远。
                    //把x1,y1放入queue
                    if (max >= mid) {
                        vis[x1][y1] = true;
                        queue.add(new int[]{x1, y1, Math.abs(x1 - end[0]) + Math.abs(y1 - end[1])});
                    }
                }
            }

        }
        return false;
    }
}

