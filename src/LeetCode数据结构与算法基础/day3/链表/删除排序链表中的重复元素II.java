package LeetCode数据结构与算法基础.day3.链表;

/**
 * @author KingofTetris
 * @File 删除排序链表中的重复元素II
 * @Time 2021/10/20  11:38
 */

/**
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，
 * 请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。

        返回同样按升序排列的结果链表。

        示例 1：


        输入：head = [1,2,3,3,4,4,5]
        输出：[1,2,5]
        示例 2：


        输入：head = [1,1,1,2,3]
        输出：[2,3]
         

        提示：

        链表中节点数目在范围 [0, 300] 内
        -100 <= Node.val <= 100
        题目数据保证链表已经按升序排列*/


public class 删除排序链表中的重复元素II {


    //遍历+双指针 删除重复元素
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)       return head;
        //随便给个值就行了
        ListNode newHead = new ListNode(-666);
        newHead.next  = head;

        //left指向newHead right指向head
        ListNode left = newHead, right = newHead.next;

        //遍历到尾部为止
        while(right != null){
            int pre = right.val;

            //preNode保存前结点
            ListNode preNode = null;
            int count = 0;

            //数值相等就继续后移
            while(right != null && right.val == pre){
                count++;
                preNode = right;
                right = right.next;
            }

            if(count == 1){
                left.next = preNode;
                left = left.next;
            }else{
                left.next = right;
            }
        }

        return newHead.next;
    }
}
