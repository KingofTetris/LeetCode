package LeetCode数据结构与算法基础.day4.栈和队列;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 设计循环队列 {

    //其实就是个手写双链表而已。
    static class Node {
        int val;
        Node pre;
        Node next;
    }

    private int capacity;
    private int size;
    private Node head;
    private Node tail;

    public 设计循环队列(int k) {
        capacity = k;
        size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public boolean enQueue(int value) {
        if (size == capacity) {
            return false;
        }
        Node node = new Node();
        node.val = value;
        node.next = head.next;
        head.next = node;
        node.next.pre = node;
        node.pre = head;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (size <= 0) {
            return false;
        }
        tail.pre.pre.next = tail;
        tail.pre = tail.pre.pre;
        size--;
        return true;
    }

    public int Front() {
        if (size == 0) {
            return -1;
        }
        return tail.pre.val;
    }

    public int Rear() {
        if (size == 0) {
            return -1;
        }
        return head.next.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}
