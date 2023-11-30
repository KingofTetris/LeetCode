package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构与算法基础.day3.链表.ListNode;

/**
 * @Author KingofTetris
 * @Date 2022/7/20 18:33
 *
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 *
 * 返回删除后的链表的头节点。
 *
 * 注意：此题对比原题有改动
 *
 * 示例 1:
 *
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 *
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *  
 *
 * 说明：
 *
 * 题目保证链表中节点的值互不相同
 * 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer18_删除链表的节点 {

    /**
     * 双指针很容易写出来
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode q = head;
        ListNode p = head; //两个指针一起走  q先走一步
        //没走之前先判断q的值是不是就是val
        if (q.val == val){
            return q.next;//直接返回q.next就行了
        }
        /**
         * 其他情况两个指针一起走
         */
        if (q.next != null){
            q = q.next;
        }
        while (q != null){
            if (q.val == val){
                p.next = q.next;
            }
            q = q.next;
            p = p.next;//两个指针一起走
        }
        return head;//返回链表
    }

    /**
     * 单指针也很简单。
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode2(ListNode head, int val){
        if(head.val == val) {
            return head.next;
        }

        ListNode pre = head;
        while ((pre.next != null) && (pre.next.val != val)){
            /**
             * 这个条件就是没找到对应的值，并且当前节点的next的值不等于val就一直往下next*/
            pre = pre.next;
        }
        //如果pre.next已经是null了。那就没有这个节点，返回head就行了。
        if(pre.next != null) {
            pre.next = pre.next.next;
        }

        return head;
    }

    /**
     * 还可以用单指针加计数器也行。
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode3(ListNode head, int val){
        return null;
    }

}
