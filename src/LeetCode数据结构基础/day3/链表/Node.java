package LeetCode数据结构基础.day3.链表;

/**
 * @Author KingofTetris
 * @Date 2022/8/29 15:54
 * 专用于剑指 Offer 35. 复杂链表的复制这题的数据结构
 */
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
