package 校招真题.腾讯.腾讯校园招聘技术类编程题;

import java.util.*;

/**
 * @Author KingofTetris
 * @Date 2023/4/3 14:53
 * 现在有 1e5 个用户，编号为 1- 1e5，现在已知有 m 对关系，
 * 每一对关系给你两个数 x 和 y，代表编号为 x 的用户和编号为 y 的用户是在一个圈子中，
 * 例如： A 和 B 在一个圈子中， B 和 C 在一个圈子中，那么 A , B , C 就在一个圈子中。
 * 现在想知道最多的一个圈子内有多少个用户。
 *
 * 数据范围 1<= m <= 2 * 1e6
 * 进阶：要求空间 O(n) 时间O(nlogn)
 * 这题用模拟求最大连通分量的话，需要用到邻接表，那就需要O(n^2)的空间。就不符合进阶了
 * 如果要符合进阶要求需要用并查集
 *
 * 输入描述：
 * 第一行输入一个整数T，接下来有T组测试数据。
 * 对于每一组测试数据：第一行输入1个整数n，代表有n对关系。
 * 接下来n行，每一行输入两个数x和y，代表编号为x和编号为y的用户在同一个圈子里。
 * 1 ≤ T ≤ 10
 * 1 ≤ n ≤ 2*1e6
 * 1 ≤ x, y ≤ 1e5
 * 输出描述：
 * 对于每组数据，输出一个答案代表一个圈子内的最多人数
 * 示例1
 * 输入例子：
 * 2
 * 4
 * 1 2
 * 3 4
 * 5 6
 * 1 6
 * 4
 * 1 2
 * 3 4
 * 5 6
 * 7 8
 * 输出例子：
 * 4
 * 2
 */

//其实就是求这张图的最大连通分量的大小
public class 朋友圈 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int graph_nums = sc.nextInt();
        int T = 0;
        while (T < graph_nums){
            LinkedHashSet<edge> edges = new LinkedHashSet<>();
            int edges_nums = sc.nextInt();
            for (int i = 0; i < edges_nums; i++) {
                 edge edge = new edge(sc.nextInt(),sc.nextInt());
                 edges.add(edge);
            }
            int pyq = pyq(edges);
            System.out.println(pyq);
            T++;
        }
        sc.close();
    }


    //求最大连通分量
    public static int pyq(LinkedHashSet<edge> edges){
        // 1. 构建邻接表
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (edge e : edges) {
            adjList.putIfAbsent(e.node1, new ArrayList<>());
            adjList.putIfAbsent(e.node2, new ArrayList<>());
            adjList.get(e.node1).add(e.node2);
            adjList.get(e.node2).add(e.node1);
        }

        // 2. 遍历每个连通分量，记录最大值
        int maxComponentSize = 0;
        Set<Integer> visited = new HashSet<>();
        for (int node : adjList.keySet()) { //遍历所有节点去找最大连通分量。
            if (!visited.contains(node)) { //如果已经遍历过就不用再遍历了。
                int componentSize = dfs(adjList, node, visited);
                maxComponentSize = Math.max(maxComponentSize, componentSize);
            }
        }

        return maxComponentSize;
    }


    /**
     * 模拟的关键在于怎么求连通分量的大小。
     * 这里用DFS来记录。每个节点不断深挖邻居，邻居的邻居，邻居的邻居的邻居...直到所有节点都被记录过。
     * @param adjacencyList
     * @param node
     * @param visited
     * @return
     */
    private static int dfs(Map<Integer, List<Integer>> adjacencyList, int node, Set<Integer> visited) {
        visited.add(node);
        int componentSize = 1;
        for (int neighbor : adjacencyList.getOrDefault(node, new ArrayList<>())) {
            //如果没遍历过这个邻居就继续深挖。
            if (!visited.contains(neighbor)) {
                //每深入一层就加上这个点的最大深度。
                componentSize += dfs(adjacencyList, neighbor, visited);
            }
        }
        return componentSize;
    }

}

class edge{
    int node1,node2;

    edge(int node1,int node2){
        this.node1 = node1;
        this.node2 = node2;
    }

    public boolean equals(edge e) {
        if ((e.node1 == this.node1 && e.node2 == this.node2) ||
                (e.node1 == this.node2 && e.node2 == this.node1)){
            return true;
        }
        return false;
    }
}
