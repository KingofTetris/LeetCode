package LeetCode周赛._20240804周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class 新增路径查询后的最短距离I {

    public static void main(String[] args) {
       int n = 5;
       int[][] queries = {{2,4},{0,2},{0,4}};
       新增路径查询后的最短距离I test = new 新增路径查询后的最短距离I();
        int[] result = test.shortestDistanceAfterQueries(n, queries);
        System.out.println(Arrays.toString(result));
    }
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        //邻接表，建图
        List<Integer>[] g = new ArrayList[n - 1];
        //初始化每个点的邻接表
//        Arrays.setAll(g,i -> new ArrayList<>());
        for (int i = 0; i < g.length; i++) {
            g[i] = new ArrayList<>();
        }
        //设置每个点的初始连接为下一个节点
        for (int i = 0; i < n - 1; i++) {
            g[i].add(i + 1);
        }
        //结果
        int[] ans = new int[queries.length];

        //visited数组，BFS和DFS的常客。
        int[] vis = new int[n - 1];
        for (int i = 0; i < queries.length; i++) {
            //新生产路径，放在这个循环里面 对整个图的影响是永久的
            g[queries[i][0]].add(queries[i][1]);
            //寻找新答案
            ans[i] = bfs(i + 1, g, vis, n);
        }
        return ans;
    }

    //BFS暴力搜索
    private int bfs(int i, List<Integer>[] g, int[] vis, int n) {
        //q就是路径长度
        List<Integer> q = new ArrayList<>();
        //添加初始起点0
        q.add(0);
        //开始BFS
        //设置step初始值为1
        for (int step = 1;; step++) {
            List<Integer> tmp = q; //每次用中间列表tmp来接收当前遍历节点的邻接表。
            q = new ArrayList<>(); // 重置q
            //x从0开始BFS
            for (int x : tmp) { //遍历tmp中的每个节点的邻接表
                //g 是整张图的邻接表
                for (int y : g[x]) { //g[x]就是实际的邻接表
                    if (y == n - 1) { //如果已经找到了，返回step
                        return step;
                    }
                    //如果y节点没有访问过，就继续访问y节点
                    if (vis[y] != i) {
                        vis[y] = i;
                        q.add(y);
                    }
                }
            }
            //每有一条就step++
        }
    }
}
