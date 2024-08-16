package LeetCode数据结构与算法基础.day3.链表;

/**
 * @author by KingOfTetris
 * @date 2024/8/14
 */
public class 回文链表 {

    //找到中点，反转后半部分链表，然后比较是否是回文串，只使用O(1)额外空间。
    public boolean isPalindrome(ListNode head) {

        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        //中间节点就是firstHalfEnd 没必要比较他。
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        //当后半段没结束就继续比较
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            //两者一起推
            p1 = p1.next;
            p2 = p2.next;
        }
        // 还原链表并返回结果
//        firstHalfEnd.next = reverseList(secondHalfStart);
        return true;
    }

    //头插法翻转head
    private ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode();

        while (head != null){
            ListNode temp = head;
            head = head.next;

            temp.next = dummy.next;
            dummy.next = temp;
        }

        return dummy.next;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

 /*   作者：力扣官方题解
    链接：https://leetcode.cn/problems/palindrome-linked-list/solutions/457059/hui-wen-lian-biao-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
