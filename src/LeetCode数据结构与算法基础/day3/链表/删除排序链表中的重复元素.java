package LeetCode数据结构与算法基础.day3.链表;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 删除排序链表中的重复元素
 * @Time 2021/10/3  16:20
 */
/*
83. 删除排序链表中的重复元素
        存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。

        返回同样按升序排列的结果链表。*/
public class 删除排序链表中的重复元素 {

    @Test
    public void test(){
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(4);
        ListNode p5 = new ListNode(5);

        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;

//        while(p1 != null){
//            System.out.println(p1.val);
//            p1 = p1.next;
//        }
        ListNode newNode = deleteDuplicates(p1);
        while(newNode != null){
            System.out.println(newNode.val);
            newNode =  newNode.next;
        }
    }

//    由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，
//    因此我们只需要对链表进行一次遍历，就可以删除重复的元素。
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return head;

        //注意链表实际上不管怎么操作都是同一条 变的只是里面的节点。
        ListNode cur = head;

        while(cur.next != null){
            //next == val 就直接断链，把next指向next.next
            if (cur.val == cur.next.val){
                cur.next = cur.next.next;
            }
            else {
                cur = cur.next;
            }

        }
        return head;
    }
}
