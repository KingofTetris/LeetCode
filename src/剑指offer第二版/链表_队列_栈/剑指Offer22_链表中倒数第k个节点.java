package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构入门.day3.链表.ListNode;

/**
 * @author by KingOfTetris
 * @date 2022/7/22
 */
public class 剑指Offer22_链表中倒数第k个节点 {

    /**
     * 也是双指针，先让快指针走k步，然后一起走。走到fast指向null。
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return null;

        ListNode fast = head;
        ListNode slow = head;

        int t = 0;
        while (fast != null){
            //也可以加个t省去for循环
            if (t >= k) slow = slow.next;
            fast =fast.next;
            t++;
        }
        if (t == 0) return null;//如果t根本没动过，那k越界了
        return slow;
    }
}
