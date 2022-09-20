package LeetCode数据结构基础.day3.链表;

import LeetCode数据结构入门.day3.链表.ListNode;

/**
 * @author KingofTetris
 * @File 翻转列表
 * @Time 2021/10/22  17:13
 */
public class 翻转列表 {

    /**
     * 反转链表
     * @param head 头节点
     * @return
     */
    public static ListNode reverseList(ListNode head){
        // pre是指向前一个节点的指针，初始头节点前面的null
        ListNode pre = null;
        // curr是当前节点
        ListNode curr = head;
        while (curr!=null){
            // 记录下一个节点的指针
            ListNode next = curr.next;
            // 将指向下一个节点的指针反转指向前一个节点
            curr.next = pre;
            // 更新前一个节点（后移
            pre = curr;
            // 后移当前节点
            curr = next;
        }
        // 最后pre会移动到最后，但此时由于链表反转pre正好是反转后的头
        return pre;
    }
}
