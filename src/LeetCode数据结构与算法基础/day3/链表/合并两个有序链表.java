package LeetCode数据结构与算法基础.day3.链表;

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
        ListNode pre = prehead; //用preHead记录链表开头。
        while (l1 != null && l2 != null) {
            //谁小就先插入谁。
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        //最后直接插入另外一个不为空的list就行了。
        pre.next = (l1 == null) ? l2 : l1;
        return prehead.next;
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode();
        ListNode head = new ListNode();
        //保存head地址
        dummyNode.next = head;
        ListNode q = list1;
        ListNode p = list2;
        while(q != null && p != null){
            if(q.val < p.val){
                head.next = q;
                q = q.next;
            }
            else{
                head.next = p;
                p = p.next;
            }
            head = head.next;
        }
        if(q == null) head.next = p;
        if(p == null) head.next = q;
        return dummyNode.next.next;
    }
}
