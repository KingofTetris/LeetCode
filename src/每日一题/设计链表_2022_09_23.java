package 每日一题;

import LeetCode数据结构与算法基础.day3.链表.ListNode;
import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2022/9/23
 *
 * 假设链表中的所有节点都是 0-index 的。
 */
public class 设计链表_2022_09_23 {

    @Test
    public void test(){
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        linkedList.get(1);            //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        linkedList.get(1);            //返回3


    }
}


class MyLinkedList {

    int length;
    ListNode head; //一个头节点就够了

    public MyLinkedList() {
        length = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        if (index < 0 || index >= length) {
            return -1;
        }
        ListNode cur = head.next;
        //小于等于index 是因为 cur 等于head
        //改成head.next 就是第一个节点 那么就要改成 <
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }


    //下面两个add只是addAtIndex的特殊情况
    //一个在0添加，一个在length添加
    public void addAtHead(int val) {
        addAtIndex(0, val);

    }

    public void addAtTail(int val) {
        addAtIndex(length, val);

    }

    public void addAtIndex(int index, int val) {
        if (index > length) {
            return;
        }
        index = Math.max(0, index);
        length++;
        ListNode pred = head;

        //找到前一个节点
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }

        //拼接新的节点
        ListNode toAdd = new ListNode(val);
        toAdd.next = pred.next;
        pred.next = toAdd;

        ListNode.printLinkedList(head.next);

    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= length) {
            return;
        }
        length--;
        ListNode pred = head;

        //还是找到前一个节点
        for (int i = 0; i < index; i++) {
            pred = pred.next;
        }

        //直接断链就行了
        pred.next = pred.next.next;
        ListNode.printLinkedList(head.next);
    }
}
