package LeetCode数据结构基础.day3.链表;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 合并两个有序链表
 * @Time 2021/10/3  13:38
 */

/*将两个升序链表合并为一个新的 升序 链表并返回。
        新链表是通过拼接给定的两个链表的所有节点组成的。*/
public class 合并两个有序链表 {
    @Test
    public void test() {
        ListNode list1 = ListNode.createListNode(new int[]{1, 3, 5, 7});
        ListNode list2 = ListNode.createListNode(new int[]{2, 4, 6, 8});
        ListNode list3 = mergeTwoLists(list1, list2);
        while (list3 != null) {
            System.out.print(list3.val + "\t");
            list3 = list3.next;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode pre = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = (l1 == null) ? l2 : l1;
        return prehead.next;
    }
}
