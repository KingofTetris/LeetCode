package LeetCode数据结构与算法基础.day3.链表;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 设计链表
 * @Time 2021/10/21  13:51
 */


/**设计链表的实现。
        您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。
        val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，
        则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。*/
public class MyLinkedList {


    int size;
    ListNode dummyHead;
    @Test
    public void test(){
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);//1
        myLinkedList.addAtTail(3);//1 3
        myLinkedList.addAtIndex(1,2);//1 2 3
        myLinkedList.show();
        System.out.println(myLinkedList.get(1));//2
        myLinkedList.deleteAtIndex(1);//1 3
        myLinkedList.show();
        System.out.println(myLinkedList.get(1));//3
    }

    public void show(){
        ListNode p = dummyHead.next;
        while (p != null){
            System.out.print(p.val + "\t");
            p = p.next;
        }
    }
    public MyLinkedList() {
        dummyHead = new ListNode();
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index > size) throw new IllegalArgumentException("参数异常");

        ListNode p = dummyHead;
        //因为用了哑节点 要+1 或者<=index
        for (int i = 0; i <=index; i++) {
             p  = p.next;
        }
        return p.val;
    }


    //头插法
    public void addAtHead(int val) {
        addAtIndex(0,val);
    }


    //尾插法
    public void addAtTail(int val) {
       addAtIndex(size,val);
    }


    //指定位置插入
    /**
     * 在链表中的第 index 个节点之前添加值为 val  的节点。
     * 如果 index 等于链表的长度，则该节点将附加到链表的末尾。
     * 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
     **/

    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    public void addAtIndex(int index, int val) {
       if (index > size)
           return;
       if (index < 0) index = 0;

       size++;

       ListNode pre = dummyHead;

       //注意找的是前驱节点 这个不用+1
        for (int i = 0; i < index ; i++) {
            pre = pre.next;
        }

        ListNode toAdd = new ListNode(val);
        toAdd.next = pre.next;
        pre.next = toAdd;
    }


    //删除指定位置元素
    public void deleteAtIndex(int index) {
        if (index > size - 1 || index < 0)
            return;
        ListNode pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        pre.next = pre.next.next;
        size--;
    }

}
