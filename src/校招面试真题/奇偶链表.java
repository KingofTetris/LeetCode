package 校招面试真题;

import LeetCode数据结构基础.day3.链表.ListNode;
import org.junit.Test;

public class 奇偶链表 {
    @Test
    public void test(){
        ListNode listNode = ListNode.createListNode(new int[]{1,2,3,4,5});
        ListNode res = oddEvenList(listNode);
        ListNode.printLinkedList(res);
    }
    //你得在O(n)时间复杂度 O(1)空间复杂度下解决这个问题
    /**
     * 两个指针，一个指针去连接奇链表，一个指针去连接偶链表
     * 然后让奇链表连接偶链表即可。
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        //小于等于1的情况直接返回。
        if (head == null || head.next == null) {
            return head;
        }
        //记录两个头指针，方便返回结果
        ListNode oddHead = head;
        ListNode evenHead = head.next;

        ListNode odd = head;//奇链表
        ListNode even = head.next;//偶链表
        int flag = 3; //从第3个节点开始
        head = even.next;
        while (head != null){
            //如果是奇数
            //链接奇链表 指针后移odd = odd.next
            if (flag % 2 == 1){
                odd.next = head;
                odd = odd.next;
            }
            //如果是偶数
            //链接偶数表 指针后移even = even.next
            else {
                even.next = head;
                even = even.next;
            }
            head = head.next;
            flag++;
        }
        //然后奇链表表尾指向偶链表表头
        //要小心，出现3->5，同时4->5这种情况出现。
        //就成了1->3->5->2->4->5->2...就成环了。
        //避免成环,加上下面这句话就完事了。
        even.next = null;
        odd.next = evenHead;
        //返回oddHead就是奇偶链表。
        return oddHead;
    }
}
