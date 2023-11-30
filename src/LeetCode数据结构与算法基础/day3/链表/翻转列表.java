package LeetCode数据结构与算法基础.day3.链表;

/**
 * @author KingofTetris
 * @File 翻转列表
 * @Time 2021/10/22  17:13
 */
public class 翻转列表 {

    public static void main(String[] args) {
        ListNode node = ListNode.createListNode(new int[]{1, 2, 3, 4, 5});
        ListNode reverseList = reverseList(node);
        while (reverseList != null){
            System.out.print(reverseList.val + "\t");
            reverseList = reverseList.next;
        }
    }
    /**
     * 反转链表
     * @param head 头节点
     * @return
     * 头插法 + 临时节点 原地翻转链表
     */
    public static ListNode reverseList(ListNode head){
        ListNode dummy = new ListNode();
        dummy.next = null;
        //dummy永远指向头部
        while (head != null){
            ListNode temp = head;
            head = head.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }
        return dummy.next;
    }
}
