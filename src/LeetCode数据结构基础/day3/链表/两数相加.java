package LeetCode数据结构基础.day3.链表;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 两数相加
 * @Time 2021/10/19  9:39
 */


//其实这题类似大数相加。
/*2. 两数相加
        给你两个 非空 的链表，表示两个非负的整数。
        它们每位数字都是按照 **逆序** 的方式存储的，
        并且每个节点只能存储 一位 数字。
        请你将两个数相加，并以相同形式返回一个表示和的链表。
        你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

        示例 1：
        输入：l1 = [2,4,3], l2 = [5,6,4]
        输出：[7,0,8]
        解释：342 + 465 = 807.
        示例 2：

        输入：l1 = [0], l2 = [0]
        输出：[0]
        示例 3：

        输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
        输出：[8,9,9,9,0,0,0,1]


        提示：

        每个链表中的节点数在范围 [1, 100] 内
        0 <= Node.val <= 9
        题目数据保证列表表示的数字不含前导零*/
public class 两数相加 {


    @Test
    public void test() {
        int[] num1 = {2, 4, 3};
        int[] num2 = {5, 6, 4};
        ListNode list1 = ListNode.createListNode(num1);
        ListNode list2 = ListNode.createListNode(num2);
        ListNode res = addTwoNumbers(list1, list2);
        while (res != null) {
            System.out.print(res.val + "\t");
            res = res.next;
        }
    }

    //用0补齐链表，使得长度一致
   /* public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        int len1 = 0,len2 = 0;
        ListNode p = l1;
        ListNode q = l2;
        while(p != null || q != null){
            if (p != null)
            {   len1++;
                p = p.next;
            }
            if (q != null){
                len2++;
                q = q.next;
            }
        }


        //现在p,q都指在l1,l2的尾巴
        if (len1 > len2){
            for (int i = 1; i < len1 - len2; i++) {
                q.next = new ListNode(0);
                q = q.next;
            }
        }
        else{
            for (int i = 1; i < len1 - len2; i++) {
                p.next = new ListNode(0);
                p = p.next;
            }
        }


        //重新指回头部
        p = l1;
        q = l2;
        int add = 0;//记录是否有进位
        ListNode w = res;//指向结果链表的指针

        int sum = 0;//相加结果

        while (p!=null&&q!=null){
            sum = add + q.val + p.val;
            w.next = new ListNode(sum%10);
            add = sum / 10;
            w = w.next;
            q = q.next;
            p = p.next;
        }


        //最后还有进位
        if (add > 0){
            w.next = new ListNode(add);
        }

        //因为next才是真正的值，res是空节点
        return res.next;
    }*/

    //不用补齐，直接判断为null就当作0加上去
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 记录进位
        int carry = 0;
        // 记录该位的和
        int sum = 0;
        // 无用的头节点 用来记录结果的开头dummy
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        // 只要l1或者l2不为空或者还有进位就继续循环。
        while (l1 != null || l2 != null || carry != 0) {
            // 该结果位的值等于两链表对应位置的值之和再加上上一位的进位
            sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            // 计算该位的进位
            carry = sum / 10;
            // 该位的值等于sum与10取余
            p.next = new ListNode(sum % 10);
            p = p.next;
            //链表往后移
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;
        }
        return dummy.next;
    }
}
