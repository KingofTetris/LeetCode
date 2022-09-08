package LeetCode数据结构入门.day3.链表;

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
    public void test(){
        ListNode list1_1 = new ListNode(1);
        ListNode list1_2 = new ListNode(2);
        ListNode list1_3 = new ListNode(3);
        ListNode list1_4 = new ListNode(4);

        ListNode list2_1 = new ListNode(6);
        ListNode list2_2 = new ListNode(7);
        ListNode list2_3 = new ListNode(15);

        list1_1.next = list1_2;
        list1_2.next = list1_3;
        list1_3.next = list1_4;


        list2_1.next = list2_2;
        list2_2.next = list2_3;

        ListNode list3 = mergeTwoLists(list1_1,list2_2);
        while (list3 != null){
            System.out.print(list3.val + "\t");
            list3 = list3.next;
        }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode pre = prehead;

        while(l1!=null&&l2!=null){
            if (l1.val <= l2.val){
                pre.next = l1;
                l1  = l1.next;
            }
            else{
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = (l1 == null)?l2:l1;

        return prehead.next;
    }
}
