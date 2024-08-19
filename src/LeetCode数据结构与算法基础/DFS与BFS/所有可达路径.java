package LeetCode数据结构与算法基础.DFS与BFS;

/**
 * @author by KingOfTetris
 * @date 2024/8/18
 */

import java.util.*;

public class 所有可达路径 {
    private static List<List<Integer>> res = new ArrayList<>();
    private static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        //邻接表也行，邻接矩阵也行
        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int s = sc.nextInt();
            int t = sc.nextInt();
            graph[s].add(t);
        }
        path.add(1);
        dfs(graph, 1, N);
        if (res.size() == 0) {
            System.out.println(-1);
        } else {
            for (List list : res) {
                for (int i = 0; i < list.size() - 1; i++) {
                    System.out.print(list.get(i) + " ");
                }
                System.out.println(list.get(list.size() - 1));
            }
        }
    }

    public static void dfs(List<Integer>[] graph, int x, int n) {
        //终止条件，找到了x->n
        if (x == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        //单层循环
        //x->t->n
        //回溯模板
        for (int i = 0; i < graph[x].size(); i++) {
            int t = graph[x].get(i);
            path.add(t);
            dfs(graph, t, n);
            path.remove(path.size() - 1);
        }
    }
}
