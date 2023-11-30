package LeetCode数据结构与算法基础.双指针;


import org.junit.Test;

/**
 * @author KingofTetris
 * @File 链表的中间结点
 * @Time 2021/9/21  14:25
 */

/*给定一个头结点为 head 的非空单链表，返回链表的中间结点。

        如果有两个中间结点，则返回第二个中间结点。

        示例 1：

        输入：[1,2,3,4,5]
        输出：此列表中的结点 3 (序列化形式：[3,4,5])
        返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
        注意，我们返回了一个 ListNode 类型的对象 ans，这样：
        ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
        示例 2：

        输入：[1,2,3,4,5,6]
        输出：此列表中的结点 4 (序列化形式：[4,5,6])
        由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
         

        提示：

        给定链表的结点数介于 1 和 100 之间。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/middle-of-the-linked-list
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    public ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class 链表的中间结点 {
    @Test
    public void test(){
        ListNode q6 = new ListNode(6);
        ListNode q5 = new ListNode(5,q6);
        ListNode q4 = new ListNode(4,q5);
        ListNode q3 = new ListNode(3,q4);
        ListNode q2 = new ListNode(2,q3);
        ListNode q1 = new ListNode(1,q2);

        ListNode q = middleNode(q1);
        System.out.print(q.val);


    }

    public ListNode middleNode(ListNode head) {
        //一个快一个慢，快的到尾巴时，慢的到中间

        //快指针
        ListNode p = new ListNode();
        //慢指针
        ListNode q = new ListNode();
        p = head;
        q = head;
        while(p != null && p.next != null){
            p = p.next.next;
            q = q.next;
        }
        return q;
    }
}
