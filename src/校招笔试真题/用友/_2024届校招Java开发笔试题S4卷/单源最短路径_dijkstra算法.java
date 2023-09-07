package 校招笔试真题.用友._2024届校招Java开发笔试题S4卷;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/9/5
 */
import java.util.*;
//其实迪杰斯特拉是BFS+贪心，不是DFS。
public class 单源最短路径_dijkstra算法 {
    public static int[] shortestPath(List<int[]> graph, int src) {
        int n = getMaxNode(graph) + 1; // 获取节点数量,假设节点编号是从0到n-1
        int[] dist = new int[n]; // 用于存储从源点到各个节点的最短距离
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0; // 源点到自身的距离为0
        //// 使用最小堆来维护距离最短的节点
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.offer(new int[]{src, 0}); // 存储源点和距离的二元组到最小堆
        while (!minHeap.isEmpty()) {
            int[] node = minHeap.poll(); // 弹出距离最短的节点
            int u = node[0]; // 当前节点的编号
            int distance = node[1]; // 当前节点到源点的距离
            // 如果当前节点的距离已经不是最小值，则忽略它
            if (distance > dist[u]) continue;
            // 遍历图中的边
            for (int[] edge : graph) {
                if (edge[0] == u && dist[u] + edge[2] < dist[edge[1]]) {
                    // 如果从源点经过u到达edge[1]的距离比当前记录的距离短，则更新距离
                    dist[edge[1]] = dist[u] + edge[2];
                    minHeap.offer(new int[]{edge[1], dist[edge[1]]}); // 更新最小堆
                } else if (edge[1] == u && dist[u] + edge[2] < dist[edge[0]]) {
                    // 如果从源点经过u到达edge[0]的距离比当前记录的距离短，则更新距离
                    dist[edge[0]] = dist[u] + edge[2];
                    minHeap.offer(new int[]{edge[0], dist[edge[0]]}); // 更新最小堆
                }
            }
        }
        return dist;
    }

    // 获取图中的最大节点值
    private static int getMaxNode(List<int[]> graph) {
        int maxNode = -1;
        for (int[] edge : graph) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }
        return maxNode;
    }

    public static void main(String[] args) {
        List<int[]> graph = new ArrayList<>();
        graph.add(new int[]{0, 1, 100});
        graph.add(new int[]{0, 2, 500});
        graph.add(new int[]{1, 2, 100});
        graph.add(new int[]{2, 3, 200});
        graph.add(new int[]{1, 4, 300});
        graph.add(new int[]{0, 4, 900});
        int src = 0;
        int[] result = shortestPath(graph, src);
        System.out.println(Arrays.toString(result));
    }
}






