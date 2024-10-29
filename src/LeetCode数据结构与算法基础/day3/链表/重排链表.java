package LeetCode数据结构与算法基础.day3.链表;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author KingofTetris
 * @File 重排链表
 * @Time 2021/10/22  10:45
 */

/*
给定一个单链表 L 的头节点 head ，单链表 L 表示为：
从L0 → L1 → … → Ln-1 → Ln
变L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

输入: head = [1,2,3,4]
输出: [1,4,2,3]

输入: head = [1,2,3,4,5]
输出: [1,5,2,4,3]

提示：

链表的长度范围为 [1, 5 * 104]
1 <= node.val <= 1000
*/
public class 重排链表 {

    @Test
    public void test(){
        ListNode listNode = ListNode.createListNode(new int[]{1, 2, 3, 4, 5});
        //Object 类提供的toString方法总是返回该对象实现类的类名 + @ +hashCode值。
        //LeetCode数据结构入门.day3.链表.ListNode@22927a81
        System.out.println(listNode.toString());
        reorderList2(listNode);

        //LeetCode数据结构入门.day3.链表.ListNode@22927a81 发现没有，hashCode居然没有变？？我操
        //也就是说指向的还是同一个对象，也就是开头节点listNode1
        System.out.println(listNode.toString());


        while (listNode !=null){
            System.out.print(listNode.val + "\t");
            listNode = listNode.next;
        }
    }


    //不太容易想到，但是可以借助双端队列 一个函数直接完成。
    //双端队列 LinkedList的(add/poll)First 和(add/poll)Last方法就是来回使用就构成了双端队列
    public void reorderList(ListNode head) {
        if(head == null)
            return;
        LinkedList<ListNode> deque = new LinkedList<>();
        //建议写成这样。
        ListNode p = head;
        while (p != null){
            deque.add(p);
            p = p.next;
        }
        ListNode vh = new ListNode(0);
        while (!deque.isEmpty()){
            ListNode prev = deque.pollFirst();
            vh.next = prev;
            ListNode next = deque.pollLast();
            prev.next = next; //prev.next指向next 就是正数第n个指向倒数第n个。
            //如果双端队列的元素都poll完了，则next必为空 就结束了
            if (next == null){
                return;
            }
            //vh一直指向链表的最后节点
            //这样才能连续起来。
            vh = next;
        }
        //为了避免链表成环 比如1，2，3，4 你模拟一下就明白了
        //最后vh指向null
        vh.next = null;
    }


    //找到链表的中点，|然后反转后半段（头插法或者用栈），最后建个新链表
    //而且为什么下面这种先找中点，反转后半部分，然后两条链表进行合并的方法比上面那种双端队列快得多??
    public void reorderList2(ListNode head) {
        if (head == null || head.next == null)
            return;
        ListNode mid = findMiddle(head);
        ListNode p = head;
        ListNode q = mid.next;
        //断链
        mid.next = null;
        //后半段逆序一下
        q = reverseList(q);
        //然后重新合并即可。
        mergeList(p,q);
    }

    public ListNode findMiddle(ListNode head){
        //没有，或者只有1个 你也别玩了
        if (head == null || head.next == null)
            return head;
        //快慢指针找中点，一个走两步，一个走一步
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    //这个合并函数还挺恶心的，如果不是题目要求只能改动原列表，返回void
    // 建议用dummyHead来写，返回一个新链表。不然真的恶心
    public void mergeList(ListNode l1,ListNode l2){
        ListNode l1_tmp;
        ListNode l2_tmp;
        //其中一个为空 也就结束了，所以才可以这样写。
        while (l1 != null && l2 != null){
            l1_tmp = l1.next;
            l2_tmp = l2.next;
            l1.next = l2;
            l1 = l1_tmp;
            l2.next = l1;
            l2 = l2_tmp;
        }
    }

    public ListNode mergeList2(ListNode l1,ListNode l2){
        ListNode res = new ListNode();
        ListNode dummyHead = res;
        int k = 0;
        while (l1 != null && l2 != null){
            if (k % 2 == 0){
                res.next = l1;
                l1 = l1.next;
            }
            else {
                res.next = l2;
                l2 = l2.next;
            }
            k++;
            res = res.next;
        }
        //只有可能是l2为空，因为我们把中点给了l1
        //但我们用通用的写法
        if (l1 == null) res.next = l2;
        if (l2 == null) res.next = l1;

        //返回头节点即可。
        return dummyHead;
    }

}
