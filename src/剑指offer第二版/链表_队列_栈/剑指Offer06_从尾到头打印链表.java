package 剑指offer第二版.链表_队列_栈;

import LeetCode数据结构基础.day3.链表.ListNode;
import org.junit.Test;

import java.util.Stack;

/**
 * @Author KingofTetris
 * @Date 2022/7/8 15:27
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer06_从尾到头打印链表 {

    @Test
    public void test(){
        int[] vals = {1,2,3};
        ListNode listNode = ListNode.createListNode(vals);
        ListNode.printLinkedList(listNode);
        int[] reverse = reversePrint2(listNode);
        for (int i = 0; i < reverse.length; i++) {
            System.out.print(reverse[i] + "\t");
        }
    }

    /**
     * 利用stack反转。
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while(head != null){
            stack.push(head);
            head = head.next;
        }
        int[] reverse = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty()){
           reverse[i] = stack.pop().val;
           i++;//傻逼了 i忘了++
        }
        return reverse;
    }

    /**
     * 不借助栈 空间和时间都比较快 首选这个
     * 真的首选这个吗？
     * 你下面那个reverse数组，空间不就是O(n)?
     * @param head
     * @return
     */
    public int[] reversePrint2(ListNode head) {
       //先找出链表的长度
        int size = 0;
        ListNode q = head;
        while (q != null){
            size++;
            q = q.next;
        }

        int[] reverse = new int[size];
        size = size - 1;//因为要用到数组里 所以要-1
        while (head != null){
            reverse[size] = head.val;
            head = head.next;
            size--;
        }
        return reverse;
    }
}
