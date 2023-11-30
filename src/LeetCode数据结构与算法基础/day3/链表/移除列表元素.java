package LeetCode数据结构与算法基础.day3.链表;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 移除列表元素
 * @Time 2021/10/3  14:15
 */

//203. 移除链表元素
//        给你一个链表的头节点 head 和一个整数 val ，
//        请你删除链表中 **所有** 满足 Node.val == val 的节点，并返回 新的头节点 。

/*    提示：

            列表中的节点数目在范围 [0, 10^4] 内
            1 <= Node.val <= 50
            0 <= val <= 50*/
public class 移除列表元素 {

    @Test
    public void test(){

    }

    //你要删除元素肯定要先找到前一个节点。
    //且链头有可能会被删除，创造哑节点dummyHead
    //dummyHead.next = head
    //最后返回dummy.next


    //删除节点常常要用到哑节点
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null){
            if (temp.next.val == val)
                temp.next = temp.next.next;
            else
                temp = temp.next;
        }
        return dummyHead.next;
    }
}
