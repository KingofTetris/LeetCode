package LeetCode数据结构与算法基础.day3.链表;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/8/15
 */
public class 排序链表 {


    @Test
    public void test(){
        ListNode head = ListNode.createListNode(new int[]{2,3,1,5,7,6});
        ListNode listNode = sortList(head);
        ListNode.printLinkedList(listNode);
    }
    //最简单的化成list,然后对list排序，最后拼接链表就可以了。
    //O(nlogn) O(n)
    public ListNode sortList1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        Collections.sort(list);
        ListNode dummy;
        ListNode resHead = new ListNode();
        dummy = resHead;
        for (int i = 0; i < list.size(); i++) {
            ListNode temp = new ListNode(list.get(i));
            resHead.next = temp;
            resHead = resHead.next;
        }
        return dummy.next;
    }

    //进化版，
    /**
     * 利用快慢指针找出链表的中间节点，并切分成两个子链表
     * 分别递归排序左子链表和右子链表
     * 归并排序左右链表，返回排序后的头节点
     * @param head
     * @return
     *
     * O(nlogn)  递归栈大小 O(logN)
     */
    public ListNode sortList(ListNode head){
        // 归并排序做法
        if (head == null || head.next == null) return head;
        // 切分两组
        ListNode newHead = split(head);
        // 排序左链表
        head = sortList(head);
        // 排序右链表
        newHead = sortList(newHead);
        // 归并排序左右链表
        return merge(head, newHead);
    }

    public ListNode split(ListNode node){ // 找出中间节点切割后，返回右子链表的开头节点
        // 如果slow从node直接开始会导致只有两个节点时，返回的slow.next为null，所以构建虚拟头节点
        ListNode slow = new ListNode();
        slow.next = node;
        ListNode fast = slow;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newNode = slow.next; // 右子链表的开头节点
        slow.next = null; // 断开两个子链表之间的联系
        return newNode;
    }
    //分成左右两边进行merge
    public ListNode merge(ListNode head1, ListNode head2){ // 合并并排序左右子链表
        ListNode headA = head1;
        ListNode headB = head2;
        ListNode head = new ListNode();
        ListNode pre = head;
        while (headA != null && headB != null){
            if (headA.val <= headB.val){
                pre.next = headA;
                pre = pre.next;
                headA = headA.next;
            } else {
                pre.next = headB;
                pre = pre.next;
                headB = headB.next;
            }
        }
        if (headA != null){
            pre.next = headA;
        }

        if (headB != null){
            pre.next = headB;
        }
        return head.next;
    }
}
/*作者：鱼
        链接：https://leetcode.cn/problems/sort-list/solutions/2914017/chao-ji-rong-yi-li-jie-de-gui-bing-pai-x-qgwv/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
