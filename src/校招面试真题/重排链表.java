package 校招面试真题;

import LeetCode数据结构基础.day3.链表.ListNode;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @author KingofTetris
 * @File 重排链表
 * @Time 2021/10/22  10:45
 */

/*
给定一个单链表 L 的头节点 head ，单链表 L 表示为：
从L0 → L1 → … → Ln-1 → Ln
变L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

输入: head = [1,2,3,4]
输出: [1,4,2,3]

输入: head = [1,2,3,4,5]
输出: [1,5,2,4,3]

提示：

链表的长度范围为 [1, 5 * 104]
1 <= node.val <= 1000
*/


//瑞幸咖啡笔试。
public class 重排链表 {

    @Test
    public void test(){
        ListNode listNode = ListNode.createListNode(new int[]{1, 2, 3, 4, 5});
        ListNode.printLinkedList(listNode);
        reorderList(listNode);
        ListNode.printLinkedList(listNode);
    }

    //双端队列 LinkedList的(add/poll)First 和(add/poll)Last方法就是来回使用就构成了双端队列
    public void reorderList(ListNode head) {
        if(head == null)
            return;
        LinkedList<ListNode> deque = new LinkedList<>();
        //建议写成这样。
        ListNode p = head;
        while (p != null){
            deque.add(p);
            p = p.next;
        }
        ListNode vh = new ListNode(0);
        while (!deque.isEmpty()){
            ListNode prev = deque.pollFirst();
            vh.next = prev;
            ListNode next = deque.pollLast();
            prev.next = next;
            //如果双端队列的元素都poll完了，则next必为空 就结束了
            if (next == null){
                return;
            }
            //vh一直指向链表的最后节点
            vh = next;
        }
        //为了避免链表成环 比如1，2，3，4 你模拟一下就明白了
        vh.next = null;
    }
}
