package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构入门.day3.链表.ListNode;

/**
 * @Author KingofTetris
 * @Date 2022/7/26 15:40
 */
public class 剑指Offer25_合并K个排序的链表 {

//    两两合并，故渐进时间复杂度为O(k^2 n)。
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; ++i) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

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
     * 递归解法 写法很简单，但是递归栈也要占用O(n)的空间复杂度。没有上面的好
     * O(n) O(n)
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
