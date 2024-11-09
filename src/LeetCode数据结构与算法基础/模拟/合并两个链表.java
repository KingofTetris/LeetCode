package LeetCode数据结构与算法基础.模拟;

import LeetCode数据结构与算法基础.day3.链表.ListNode;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 合并两个链表 {

    /**
     * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
     *
     * 请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置。
     * @param list1
     * @param a
     * @param b
     * @param list2
     * @return
     */
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode preA = list1;
        for (int i = 0; i < a - 1; i++) {
            preA = preA.next;
        }
        ListNode preB = preA;
        for (int i = 0; i < b - a + 2; i++) {
            preB = preB.next;
        }
        preA.next = list2;
        while (list2.next != null) {
            list2 = list2.next;
        }
        list2.next = preB;
        return list1;
    }

  /*  作者：力扣官方题解
    链接：https://leetcode.cn/problems/merge-in-between-linked-lists/solutions/2079499/he-bing-liang-ge-lian-biao-by-leetcode-s-alt8/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
