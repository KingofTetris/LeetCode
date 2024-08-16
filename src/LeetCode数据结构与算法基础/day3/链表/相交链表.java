package LeetCode数据结构与算法基础.day3.链表;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author KingofTetris
 * @File 相交链表
 * @Time 2021/10/20  9:23
 */

/**
 * 给你两个单链表的头节点 headA 和 headB ，
 * 请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * <p>
 * 图示两个链表在节点 c1 开始相交：
 * <p>
 * <p>
 * <p>
 * 题目数据 保证 整个链式结构中不存在环。
 * <p>
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5],
 * listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null 。
 *  
 * <p>
 * 提示：
 * <p>
 * listA 中节点数目为 m
 * listB 中节点数目为 n
 * 0 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA <= m
 * 0 <= skipB <= n
 * 如果 listA 和 listB 没有交点，intersectVal 为 0
 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 *  
 * <p>
 * 进阶：你能否设计一个时间复杂度 O(n) 、仅用 O(1) 内存的解决方案？
 */

public class 相交链表 {
    @Test
    public void test() {
        ListNode ln1 = new ListNode(2);
        ListNode ln2 = new ListNode(4);
        ListNode ln3 = new ListNode(6);
        ln1.next = ln2;
        ln2.next = ln3;

        ListNode l2n1 = new ListNode(3);
        ListNode l2n2 = new ListNode(8);
        ListNode l2n3 = new ListNode(9);

        l2n1.next = l2n2;
        l2n2.next = l2n3;
        l2n3.next = ln2;

        //        2 -> 4 -> 6
        //    3->8->9->4->6      他们的交点就是4
        ListNode intersectionNode = getIntersectionNode2(ln1, l2n1);
        if (intersectionNode != null)
            System.out.println(intersectionNode.val);
        else
            System.out.println(intersectionNode);
    }


    //HashSet 哈希集合
    //注意这个相交的定义是使用了同一个结点，即引用地址一样 而不是新new出来一个结点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;
        Set<ListNode> visited = new HashSet<>();
        //和树一样，其实每个结点都是一颗树。 链表中每个结点都是一个链表
        //把headA所有节点输入到Set中 注意无环 每个节点都不重复。
        //因为存的是ListNode 是对象而不是值。
        //具体就是存的是引用地址。每个节点的地址都不同。
        while (p != null) {
            visited.add(p);
            p = p.next;
        }
        //从头部开始遍历 如果contains到一样的引用地址。那就找到了交点。
        while (q != null) {
            if (visited.contains(q)) {
                return q;
            }
            q = q.next;
        }
        return null;
    }

    //双指针 正确的人才能相交。这个算法只能对有交点的链表用，没有交点会死循环。
    //若要跳出死循环，可以给个长度变量，如果count超过了两人的长度之和还是没有找到，那就没有交点了。
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        //其实是道数学题
        //如果有交点，那么A和B交替走一定会走到这个交点
        //如果没有交点，那么A,B同时指向NULL，直接返回。

        ListNode A = headA, B = headB;
        while (A != B) {//终止条件A==B
            //如果A不等于null 往后走，走到尾巴再去走B
            A = A != null ? A.next : headB;
            //如果B不等于null 往后走，走到尾巴再去走A
            B = B != null ? B.next : headA;
        }
        return A;

       /* 作者：Krahets
        链接：https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/solutions/1190240/mian-shi-ti-0207-lian-biao-xiang-jiao-sh-b8hn/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
