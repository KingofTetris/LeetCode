package LeetCode数据结构基础.day3.链表;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author KingofTetris
 * @File 环形链表II
 * @Time 2021/10/19  11:19
 */

/*给定一个链表，如果链表存在环，请你返回环的入口。 如果链表无环，则返回 null。
        为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
         如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
        说明：不允许修改给定的链表。
        进阶：
        你是否可以使用 O(1) 空间解决此题？
        示例 1：
        输入：head = [3,2,0,-4], pos = 1
        输出：返回索引为 1 的链表节点
        解释：链表中有一个环，其尾部连接到第二个节点。
        示例 2：
        输入：head = [1,2], pos = 0
        输出：返回索引为 0 的链表节点
        解释：链表中有一个环，其尾部连接到第一个节点。
        示例 3：
        输入：head = [1], pos = -1
        输出：返回 null
        解释：链表中没有环。
        提示：
        链表中节点的数目范围在范围 [0, 104] 内
        -105 <= Node.val <= 105
        pos 的值为 -1 或者链表中的一个有效索引*/
public class 环形链表II {
    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(8);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
//        ListNode.createListNode()这个只能生成一个简单的单链表，不能成环
//        ListNode.printLinkedList(node1); 也不能打印环，没给他添加打印环的逻辑。
        System.out.println("环的开头是:" + detectCycle(node1).val);
        System.out.println("环的开头是:" + detectCycle_Map(node1).val);
    }

    //利用额外空间O(n)的哈希表 指针第一次重复走过的节点就是环形入口
    //体现在map中第一个Integer为2的Key
    //如果key全是1 就没有环
    public ListNode detectCycle_Map(ListNode head) {
        ListNode cycle = new ListNode(-1);
        ListNode p = head;
        HashMap<ListNode, Integer> map = new HashMap<>();
        while (p != null) {
            map.put(p, map.getOrDefault(p, 0) + 1);
            //当然找到了就要break跳出，不然有环就死循环了,无环的话，p会指向null 自然会跳出
            if (map.get(p) == 2) {
                cycle = p;//这个p就是环的入口
                break;
            }
            p = p.next;
        }
      /*  ListNode res = new ListNode();
        if (cycle.val != -1){
            res = cycle;
        }*/
        //打印了一下环
        do {
            System.out.print(cycle.val + "\t");
            cycle = cycle.next;
        } while (cycle.next != null && map.get(cycle) != 2);
        if (cycle.next != null) {
            return cycle;
        }
        //无环return null
        return null;
    }


    /**
     * 不利用额外空间 双指针+数学推导
     * 快慢指针，相遇后，从相遇节点和head同时出发，再相遇时的节点就是入口节点，
     * 这个结论的推到看下面的链接
     * https://programmercarl.com/0142.%E7%8E%AF%E5%BD%A2%E9%93%BE%E8%A1%A8II.html#%E6%80%9D%E8%B7%AF
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode meet = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                meet = fast;
                break;
            }
        }
        //无环
        if (meet == null) return null;

        //找入口，slow重新回到原点
        slow = head;
        //调整步长都是1步
        while (slow != meet) {
            slow = slow.next;
            meet = meet.next;
        }
        //当他们再次相遇，那就是环的入口。
        //返回slow fast都行。
        return meet;
    }
}
