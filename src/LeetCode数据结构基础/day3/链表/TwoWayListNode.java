package LeetCode数据结构基础.day3.链表;

/**
 * @Author KingofTetris
 * @Date 2022/7/20 14:03
 */
public class TwoWayListNode {
    public int val;
    public TwoWayListNode next;
    public TwoWayListNode pre;

    public TwoWayListNode(int val, TwoWayListNode next, TwoWayListNode pre) {
        this.val = val;
        this.next = next;
        this.pre = pre;
    }

    public TwoWayListNode(){}

    public TwoWayListNode(int val){
        this.val = val;
    }
}
