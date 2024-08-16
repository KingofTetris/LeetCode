package LeetCode数据结构与算法基础.day3.链表;

/**
 * @author KingofTetris
 * @File 两两交换链表中的节点
 * @Time 2021/10/21  11:57
 */

import org.junit.Test;

/** 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

        你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

         

        示例 1：


        输入：head = [1,2,3,4]
        输出：[2,1,4,3]
        示例 2：

        输入：head = []
        输出：[]
        示例 3：

        输入：head = [1]
        输出：[1]
         

        提示：
        链表中节点的数目在范围 [0, 100] 内
        0 <= Node.val <= 100
        进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）*/

public class 两两交换链表中的节点 {
    @Test
    public void test(){
        ListNode head = ListNode.createListNode(new int[]{1,2,3,4,5,6});
        ListNode node = swapPairs1(head);
        while (node != null){
            System.out.print(node.val + "\t");
            node = node.next;
        }
    }
    //简单说就是两个一组来换，第一和第二  第三和第四。类推。 并不是1 2换完变2 1  再 1 3换 3 1
    //简单交换个值也是能过的。因为没办法检测出next到底换没换
    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return null ;
        ListNode p1 = head;
        ListNode p2 = head.next;
        while(p2!=null){
            int val = p1.val;
            p1.val =  p2.val;
            p2.val = val;
            //都后移两步。
            if (p2.next != null){
                p1 = p1.next.next;
                p2 = p2.next.next;
            }
            else //p2到最后了 没必要再换了
                break;
        }
        return head;
    }

    //真正换next的指向
    //链表的题目没什么好讲的 一定要多弄几个指针 省的把自己绕晕，一定要画图
    public static  ListNode swapPairs1(ListNode head) {
        //只有一个或者空节点就别瞎折腾了。
        if (head == null || head.next == null)
            return head;
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null){
             ListNode p1 = temp.next;
             ListNode p2 = temp.next.next;
             //最重要的四句话 实际上是取了利用一个临时节点temp来交换next
             temp.next = p2;
             p1.next = p2.next;
             p2.next = p1;
             //最后temp移动到p1，重复这个过程。
             temp = p1;
        }
        //dummyHead始终是不动的，dummyHead.next一直指向实际链表的头部
        return dummyHead.next;
    }

}
