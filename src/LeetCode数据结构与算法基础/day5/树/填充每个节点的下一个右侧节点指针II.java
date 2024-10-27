package LeetCode数据结构与算法基础.day5.树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by KingOfTetris
 * @date 2024/10/27
 */


class NodeNext {
    public int val;
    public NodeNext left;
    public NodeNext right;
    public NodeNext next;
    public NodeNext() {}
    public NodeNext(int _val) {
        val = _val;
    }
    public NodeNext(int _val, NodeNext _left, NodeNext _right, NodeNext _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

public class 填充每个节点的下一个右侧节点指针II {

    public NodeNext connect(NodeNext root) {
        if (root == null) return null;
        Queue<NodeNext> queue = new LinkedList<>();
        queue.add(root);
        root.next = null;
        while (!queue.isEmpty()) {
            int n = queue.size();
            NodeNext last = null;
            for (int i = 1; i <= n; ++i) {
                NodeNext f = queue.poll(); //当前节点
                if (f.left != null) {
                    queue.offer(f.left);
                }
                if (f.right != null) {
                    queue.offer(f.right);
                }
                //该层节点的next都指向下一个节点。
                if (i != 1) {
                    last.next = f;
                }
                last = f;
            }
        }
        return root;
    }
}
