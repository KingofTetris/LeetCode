package LeetCode算法20天.day2.双指针;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 链表倒数第N个节点
 * @Time 2021/9/21  15:28
 */

/*链表中结点的数目为 sz
        1 <= sz <= 30
        0 <= Node.val <= 100
        1 <= n <= sz*/
//同一个包下的public（默认）类可以共用
public class 链表倒数第N个节点 {

    @Test
    public void test(){
        ListNode q6 = new ListNode(6);
        ListNode q5 = new ListNode(5,q6);
        ListNode q4 = new ListNode(4,q5);
        ListNode q3 = new ListNode(3,q4);
        ListNode q2 = new ListNode(2,q3);
        ListNode q1 = new ListNode(1,q2);
        ListNode q = removeNthFromEnd(q1,5);
        while (q != null){
            System.out.print(q.val + "\t");
            q = q.next;
        }
    }

    //让快指针先走n步 快指针走到链表末尾 指向null时才叫末尾，慢指针就是倒数第n个节点
    //因为要删除 所以要知道前一个节点，所以再慢指针前面再加一个哑节点。
    //所以我们才要用dummy去赋值slow
    //这样我们就找到了第N个节点的前驱，直接断链就方便多了
    public ListNode removeNthFromEnd(ListNode head, int n) {

        //这些赋值玩来玩去 其实还是一条链，不是两条，不要搞错了
        ListNode dummy = new ListNode(-1,head);
        ListNode fast = head;
        ListNode slow = dummy;

        //先走n步
        for (int i = 1; i <= n; i++) {
            fast = fast.next;
        }
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        //直接断链就删除了
        slow.next = slow.next.next;//注意现在slow已经是倒数第N个节点的前驱节点 而不是整条链
        //所以我们
        return dummy.next;
    }
}
