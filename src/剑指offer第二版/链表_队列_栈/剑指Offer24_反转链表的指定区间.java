package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构入门.day3.链表.ListNode;
import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.util.ArrayList;

/**
 * 第一题我们反转了整个链表，现在我们更新需求，我们仅仅想要更改链表的指定区间。
 * 比如1->2->3->4->5 我只想反转区间[2,4]。变成
 * 1->4->3->2->5
 * 请你实现代码。
 */
public class 剑指Offer24_反转链表的指定区间 {

    @Test
    public void test() {
        ListNode listNode = ListNode.createListNode(new int[]{1,2,3,4,5,6,7,8,9,10});
        ListNode node = reverseInternList(listNode,2,9);
        ListNode.printLinkedList(node);
    }

    //其实反转部分，还是可以通过 头插法把这一部分先反转了。再去拼接首尾。
    //这就需要我们先记录下来原本 这部分中第一个节点的pre 和 最后一个节点last的next

    //然后 pre.next = dummy.next; 接上开头
    // 要接上结尾就麻烦一点，你要先遍历整个反转部分找到尾巴part_last
    // 然后part_last = last.next;才能实现部分链表反转
    private ListNode reverseInternList(ListNode head, int start, int end) {
        if (start == end) return head;//如果两个相等，那么等于不用反转。
        //先定位反转链表
        int p0 = 0,p1= 0;
        ListNode node1 = null,node2 = null; //记录反转链表
        ListNode pre = null,tail = null;
        //找到我们需要的4个节点
        while (head != null){
            //head不等于1，计数+1
            p0++;
            p1++;
            if (p0 != start){ //定位开头
                if (p0 == start - 1){ //记录node1的前一个节点
                    pre = head;
                }
                head = head.next;
            }else { //如果已经找到了开头节点。
                node1 = head;//记录开头节点
                //开始定位结尾
                while (p1 != end && head.next != null){
                    head = head.next;
                    p1++;
                }
                //记录反转结束链表
                node2 = head;
                //记录最后结果要接上的尾巴
                tail = head.next;
                //就可以结束不用在继续遍历head了
                break;
            }
        }


        //开始反转部分链表。
        ListNode dummyHead = new ListNode();
        //从node1一直到node2都是需要反转的部分，那么到node2.next就结束反转
        //小心这里有坑，因为temp.next这句话会导致node2被修改了。已经不是原来的方向了。
//        所以不要使用node2.next，直接用tail代替
        while (node1 != null && node1 != tail){
            ListNode temp = node1;
            node1 = node1.next;

            temp.next = dummyHead.next;
            dummyHead.next = temp;
        }
        //最后拼接头部
        pre.next = dummyHead.next;
        //记录个链表头
        ListNode finalHead = pre;
        //拼接尾部
        //尾部要麻烦点
        //遍历到尾部
        while (pre.next != null){
            pre = pre.next;
        }
        pre.next = tail;

        //大功告成。
        return finalHead;
    }


}
