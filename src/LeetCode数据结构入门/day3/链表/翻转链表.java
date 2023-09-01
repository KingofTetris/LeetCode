package LeetCode数据结构入门.day3.链表;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 翻转链表
 * @Time 2021/10/3  14:32
 */

//给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
public class 翻转链表 {


    @Test
    public void test(){
        ListNode root = ListNode.createListNode(new int[]{1,2,3,4,5});
        ListNode res = reverseList(root);
        ListNode.printLinkedList(res);
    }
//    可以借助栈，数组来重建队列都有较大的空间消耗
//    这里用prev来记录前节点
    public ListNode reverseList(ListNode head) {
       /* ListNode prev = null;
        ListNode current = head;
        while(current != null){
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;*/
        ListNode dummy = new ListNode();
        ListNode temp;
        while (head != null){
            temp = head;
            head = head.next;

            temp.next = dummy.next;
            dummy.next = temp;
        }

        return dummy.next;
    }


}
