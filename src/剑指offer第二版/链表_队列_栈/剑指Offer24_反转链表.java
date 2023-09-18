package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构入门.day3.链表.ListNode;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @Author KingofTetris
 * @Date 2022/7/26 14:03
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= 节点个数 <= 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer24_反转链表 {

    @Test
    public void test() {
        ListNode listNode = ListNode.createListNode(new int[]{1, 2, 3, 4, 5});
        ListNode node = reverseList(listNode);
        ListNode.printLinkedList(node);
    }

    /**
     * 头插法+中间节点 就是这个简单反转链表的最佳解！
     * 1.利用中间节点的头插法
     *  创建一个哑节点，每次在哑节点后插入节点充当头节点就是头插法。
     *  头插法的好处就是不用每次遍历链表。
     *  比如
     *  1->2->3->4->5
     *  加上哑节点D用头插法反转一次
     *  D->1
     *  两次
     *  D->2->1
     *  三次
     *  D->3->2->1
     *  四次
     *  D->4->3->2->1
     *  五次
     *  D->5->4->3->2->1
     *  最后返回D.next就完成了反转。这个过程需要一个中间节点进行辅助。
     *  结束条件是head != null 也就是遍历完整个链表。
     *  另外对于链表的操作，很多时候，位置是至关重要的，不能改变代码的位置。
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode();//Dummy节点方便操作。
        while (head != null) {
            ListNode temp = head;//每次获得新的表头。
            head = head.next;//然后就要马上让head后移。
            // 因为我们后面会修改temp的next，这个temp其实就是head。因为他们的地址是一样的。
            //如果你不在这里原本还保留的地方后移head，那么原本的链表就会被舍弃，就变成只插入了1个!!
            //下面把temp插入到表头。
            temp.next = dummy.next; // temp指向dummy的next，完成头插第一步,temp.next舍弃原来的指向，转向到dummy尾巴。
            dummy.next = temp; //dummy的next指向temp，完成头插第二步 在dummy里面插入这个temp
            //经过上面两补就完成了temp置于表头的操作
        }
        return dummy.next;//返回dummy.next即可
    }

    /**
     * 最简单的利用栈，列表实现反转。
     * @param head
     * @return
     */
    public ListNode reverseList4(ListNode head) {
        // write code here
        ArrayList<ListNode> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head);
            head = head.next;
        }
        //逆序遍历链表，尾插法插入即可
        ListNode dummyHead = new ListNode(-1);//哑节点方便返回链表开头。
        ListNode p = dummyHead;
        for (int i = arr.size() - 1; i >= 0; i--) {
            arr.get(i).next = null;//先修改一下原本节点的指向，防止形成回路
            p.next = arr.get(i);//修改当前节点的next为arr.get(i)
            p = p.next; //移动p指针
        }
        return dummyHead.next;
    }

    /**
     * 双指针 迭代
     * 考虑遍历链表，并在访问各节点时修改 next 引用指向，算法流程见注释。
     * 双指针的思想就是找到前后两个节点
     */
    public ListNode reverseList2(ListNode head) {
        ListNode pre = null, cur = head;//第一个节点的pre是null
        while (cur != null) {
            ListNode tmp = cur.next; // cur先往后走，把后继节点放到temp里
            cur.next = pre;          // 反向指回来就完事了。
            pre = cur;               //  pre往后走
            cur = tmp;               //  cur也往后走
        }
        return pre; //当cur为空,pre就是反过来的头节点
    }


    /**
     * 递归
     *
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        //递归终止条件是当前为空，或者下一个节点为空
        if (head == null || head.next == null) {
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
