package LeetCode_HOT100题;

import LeetCode数据结构入门.day3.链表.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author by KingOfTetris
 * @date 2022/10/11
 */
public class 合并K个升序链表 {

    //优先队列 取名叫pq的时候就是队列，如果叫heap 那就是堆(默认是小根堆)

    /**
     * 这题的难点的话，可能就是这个数据结构你得知道
     * 因为是比较复杂的对象，所以要指定比较的方法
     * 所以初始化的时候给了一个比较器，Comparator
     * 并且指定比较方法是comparingInt()。比较的是对象的val
     * 使用JDK8 lambda表达式写法就是 Comparator.comparingInt(o -> o.val))
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        //按照List的头节点的值的大小进行升序
//        PriorityQueue<ListNode> pq = new PriorityQueue<>();
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        //链表入队
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        //新建一个虚拟头节点，head.next指向链表的开头
        ListNode head = new ListNode();
        //尾节点，这个节点是一直会动的。最后会变成尾巴所以叫tail
        ListNode tail = head;

        //当pq不为空
        while(!pq.isEmpty()) {
            //当前链表出队
            ListNode cur = pq.poll();
            //tail指向当前节点
            tail.next = cur;
            tail = tail.next;//tail后移
            //如果cur.next不为空就继续添加进优先队列
            //每次添加进优先队列都会把链头最小的整理到队头
            //这样你每次poll出来都能保证最小。
            if (cur.next != null) {
                pq.offer(cur.next);
            }
        }

        //最后返回head.next就是整条完整的升序链
        return head.next;
    }
}
