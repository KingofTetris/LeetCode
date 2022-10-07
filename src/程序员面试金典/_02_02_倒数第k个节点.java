package 程序员面试金典;

import LeetCode数据结构入门.day3.链表.ListNode;

/**
 * @author by KingOfTetris
 * @date 2022/9/29
 *
 * 链表的双指针可以解决，倒数第k个节点，有没有环，环的长度是多少，中间节点等等。
 */
public class _02_02_倒数第k个节点 {
    public int kthToLast(ListNode head, int k) {
        ListNode slow = head;
        ListNode fast = head;

        //fast先走k步
        while (fast != null && k != 0){
            fast = fast.next;
            k--;
        }
        if (k > 0) return Integer.MAX_VALUE;//倒数K超过链表长度

        //一起走 一直走到fast走到null 那么slow 就是倒数第k个节点
        while (fast != null){
            slow = slow.next;
            fast = fast.next;
        }

        return slow.val; //这个时候slow就是倒数第k个节点
    }
}
