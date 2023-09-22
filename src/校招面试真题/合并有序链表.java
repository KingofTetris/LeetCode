package 校招面试真题;

import LeetCode数据结构入门.day3.链表.ListNode;
import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/9/22
 */
public class 合并有序链表 {
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
        ListNode pre = prehead;//相当于记录一下头节点。
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
        //如果有一个为空了，直接改为下一个即可
        pre.next = (l1 == null) ? l2 : l1;
        return prehead.next;
    }
}
