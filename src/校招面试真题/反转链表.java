package 校招面试真题;

import LeetCode数据结构与算法基础.day3.链表.ListNode;

import java.util.ArrayList;

/**
 * @author by KingOfTetris
 * @date 2023/8/24
 */
public class 反转链表 {

    public static void main(String[] args) {
        ListNode head = ListNode.createListNode(new int[]{1,2,3});
        ListNode res = ReverseList(head);
        ListNode.printLinkedList(res);
    }
    public static ListNode ReverseList (ListNode head) {
        // write code here
        ArrayList<ListNode> arr = new ArrayList<>();
        while(head != null){
            arr.add(head);
            head = head.next;
        }
        //逆序遍历链表，尾插法插入即可
        ListNode dummyHead = new ListNode(-1);
        ListNode p = dummyHead;
        for(int i = arr.size() - 1; i >= 0; i--){
            arr.get(i).next = null;//先修改一下原本节点的指向，防止形成回路
            p.next = arr.get(i);//修改当前节点的next为arr.get(i)
            p = p.next; //移动p指针
        }
        return dummyHead.next;
    }
}
