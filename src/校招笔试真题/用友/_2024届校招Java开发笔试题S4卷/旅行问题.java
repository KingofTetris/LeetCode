package 校招笔试真题.用友._2024届校招Java开发笔试题S4卷;

import java.util.*;

public class 旅行问题 {
    public static int[] shortestPath(List<int[]> graph, int src) {
        int n = getMaxNode(graph) + 1; // 获取节点数量
        int[] dist = new int[n]; // 用于存储从源点到各个节点的最短距离
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0; // 源点到自身的距离为0
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.offer(new int[]{src, 0}); // 存储节点和距离的二元组
        while (!minHeap.isEmpty()) {
            int[] node = minHeap.poll();
            int u = node[0];
            int distance = node[1];
            // 如果当前节点的距离已经不是最小值，则忽略它
            if (distance > dist[u]) continue;
            for (int[] edge : getEdges(graph, u)) {
                int v = edge[0];
                int weight = edge[1];
                // 如果从源点经过u到v的距离比当前记录的距离短，则更新距离
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    minHeap.offer(new int[]{v, dist[v]});
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

    // 获取以节点u为起点的边的列表
    private static List<int[]> getEdges(List<int[]> graph, int u) {
        List<int[]> edges = new ArrayList<>();
        for (int[] edge : graph) {
            if (edge[0] == u) {
                edges.add(edge);
            } else if (edge[1] == u) {
                // 如果是无向图，还需要考虑目标节点是u的情况
                edges.add(new int[]{edge[1], edge[0], edge[2]});
            }
        }
        return edges;
    }

    public static void main(String[] args) {
        List<int[]> graph = new ArrayList<>();
        graph.add(new int[]{0, 1, 100});
        graph.add(new int[]{0, 2, 500});
        graph.add(new int[]{1, 2, 100});
        int src = 0;
        int[] result = shortestPath(graph, src);
        System.out.println(Arrays.toString(result));
    }
}
