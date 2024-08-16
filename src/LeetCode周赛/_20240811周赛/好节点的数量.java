package LeetCode周赛._20240811周赛;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author by KingOfTetris
 * @date 2024/8/11
 */

/**
 * 现有一棵 无向 树，树中包含 n 个节点，按从 0 到 n - 1 标记。树的根节点是节点 0 。
 * 给你一个长度为 n - 1 的二维整数数组 edges，其中 edges[i] = [ai, bi] 表示树中节点 ai 与节点 bi 之间存在一条边。
 *
 * 如果一个节点的所有子节点为根的子树
 *  包含的节点数相同，则认为该节点是一个 好节点。
 *
 * 返回给定树中 好节点 的数量。
 *
 * 子树 指的是一个节点以及它所有后代节点构成的一棵树。
 */
public class 好节点的数量 {

    public int countGoodNodes(int[][] _edges) {

        //构造邻接表
        List<Integer>[] edges = new List[_edges.length + 1];
        for (int i = 0; i < edges.length; ++i) {
            edges[i] = new ArrayList<>();
        }
        //这个是双向
        for (int[] e : _edges) {
            edges[e[0]].add(e[1]);
            edges[e[1]].add(e[0]);
        }
        int[] res = {0};
        //DFS寻找好节点
        dfs(0, -1, edges, res);
        return res[0];
    }

    private int dfs(int cur, int last, List<Integer>[] edges, int[] res) {
        int sum = 0;
        Set<Integer> down = new HashSet<>();
        //遍历当前节点的邻接表
        for (int n : edges[cur]) {
            if (n != last) { //如果节点不是-1，也就不是叶子节点
                //就继续往下深搜
                int tmp = dfs(n, cur, edges, res);
                sum += tmp; //加上当前节点的tmp
                down.add(tmp);
            }
        }
        if (down.size() <= 1) {
            res[0] += 1;
        }
        return sum + 1;
    }
}
