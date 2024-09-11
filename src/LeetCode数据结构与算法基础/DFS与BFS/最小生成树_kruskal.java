package LeetCode数据结构与算法基础.DFS与BFS;

import java.util.*;

class Edge {
    int l, r, val;
    Edge(int l, int r, int val) {
        this.l = l;
        this.r = r;
        this.val = val;
    }
}

public class 最小生成树_kruskal {
    private static int n = 10001;
    private static int[] father = new int[n];

    // 并查集初始化
    public static void init() {
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    // 并查集的查找操作
    public static int find(int u) {
        if (u == father[u]) return u;
        return father[u] = find(father[u]);
    }

    // 并查集的加入集合
    public static void join(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return;
        father[v] = u;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int e = scanner.nextInt();
        List<Edge> edges = new ArrayList<>();
        int result_val = 0;

        for (int i = 0; i < e; i++) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            int val = scanner.nextInt();
            edges.add(new Edge(v1, v2, val));
        }

        // 执行Kruskal算法
        // 先给边从小到大排序
        edges.sort(Comparator.comparingInt(edge -> edge.val));

        // 并查集初始化
        init();

        // 从头开始遍历边
        for (Edge edge : edges) {
            int x = find(edge.l);
            int y = find(edge.r);

            if (x != y) {
                result_val += edge.val;
                join(x, y);
            }
        }
        System.out.println(result_val);
        scanner.close();
    }
}
