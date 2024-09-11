package LeetCode数据结构与算法基础.DFS与BFS;

import java.util.Scanner;


//删掉一条边使得，图中没有环
//如果有多种选择，删掉赋值时给的最后一条。
public class 冗余连接 {
    static int n; // 节点数量
    static int[] father = new int[1001]; // 按照节点大小范围定义数组

    // 并查集初始化
    static void init() {
        for (int i = 0; i <= n; ++i) {
            father[i] = i;
        }
    }

    // 并查集里寻根的过程
    static int find(int u) {
        return u == father[u] ? u : (father[u] = find(father[u]));
    }

    // 判断 u 和 v 是否找到同一个根
    static boolean isSame(int u, int v) {
        u = find(u);
        v = find(v);
        return u == v;
    }

    // 将 v->u 这条边加入并查集
    static void join(int u, int v) {
        u = find(u); // 寻找u的根
        v = find(v); // 寻找v的根
        if (u == v) return; // 如果发现根相同，则说明在一个集合，不用两个节点相连直接返回
        father[v] = u;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s, t;
        n = scanner.nextInt();
        init();
        for (int i = 0; i < n; i++) {
            s = scanner.nextInt();
            t = scanner.nextInt();
            if (isSame(s, t)) {
                System.out.println(s + " " + t);
                return;
            } else {
                join(s, t);
            }
        }
    }
}
