package LeetCode数据结构与算法基础.day3.链表;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/8/15
 */
public class 排序链表 {

    public ListNode sortList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        list.sort(Comparator.naturalOrder());
        ListNode dummy = new ListNode();
        ListNode resHead = new ListNode();
        dummy.next = resHead;
        for (int i = 0; i < list.size(); i++) {
            ListNode temp = new ListNode(list.get(i));
            resHead.next = temp;
            resHead = resHead.next;
        }
        return dummy.next.next;
    }
}
