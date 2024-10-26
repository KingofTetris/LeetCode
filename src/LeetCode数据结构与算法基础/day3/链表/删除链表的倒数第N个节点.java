package LeetCode数据结构与算法基础.day3.链表;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/8/14
 */
public class 删除链表的倒数第N个节点 {


    @Test
    public void test(){
        ListNode node = ListNode.createListNode(new int[]{1});
        ListNode res = removeNthFromEnd(node, 1);
        ListNode.printLinkedList(res);
    }

    //双指针，要删除倒数第N个元素，那你应该先找到倒数第N+1个元素，让他指向N-1，就完成了删除
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 由于可能会删除链表头部，用哨兵节点简化代码
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode left = dummy, right = dummy;

        for (int i = 0; i < n; i++) {
            right = right.next; // 右指针先向右走 n 步
        }

        while (right.next != null) {
            left = left.next;
            right = right.next; // 左右指针一起走，right走到表尾,left就走到倒数n+1个节点。
        }

        //把left 指向next.next 就删除了倒数第n个节点。
        left.next = left.next.next; // 左指针的下一个节点就是倒数第 n 个节点
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        //因为有可能删除第一个节点。来个哑节点
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        //fast多走一步，slow再一起走
        for(int i = 0;i < n + 1;i++){
            if(fast != null){
                fast = fast.next;
            }
        }
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        //此时slow就是倒数第n个节点的前一个节点。
        slow.next = slow.next.next;
        return dummy.next;
    }
}
