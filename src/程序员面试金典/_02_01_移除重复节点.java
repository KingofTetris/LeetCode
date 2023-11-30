package 程序员面试金典;

import LeetCode数据结构与算法基础.day3.链表.ListNode;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author by KingOfTetris
 * @date 2022/9/29
 */
public class _02_01_移除重复节点 {


    /**
     * 借助Set去重
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        Set<Integer> set = new LinkedHashSet<Integer>();
        while (head != null){
            set.add(head.val);
            head = head.next;
        }

        ListNode dummy = new ListNode();
        ListNode newHead = dummy;
        for (Integer integer : set) {
            ListNode node = new ListNode(integer);
            dummy.next = node;
            dummy = dummy.next;
        }

        return newHead.next;
    }


    /**
     * 不用额外空间就是暴力法，O(n^2)
     *
     * 链表没有for循环，就只能双指针
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes2(ListNode head) {
        ListNode ob = head;
        while (ob != null) {
            ListNode oc = ob;
            while (oc.next != null) {
                if (oc.next.val == ob.val) { //和前面一样就一直往后
                    oc.next = oc.next.next;
                } else { //不一样就把oc的值换一个
                    oc = oc.next;
                }
            }
            ob = ob.next;//开启下一轮
        }
        return head;
    }
}
