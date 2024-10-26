package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构与算法基础.day3.链表.ListNode;
import org.junit.Test;

/**
 * 第一题我们反转了整个链表，现在我们更新需求，我们仅仅想要更改链表的指定区间。
 * 比如1->2->3->4->5 我只想反转区间[2,4]。变成
 * 1->4->3->2->5
 * 请你实现代码。
 */
public class 剑指Offer24_反转链表的指定区间_就是反转链表II {

    @Test
    public void test() {
//        ListNode listNode = ListNode.createListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        ListNode listNode = ListNode.createListNode(new int[]{3,5});
        ListNode.printLinkedList(listNode);
        ListNode node = reverseInternList(listNode, 1, 2);
        ListNode.printLinkedList(node);
    }

    /**
     * 要断开再接上，你需要先找到断链的开头节点start,和他前一个节点pre，
     * 断链最后一个节点tail,断链结尾的下一个节点next
     * 然后反转这个断链
     * 让pre.next = tail;//注意反转以后，tail 已经是链头了。
     * 让start.next = next
     * 最后返回dummyHead。
     * 如果start刚好是1?startPre就是null了。
     * @param head
     * @param start
     * @param end
     * @return
     */
    private ListNode reverseInternList(ListNode head, int start, int end) {
        //先找到起点
        //然后反转start - end
        //然后拿着start节点前面的第一个节点指向反转链表
        //反转链表的最后一个节点指向end节点的next
        ListNode dummyHead = head;
        ListNode startPre = null;//头节点的前一个节点
        ListNode endNext = null;//断链的下一个节点。next
        ListNode startNode = null;//断链头节点
        int len = 0;
        while (head != null) {
            len++;
            //如果start==1 没有前一个节点怎么办呢？
            if (len == start - 1) {
                startPre = head;
            }
            if (len == start) {
                startNode = head;
            }
            if (len == end) {
                endNext = head.next; //end的下一个节点。
            }
            //指针后移
            head = head.next;
        }
        //开始反转start - end
        ListNode dummyStartHead = new ListNode();
        //到endNext就停下来
        while (startNode != endNext) {
            //头插法
            ListNode temp = startNode;
            startNode = startNode.next;

            temp.next = dummyStartHead.next;
            dummyStartHead.next = temp;//dummyHead永远指向头部
        }
        //现在拼凑链表,先找到反转链表的最后一个节点。
        ListNode q = dummyStartHead.next;
        while (q.next != null){
            q = q.next;
        }
        //拼凑后面
        q.next = endNext;
        //这里有个细节如果反转的是1-n，那么startPre为null 就没必要接前面了。
        //直接返回dummyStartHead即可
        if (startPre == null){
            return dummyStartHead.next;
        }
        //拼凑前面
        startPre.next = dummyStartHead.next;
        //然后返回整个链表即可
        return dummyHead;
    }

}
