package LeetCode数据结构与算法基础.图与并查集;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/10/3
 */
public class 国王收税问题_最短路径应用题 {
    /*
        一开始有n匹马，c个城市,m条道路
        然后输入m条道路和他们的权值也就是损失马匹数w[i][j]
        然后输入c-1行表示每个城市k的税收
        主城为0
        注意每批马只能收一个城市的税，不能让这批马继续往下走。这个条件很关键。
        那么国王最多可以收到多少税?
        还剩多少马
        去了哪些城市?(先按照收税从大到小排序，如果一样按字典序排序）*/

    /**
     * 100 5 5
     * 0 1 10
     * 0 2 20
     * 1 3 10
     * 2 3 5
     * 2 4 30
     * 10
     * 20
     * 30
     * 40
     * 这个用例应该输出
     * 100
     * 0
     * 4 3 2 1
     * 表示最大税收为100，马匹全部损失，去了4 3 2 1 4座城市
     *
     *
     * 10 4 3
     * 0 1 5
     * 0 2 7
     * 1 2 3
     * 1
     * 3
     * 5
     * 这个用例应该输出
     * 3
     * 3
     * 2
     * 要让税收最大只能去2号城市。因为3不可达，而如果去了1，那么马匹就只剩下5只，不能到达2。
     * 注意马匹只能使用一次。不能继续使用。比如如果选择 0 -> 1 -> 2的路线的话。
     * 你要直接用到2的消耗，途中无法在1处收税。
     */
// 用于表示边的类
    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Dijkstra 算法求从主城（0号城市）到其他城市的最短路径
    public static int[] dijkstra(int n, List<List<Edge>> graph) {
        int[] dist = new int[n];  // 存储从主城到每个城市的最短路径
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{0, 0});  // {当前城市, 当前距离}
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int city = curr[0];
            int currDist = curr[1];
            //如果当前距离大于记录的最短距离就跳过这条路。
            if (currDist > dist[city]) continue;

            //遍历这个点的边。
            for (Edge edge : graph.get(city)) {
                int nextCity = edge.to;
                int newDist = currDist + edge.weight;
                if (newDist < dist[nextCity]) {
                    dist[nextCity] = newDist;
                    pq.add(new int[]{nextCity, newDist});
                }
            }
        }
        return dist;  // 返回从主城到每个城市的最短路径
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入n, c, m
        int n = scanner.nextInt();  // 初始马匹数
        int c = scanner.nextInt();  // 城市数量
        int m = scanner.nextInt();  // 道路数量

        // 构建图
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            graph.add(new ArrayList<>());
        }

        // 输入m条道路信息
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.get(u).add(new Edge(v, w));
            graph.get(v).add(new Edge(u, w));
        }

        // 输入各城市的税收
        int[] taxes = new int[c];
        for (int i = 1; i < c; i++) {
            taxes[i] = scanner.nextInt();  // 城市0主城不需要税收输入
        }

        // 求从主城（0号城市）到每个城市的最短路径
        int[] dist = dijkstra(c, graph);

        // 用于存储每个城市的信息（城市编号，最小消耗下的剩余马匹数，税收）
        List<double[]> cityInfo = new ArrayList<>();

        // 计算每个城市能否到达，以及到达后的剩余马匹和税收
        for (int i = 1; i < c; i++) {
            //税收性价比 越大当然性价比越高啊 贪心去选择城市。
            double ratio = (double) taxes[i] / dist[i]; // Tax-to-loss ratio
            cityInfo.add(new double[]{i, dist[i], taxes[i], ratio});//记录相应信息,城市编号，从主城到达城市i损失的最小马匹，税收
        }

        // 按照性价比从大到小排序，如果性价比相同则按城市编号排序（字典序）
        cityInfo.sort((a, b) -> {
            if (a[3] != b[3]) {
                //compare方法直接把b放后面就是降序 不用加-号
                return Double.compare(b[3], a[3]); // Sort by ratio (descending)
            }
            return (int) (a[0] - b[0]); // Sort by city number (ascending) if ratios are equal
        });

        // 计算总税收和剩余马匹数，并记录访问过的城市
        int totalTax = 0;
        int remainingHorses = n;
        List<int[]> visitedCities = new ArrayList<>();

        //现在问题就变成了，你现在只有n匹马，怎么去收税才能让税收最大?
        for (double[] cityData : cityInfo) {
            int city = (int) cityData[0];
            int horsesCost = (int) cityData[1];
            int cityTax = (int) cityData[2];
            if (remainingHorses - horsesCost >= 0) {
                totalTax += cityTax;
                remainingHorses = remainingHorses - horsesCost;
                visitedCities.add(new int[]{city,cityTax});
            }
        }

        // 输出结果
        System.out.println(totalTax);
        System.out.println(remainingHorses);

        //visited还要按照税收值从大到小排序，如果税收一样，按字典序升序。
        visitedCities.sort((a,b) -> {
            if (a[1] == b[1]){
                return a[0] - b[0];
            }
            else {
                return b[1] - a[1];
            }
        });

        for (int[] cities : visitedCities) {
            System.out.print(cities[0] + " ");
        }
    }
}
