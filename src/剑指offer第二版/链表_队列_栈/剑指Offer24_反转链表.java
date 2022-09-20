package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构入门.day3.链表.ListNode;
import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/7/26 14:03
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 *  
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer24_反转链表 {

    @Test
    public void test(){
        ListNode listNode = ListNode.createListNode(new int[]{1, 2, 3, 4, 5});
        ListNode node = reverseList3(listNode);
        ListNode.printLinkedList(node);
    }
    /**
     * 头插法反转链表
     * 1.利用中间节点的头插法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode();//Dummy节点方便操作。
        while (head != null){
            ListNode temp = head;
            head = head.next;//每次取到头节点就后移


            temp.next = dummy.next; // temp指向dummy的next
            dummy.next = temp; //dummy的next指向temp，把temp置于表头
        }

        return dummy.next;//返回dummy.next即可
    }

    /**
     * 双指针 迭代
     * 考虑遍历链表，并在访问各节点时修改 next 引用指向，算法流程见注释。
     * 双指针的思想就是找到前后两个节点
     */
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null,cur = head;//第一个节点的pre是null
        while (cur != null){
            ListNode tmp = cur.next; // cur先往后走，把后继节点放到temp里
            cur.next = pre;          // 反向指回来就完事了。

            pre = cur;               //  pre往后走
            cur = tmp;               //  cur也往后走
        }
        return pre; //当cur为空,pre就是反过来的头节点
    }


    /**
     *  递归
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        //递归终止条件是当前为空，或者下一个节点为空
        if(head==null || head.next==null) {
            return head;
        }
        //这里的cur就是最后一个节点
        ListNode cur = reverseList(head.next); //cur没变，一直是5
        //这里请配合动画演示理解
        //如果链表是 1->2->3->4->5，那么此时的cur就是5
        //而head是4，head的下一个是5，下下一个是空
        //所以head.next.next 就是5->4

        //看不懂这里的去看下面的网站图例。
        // https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/solution/dong-hua-yan-shi-duo-chong-jie-fa-206-fan-zhuan-li/
        head.next.next = head;
        //防止链表循环，需要将head.next设置为空
        head.next = null;
        //每层递归函数都返回cur，也就是最后一个节点
        return cur;
    }
}
