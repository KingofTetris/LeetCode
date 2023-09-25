package 校招笔试真题.星网锐捷;

import LeetCode数据结构入门.day3.链表.ListNode;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/9/24
 */
public class 链表排序 {


    @Test
    public void test(){
        ListNode q = ListNode.createListNode(new int[]{3,-1,8,5,1});
        ListNode res = sortList(q);
        ListNode.printLinkedList(res);
    }
    public ListNode sortList (ListNode head) {
        // write code here
        if (head == null) return null;
        int len = 0;
        ListNode p = head;
        while (p != null){
            p = p.next;
            len++;
        }
        p = head;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = p.val;
            p = p.next;
        }
        Arrays.sort(nums);
        ListNode resHead = new ListNode(nums[0]);
        ListNode res = resHead;
        for (int i = 1; i < len; i++) {
            ListNode temp = new ListNode(nums[i]);
            //链接结点，指针后移
            resHead.next = temp;
            resHead = resHead.next;
        }
        return res;
    }
}
