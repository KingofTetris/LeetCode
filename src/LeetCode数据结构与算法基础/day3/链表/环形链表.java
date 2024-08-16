package LeetCode数据结构与算法基础.day3.链表;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 环形链表
 * @Time 2021/10/3  13:21
 */

/*141. 环形链表
        给定一个链表，判断链表中是否有环。

        如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

        如果链表中存在环，则返回 true 。 否则，返回 false 。



        进阶：

        你能用 O(1)（即，常量）内存解决此问题吗？



        示例 1：



        输入：head = [3,2,0,-4], pos = 1
        输出：true
        解释：链表中有一个环，其尾部连接到第二个节点。
        示例 2：



        输入：head = [1,2], pos = 0
        输出：true
        解释：链表中有一个环，其尾部连接到第一个节点。
        示例 3：



        输入：head = [1], pos = -1
        输出：false
        解释：链表中没有环。


        提示：

        链表中节点的数目范围是 [0, 104]
        -105 <= Node.val <= 105
        pos 为 -1 或者链表中的一个 有效索引 。*/
public class 环形链表 {
    @Test
    public void test(){
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        p1.next = p2;
        p2.next = p1;
        System.out.println(hasCycle(p1));
    }


    //经典题目用双指针，如果快指针追上慢指针就一定有环
    //请你证明快指针一定会碰到慢指针？
    //兔子每次走2步，乌龟每次1步，兔子相对乌龟来说其实就是乌龟不动，兔子走1步，如果有环，那么兔子一定会追到乌龟。
    public boolean hasCycle(ListNode head) {
        //没有单环这种东西限制，存在null 那么肯定无环。
        if (head == null || head.next == null) {
            return false;
        }
        //快慢指针
        ListNode slow = head;
        ListNode fast = head;
        //注意一下这个停止条件，如果都从head出发，那么停止条件最好写出这个
        //fast != null 且 fast.next != null
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;

      /*  作者：力扣官方题解
        链接：https://leetcode.cn/problems/linked-list-cycle/solutions/440042/huan-xing-lian-biao-by-leetcode-solution/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
