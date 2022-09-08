package 每日一题;

import LeetCode数据结构入门.day3.链表.ListNode;

import java.util.HashSet;

/**
 * @author by KingOfTetris
 * @date 2022/9/8
 * 输入两个链表，找出它们的第一个公共节点。
 *
 * 如下面的两个链表：
 * 在节点 c1 开始相交。
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 * 示例 2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 * 示例 3：
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。

 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * 本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


public class 剑指Offer52_两个链表的第一个公共节点 {

    /**
     * 双指针模拟
     * O(n) O(1)
     * @param headA
     * @param headB
     * @return
     */
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**
         * 先计算出A,B的长度，让长的先走
         */
        int lenA = 0, lenB = 0;
        ListNode q = headA;
        ListNode p = headB;
        while (q != null){
            lenA++;
            q = q.next;
        }
        while (p  != null){
            lenB++;
            p = p.next;
        }
        q = lenA > lenB ? headA : headB; //q指向长的
        p = lenA > lenB ? headB : headA;  //p指向短的

        int diff = Math.abs(lenA - lenB);//差值，应该先走几步
        while (diff > 0){
            q = q.next; //让长的先走差值步和短的一起
            diff--;
        }
        while (q != null){ // 因为后面一样长，q != null就可以了
            if (q == p) return q;//如果地址一样就直接返回其中一个就行
            //如果不一样两个一起往后面走
            q = q.next;
            p = p.next;
        }

        return null;//如果没返回q 就是没找到交点
    }


    /**
     * 哈希Set法，用哈希Set完全读完一条链表的节点，然后遍历另外一条链表。
     * 遇到相同节点返回即可。
     * O(m+n) O(m)
     * @param headA
     * @param headB
     * @return
     */
    ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        HashSet<ListNode> hashSet = new HashSet<>();
        while (headA != null){
            hashSet.add(headA);
            headA = headA.next;
        }
        while (headB != null){
            if (hashSet.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }

    /**
     * 双指针循环遍历，不用再去计算谁长谁短
     * 我们使用两个指针 node1，node2 分别指向两个链表 headA，headB 的头结点，
     * 然后同时分别逐结点遍历，当 node1 到达链表 headA 的末尾时，
     * 重新定位到链表 headB 的头结点；当 node2 到达链表 headB 的末尾时，重新定位到链表 headA 的头结点。
     *
     * 这样，当它们相遇时，所指向的结点就是第一个公共结点。
     *
     * 作者：z1m
     * 链接：https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/shuang-zhi-zhen-fa-lang-man-xiang-yu-by-ml-zimingm/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param headA
     * @param headB
     * @return
     */
    ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        //谁先遍历完，就变成另外一个人
        ListNode node1 = headA;
        ListNode node2 = headB;

        /**
         * 即使 node1 和 node2 没有交点，那么走到尽头.node1 == node2 == null的时候也会结束
         */
        while (node1 != node2){
            node1 = node1 != null? node1.next : headB;
            node2 = node2 != null? node2.next : headA;
        }
        return node1;
    }


    /**
     * 如果存在环怎么办？
     * @param headA
     * @param headB
     * @return
     */
    ListNode getIntersectionNode4(ListNode headA, ListNode headB) {
        return null;
    }

}
