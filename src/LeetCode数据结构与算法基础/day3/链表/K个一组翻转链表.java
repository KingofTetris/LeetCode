package LeetCode数据结构与算法基础.day3.链表;

/**
 * @author KingofTetris
 * @File K个一组翻转链表
 * @Time 2021/10/22  16:45
 */

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * 示例 3：
 * <p>
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 * 示例 4：
 * <p>
 * 输入：head = [1], k = 1
 * 输出：[1]
 * 提示：
 * <p>
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 */

public class K个一组翻转链表 {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 假设的dummy节点，接在每一段头节点之前
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        // 用于记录头节点返回
        ListNode hair = dummy;
        // 初始化tail，代表每个小段链表的末尾
        ListNode tail = dummy;
        ListNode start, next;
        // 遍历链表
        while (tail.next != null) {
            // 移动tail到小段链表末尾
            // k 个一组
            for (int i = 0; i < k && tail != null; i++) {
                tail = tail.next;
            }
            //最后一段不够反转的,不需要反转
            // 直接跳出即可
            if (tail == null) {
                break;
            }
            // 设置next
            next = tail.next;
            // 设置start为起始
            start = dummy.next;
            // 断开链表 进行反转，就不用去管长度了。
            tail.next = null;
            // 反转链表（start，tail)
            dummy.next = reverseListNode(start);
            // 接上链表末尾,此时start为反转后链表末尾
            start.next = next;
            // 移动指针寻找下一段链表
            dummy = start;// 此时start已经是当前逆序链表的末尾
            tail = dummy; // 移动末尾为dummy相当于再次找到表头，下一次继续根据k移动tail
        }

        //最终返回整个链表
        return hair.next;
    }

    public ListNode reverseListNode(ListNode head){
        ListNode dummy = new ListNode();
        dummy.next = head;
        while (head != null){
            ListNode temp = head; //当前节点
            head = head.next;
            //插入到dummy后
            temp.next = dummy.next;
            dummy.next = temp;
        }
        return dummy.next;
    }
}
