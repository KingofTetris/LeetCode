package LeetCode数据结构与算法基础.DFS与BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class 克隆图Node {
    public int val;
    public List<克隆图Node> neighbors;
    public 克隆图Node() {
        val = 0;
        neighbors = new ArrayList<克隆图Node>();
    }
    public 克隆图Node(int _val) {
        val = _val;
        neighbors = new ArrayList<克隆图Node>();
    }
    public 克隆图Node(int _val, ArrayList<克隆图Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

/**
 * @author by KingOfTetris
 * @date 2024/10/27
 */
public class 克隆图 {

    public 克隆图Node cloneGraph(克隆图Node node) {
        Map<克隆图Node, 克隆图Node> lookup = new HashMap<>();
        return dfs(node, lookup);
    }

    private 克隆图Node dfs(克隆图Node node, Map<克隆图Node,克隆图Node> lookup) {
        if (node == null) return null;
        if (lookup.containsKey(node)) return lookup.get(node);
        克隆图Node clone = new 克隆图Node(node.val, new ArrayList<>());
        lookup.put(node, clone);
        for (克隆图Node n : node.neighbors)clone.neighbors.add(dfs(n,lookup));
        return clone;
    }

  /*  作者：powcai
    链接：https://leetcode.cn/problems/clone-graph/solutions/14893/dfs-he-bfs-by-powcai/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
