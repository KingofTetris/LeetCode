package 校招笔试真题.美团.美团2023春招0408;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/10
 * 题目描述：
 * 有一棵 n 个节点的树，有一条边被选定。
 * 小美想知道对于所有经过这条选定边的所有树上简单路径，最长的那条有多长。
 * 一条简单的路径的长度指这条简单路径上的边的个数。
 *
 * 输入描述
 * 第一行一个整数 n，表示树的节点个数。
 *
 * 第二行 n-1 个整数，第 i 个整数 pi 表示节点 i+1 和 pi 之间有一条边相连。
 *
 * 第三行两个整数 x, y，表示这条选定的边。保证这条边一定是树上的一条边。
 *
 * 对于全部数据，2 ≤ n ≤ 1e5, 1 ≤ pi ≤ n, 1 ≤ x, y ≤ n, x ≠ y。
 * 保证输入数据正确描述一棵树，并且 (x, y) 是树上的一条边。
 *
 * 输出描述
 * 输出一行，一个整数，表示所有经过选定边的树上简单路径中，最长的那条的长度。
 *
 *
 * 样例输入
 * 7
 * 1 2 3 4 5 3
 * 3 7
 * 样例输出
 * 4
 */
import java.util.*;

public class 最长路径 {

    static ArrayList<Integer>[] edges; //定义邻接表

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        edges = new ArrayList[N+1];//从1开始 所以N+1 注意这个时候只是定义了长度为N+1的邻接表，这个数组里的元素全都是null

        //所以下面才要进一步初始化
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        //接下来的输入就是邻接表里面的内容
        //从节点2开始
        for (int i = 2; i <= N; i++) {
            int p1 = sc.nextInt();
            edges[i].add(p1);
            edges[p1].add(i);
        }

        //接下来是要选中的边 from-to
        int from = sc.nextInt();
        int to = sc.nextInt();

        //以u, v为根在两个子树里找距离最远的点。
        int p1 = dfs(from,to);
        int p2 = dfs(to,from);
        int ans = p1+p2+1;

        System.out.println(ans);
    }

    private static int dfs(int from, int to) {
        ArrayList<Integer> neighbors = edges[from];
        int ans = -1;//因为算的是边，最深节点再往下是没有边的。设置为-1，返回ans+1就是0
        for (Integer neighbor : neighbors) { //递归结束的条件就是neighbor == [] dfs到最远
            if (neighbor != to) //从from出发，寻找和to不同方向的最远距离
            ans = Math.max(ans,dfs(neighbor,from));
        }
        return ans + 1;
    }

}
