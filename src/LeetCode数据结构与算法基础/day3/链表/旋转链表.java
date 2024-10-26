package LeetCode数据结构与算法基础.day3.链表;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/10/26
 */
public class 旋转链表 {

    @Test
    public void test(){
        int[] nums = {1};
        ListNode node = ListNode.createListNode(nums);
        ListNode rotateRight = rotateRight(node, 1);
        ListNode.printLinkedList(rotateRight);
    }

    //本来是旋转数组，现在变成旋转链表，右移K位
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null) return head;
        //其实比旋转数组简单，只要找到倒数第k个节点，让它指向head就行了。
        ListNode p = head;
        int len = 0;
        while (p != null){
            len++;
            p = p.next;
        }
        k = k % len;
        if (k == 0) return head; //如果k刚好就是len的倍数，也别右移浪费时间了，还是head.
        //找到倒数第k个节点
        ListNode fast = head,slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        //现在slow就是倒数第k + 1个节点。
        ListNode resHead = slow.next;
        ListNode q = resHead;
        slow.next = null;
        while (q.next != null){
            q = q.next;
        }
        q.next = head; //接回表头
        //最后返回resHead即可
        return resHead;
    }
}
