package LeetCode数据结构与算法基础.DFS与BFS;

/**
 * @author KingofTetris
 * @File Node
 * @Time 2021/9/25  16:17
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    //next指向兄弟节点
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
