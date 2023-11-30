package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构与算法基础.day3.链表.ListNode;

/**
 * @Author KingofTetris
 * @Date 2022/7/26 15:40
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 限制：
 *
 * 0 <= 链表长度 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer25_合并两个排序的链表 {
    /**
     * 双指针 实际上是三指针。有一个指针有留在头部
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode p = head;//始终指向头部 方便返回
        while (l1 != null && l2!=null){
            int val1 = l1.val;
            int val2 = l2.val;
            if (val1 < val2){ //谁小谁放前面
                ListNode node = new ListNode(val1);
                head.next = node;
                l1 = l1.next;//l2后移
            }
            else {
                ListNode node = new ListNode(val2);
                head.next = node;
                l2 = l2.next;
            }
            head = head.next;//head后移
        }

        if (l1 == null){
            head.next = l2;
        }
        else{
            head.next = l1;
        }
        return p.next;
    }

    /**
     * 递归解法 O(n) O(n)
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null){
            return l1 != null ? l1 : l2;
        }
        if(l1.val <= l2.val){ //谁小谁在前面
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }
}
